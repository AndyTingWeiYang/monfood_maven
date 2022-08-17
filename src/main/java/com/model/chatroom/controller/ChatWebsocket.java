package com.model.chatroom.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.model.chatroom.ChatInfoVo;
import com.model.chatroom.dao.JedisHandleMessage;


@ServerEndpoint("/ChatWebsocket/{userId}")
public class ChatWebsocket {

	// 拿特定資料連線做發送
	private static Map<Integer, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userId") Integer userId, Session userSession) {
		// 一個session代表一個使用者跟server之間的通訊
		sessionsMap.put(userId, userSession);

	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatInfoVo chatInfoVo = gson.fromJson(message, ChatInfoVo.class);
		Integer senderId = chatInfoVo.getSenderId();
		Integer receiverId = chatInfoVo.getReceiverId();
		
		// 拿Redis裡面的歷史聊天紀錄，並發送給前端做顯示
		if ("history".equals(chatInfoVo.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(senderId, receiverId); // 有順序性，所以用list
			String historyMsg = gson.toJson(historyData);
			ChatInfoVo cmHistory = new ChatInfoVo("history", senderId, receiverId, historyMsg);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory)); // 送到前端
				System.out.println("history = " + gson.toJson(cmHistory));
				return;
			}

		}

		// 線上聊天流程
		// 拿到發送者的session
		Session receiverSession = sessionsMap.get(receiverId);
		if (receiverSession != null && receiverSession.isOpen()) {
			// 發送訊息
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
		}
		// 不管是否上線，把資料存進redis裡面>saveChatMessage()方法
		JedisHandleMessage.saveChatMessage(senderId, receiverId, message);
		System.out.println("Message received: " + message);



	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		System.out.println("Close " + reason.toString());
	}

}
