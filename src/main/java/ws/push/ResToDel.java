package ws.push;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint("/ResToDel/{id}/{type}")
public class ResToDel {
	// map蒐集所有使用者的連線集合 用key針對特地對象發送訊息
//	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();
	private static Map<String, Session> resMap = new ConcurrentHashMap<>();
	private static Map<String, Session> delMap = new ConcurrentHashMap<>();
	private static Map<String, Session> userMap = new ConcurrentHashMap<>();
	// 連線進來存type

	@OnOpen // 連線建立 //使用者資訊 接受前端的路徑參數 //蒐集連線 使用者通訊 發送訊息靠連線
	public void onOpen(@PathParam("id") String id, @PathParam("type") String type, Session session) {

		try {
			// 存入各自set
			if (!("null".equals(id))) {
				if ("0".equals(type)) {
					resMap.put(id + type, session);

					Set<String> allDel = delMap.keySet();
					State stateResMessage = new State("resOpen", id, allDel);
					String stateResMessageJson = gson.toJson(stateResMessage);
					if (session.isOpen()) {
						session.getAsyncRemote().sendText(stateResMessageJson); // 發上線通知
					}

					System.out.println("allDel: " + allDel);
				} else if ("1".equals(type)) {
					delMap.put(id + type, session);
				} else {
					userMap.put(id + type, session);

					State stateUserMessage = new State("userOpen", id);
					String stateUserMessageJson = gson.toJson(stateUserMessage);
					if (session.isOpen()) {
						session.getAsyncRemote().sendText(stateUserMessageJson); // 發上線通知
					}

				}
			}

//			System.out.println("ResToDel : 與客戶端建立連線了！");
//			resMap.forEach((k, v) -> System.out.println("餐廳"+k));
//			delMap.forEach((k, v) -> System.out.println("外送員"+k));
//			userMap.forEach((k, v) -> System.out.println("使用者"+k));

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	// 收到資料 發生事件
	@OnMessage // 發送者的連線
	public void onMessage(@PathParam("id") String id, @PathParam("type") String type, Session session, String message)
			throws IOException, InterruptedException {
		PushInfo pushInfo = gson.fromJson(message, PushInfo.class);
		String receiverId = pushInfo.getReceiver();
		System.out.println("receiverId = " + receiverId);
		String rejectType = receiverId.substring(receiverId.length() - 1, receiverId.length());
		


//		0是商家 1是外送員 2是使用者
		if ("0".equals(type)) {
			if ("2".equals(rejectType)) {
				Session receiverSession = userMap.get(receiverId);
				if (receiverSession != null && receiverSession.isOpen()) {
					// 發送訊息
					receiverSession.getAsyncRemote().sendText(message);
					session.getAsyncRemote().sendText(message);
				}
			} else {
				Session receiverSession = delMap.get(receiverId);
				if (receiverSession != null && receiverSession.isOpen()) {
					// 發送訊息
					receiverSession.getAsyncRemote().sendText(message);
					session.getAsyncRemote().sendText(message);
				}
			}

		} else if ("1".equals(type)) {
			if ("0".equals(rejectType)) {
				Session receiverSession = resMap.get(receiverId);
				if (receiverSession != null && receiverSession.isOpen()) {
					// 發送訊息
					receiverSession.getAsyncRemote().sendText(message);
					session.getAsyncRemote().sendText(message);
				}
			} else {
				Session receiverSession = userMap.get(receiverId);
				if (receiverSession != null && receiverSession.isOpen()) {
					// 發送訊息
					receiverSession.getAsyncRemote().sendText(message);
					session.getAsyncRemote().sendText(message);
				}
			}
		} else {
			String receive = receiverId.substring(receiverId.length() - 1, receiverId.length());;
			System.out.println("receive = "+ receive);
			if ("0".equals(receive)) {
				Session receiverSession = resMap.get(receiverId);
				if (receiverSession != null && receiverSession.isOpen()) {
					// 發送訊息
					receiverSession.getAsyncRemote().sendText(message);
					session.getAsyncRemote().sendText(message);
				}
			}else{
				Session receiverSession = delMap.get(receiverId);
				if (receiverSession != null && receiverSession.isOpen()) {
					// 發送訊息
					receiverSession.getAsyncRemote().sendText(message);
					session.getAsyncRemote().sendText(message);
				}
			}
			
			

		}

//		System.out.println("ResToDel : 收到一則訊息"+message);
	}

	@OnClose
	public void onClose(@PathParam("id") String id, @PathParam("type") String type) {
		resMap.remove(id);
		delMap.remove(id);
		userMap.remove(id);
//        System.out.println("ResToDel : 連線關閉！"+id+type);
	}

	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println("ResToDel : 連線發生錯誤！" + t);
	}
}
