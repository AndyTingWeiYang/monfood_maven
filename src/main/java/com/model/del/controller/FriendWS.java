package com.model.del.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
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
import com.model.del.ChatMessage;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.del.State;
import com.model.del.service.DelServiceImpl;


@ServerEndpoint("/FriendWS/{userName}")
public class FriendWS {
	//                  這邊可以同時存入使用者資訊避免覆蓋              並行
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		/* save the new user in the map */
//		這裡的session是用來做聊天的比較類似hibernate的session但這邊的session是每個使用者連入就會產生一個所以不會有同步的問題?
		sessionsMap.put(userName, userSession);
//		因為上線才會存session進入map所以map裡面的人就是上線的人use keyset()to get all users online
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
//用一個pojo存非VO的屬性
//這一手太漂亮了用一個POJO專門來承載資料庫之外的必須資訊
//		所以這個VO裡面分別是目前的狀態, 使用者名稱, 誰在線上
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
//告訴所有人誰上線了
		Collection<Session> sessions = sessionsMap.values();
		//把sessions裡面的東西全部走過，每次走過的資料存在Session的session
		for (Session session : sessions) {
			if (session.isOpen()) {
				//中間拿遙控器不知道是啥看起來是用來送訊息的
				session.getAsyncRemote().sendText(stateMessageJson);
//				這個傳出去怎麼接阿
//				看起來是onopen傳訊息出去之後JSP會進入onmsg，然後剛剛丟出去的msg作為event傳入onmsg function判斷type=open刷新好友列表
			}
		}
//%s後面對應位置的變數; %n 換行
		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		Collection<Session> toAllSessions = sessionsMap.values();
		DelService service = new DelService();
		List<DelVO> dels = service.getAll();
		Set<String> allDels = new HashSet<String>();
		for(DelVO del : dels) {
			String delString = String.valueOf(del.getDelID());
			allDels.add(delString);
		}
		Set<String> userNames = sessionsMap.keySet();
		
		//把存在redis的歷史訊息取出
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
//			取出歷史訊息之後裝回包包裡面丟回去前端
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				System.out.println("history = " + gson.toJson(cmHistory));
				return;
			} 
		}
		
//前端送訊息之後訊息送給sender and reciver and save in redis
		Session receiverSession = sessionsMap.get(receiver);
		//那如果收件者不在線上呢?不會有這種狀況因為沒上線就點不到
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
		}
		else if("ToAll".equals(receiver)) {
			for(String userName : userNames) {
				sessionsMap.get(userName).getAsyncRemote().sendText(message);
				}
			for(String allDel : allDels) {
				JedisHandleMessage.saveChatMessage(sender, allDel, message);
			}
		}
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
//		抓出所有上線的人
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
//			找出自己並移出上線者名單
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}
//		因為在後端把自己移出上線名單之後還要去前端跟每個人更新是誰下線了所以先用一個usenameclose存起來
//		在用這個state的VO去做更新
		if (userNameClose != null) {
//			更新好的下線通知
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
//			把目前還在線上的使用者叫出來並發出下線通知
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
