package com.model.cart.dao;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	
	private static JedisPool pool = null;

	private JedisPoolUtil() {
	}

	// redis連線池
	public static JedisPool getJedisPool() {
		if (pool == null) {
			synchronized (JedisPoolUtil.class) {
				if (pool == null) {
					JedisPoolConfig config = new JedisPoolConfig();
					//設定最大連線數
					config.setMaxTotal(8);
					//設定最大空閒數
					config.setMaxIdle(8);
					//設定超時時間
					config.setMaxWaitMillis(10000);
					//初始化連線池
					pool = new JedisPool(config, "localhost", 6379);
				}
			}
		}
		return pool;
	}

	// 可在ServletContextListener的contextDestroyed裡呼叫此方法註銷Redis連線池
	public static void shutdownJedisPool() {
		if (pool != null)
			pool.destroy();
	}

}
