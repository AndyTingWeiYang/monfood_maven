package com.model.chatroom.dao;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	
		// key >>> 發送者會員編號:接收者(好友)會員編號
	
		//拿到連線池
		private static JedisPool pool = JedisPoolUtil.getJedisPool(); 
		
		public static List<String> getHistoryMsg(Integer senderId, Integer receiverId ) {
			// Int轉string放進key中
			String key = new StringBuilder("" + senderId).append(":").append(receiverId).toString();
			System.out.println(key);
			Jedis jedis = null;
			//從連線池拿到redis連線
			jedis = pool.getResource(); 
			//取出全部的聊天訊息
			List<String> historyData = jedis.lrange(key, 0, -1); 
			jedis.close();
			return historyData;
		}

		public static void saveChatMessage(Integer senderId, Integer receiverId, String message) {
			// 對雙方來說，都要各存著歷史聊天記錄  (發送者跟接收者是雙向關係的) >>> 在redis裡進行重複儲存
			String senderKey = new StringBuilder("" +senderId).append(":").append(receiverId).toString(); 	
			String receiverKey = new StringBuilder("" +receiverId).append(":").append(senderId).toString();
			Jedis jedis = pool.getResource();
			jedis.rpush(senderKey, message);
			jedis.rpush(receiverKey, message);

			jedis.close();
		}
		


}
