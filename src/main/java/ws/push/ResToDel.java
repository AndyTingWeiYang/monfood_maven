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
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();
	private static Set<String> resSet = new HashSet<String>();
	private static Set<String> delSet = new HashSet<String>();
	private static Set<String> userSet = new HashSet<String>();
	//連線進來存type 
	
	@OnOpen                  //使用者資訊                                                 使用者通訊
	public void onOpen(@PathParam("id") String id, @PathParam("type") String type,Session session ) {
		// 連線建立 蒐集連線 // 發送訊息靠連線

		if(!("null".equals(id))) {
			sessionsMap.put(id+type , session);			
		}
		
		Set<String> allUser = sessionsMap.keySet();
		State stateMessage = new State("open", id, allUser);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		System.out.println("id: "+id);
		System.out.println("type: "+type);
		//存入各自set (除錯用)
		if (!("null".equals(id))) {
			if("0".equals(type)) {
				resSet.add(id);
			}else if("1".equals(type)) {
				delSet.add(id);				
			}else {
				userSet.add(id);
			}						
		}		
		
		for (Session eachSession : sessions) {
			if (eachSession.isOpen()) {
				eachSession.getAsyncRemote().sendText(stateMessageJson); //上線通知		   			
			}
		}
		
		
		System.out.println("ResToDel : 與客戶端建立連線了！");
		System.out.println("餐廳Set : " +gson.toJson(resSet));
		System.out.println("外送員Set : "+gson.toJson(delSet));
		System.out.println("使用者Set : "+gson.toJson(userSet));
		System.out.println("allUser: "+allUser);

	}
	
	// 收到資料 發生事件
	@OnMessage                // 發送者的連線
	public void  onMessage(Session session, String message) throws IOException, InterruptedException {
		PushInfo pushInfo = gson.fromJson(message, PushInfo.class);
		String receiverId = pushInfo.getReceiver();
		System.out.println("receiverId = "+receiverId);
		
		Session receiverSession = sessionsMap.get(receiverId);
		if (receiverSession != null && receiverSession.isOpen()) {
			// 發送訊息
			
			receiverSession.getAsyncRemote().sendText(message);
			session.getAsyncRemote().sendText(message);
		}
		
		System.out.println("ResToDel : 收到一則訊息"+message);
	}


    @OnClose
    public void onClose(@PathParam("id") String id, @PathParam("type") String type) {
    	sessionsMap.remove(id+type);
    	resSet.remove(id);
    	delSet.remove(id);
    	userSet.remove(id);
        System.out.println("ResToDel : 連線關閉！");
    }
  
    @OnError
    public void onError(Session session, Throwable t) {
        System.out.println("ResToDel : 連線發生錯誤！");
    }
}
