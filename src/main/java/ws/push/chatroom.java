package ws.push;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint("/chatroom/{id}/{type}")
public class chatroom {
	Gson gson = new Gson();
	private static Map<String, Session> allMap = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpen(@PathParam("id") String id, @PathParam("type") String type, Session session) {
		allMap.put(id + type, session);
//		System.out.println("chatroom : 與客戶端建立連線了！");
	}

	@OnMessage // 發送者的連線
	public void onMessage(@PathParam("id") String id, @PathParam("type") String type, Session session, String message)
			throws IOException, InterruptedException {
		PushInfo pushInfo = gson.fromJson(message, PushInfo.class);
		String receiverId = pushInfo.getReceiver();

		Session receiverSession = allMap.get(receiverId);
		if (receiverSession != null && receiverSession.isOpen()) {
			// 發送訊息
			receiverSession.getAsyncRemote().sendText(message);
			session.getAsyncRemote().sendText(message);
		}

//		System.out.println("chatroom : 收到一則訊息"+message);

	}

	@OnClose
	public void onClose(@PathParam("id") String id, @PathParam("type") String type) {
		allMap.remove(id);
//        System.out.println("chatroom : 連線關閉！"+id+type);
	}

	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println("chatroom : 連線發生錯誤！" + t);
	}

}
