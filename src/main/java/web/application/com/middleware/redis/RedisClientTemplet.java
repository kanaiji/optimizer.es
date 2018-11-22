//package web.application.com.middleware.redis;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import redis.clients.jedis.Jedis;
//
///**
// * @描述: Redis缓存工具类.
// */
//public class RedisClientTemplet {
//
//	private static Logger logger = LoggerFactory.getLogger(RedisClientTemplet.class);
//
//	/** 默认缓存时间 */
//	private static final int DEFAULT_CACHE_SECONDS = 60 * 60 * 1;// 单位秒 设置成一个钟
//
////	@Value("${use.way.code}")
//	private static String useWayCode ;
//	
//	//默认java哨兵是关闭的
////	@Value("${java.sentinel.open.status}")
////	private static boolean javaSentinelOpenStatus = false ;
//	
//	private static String MOUDLE = "agent";
//	
//	
//	/**
//	 * 获取jedis 实例
//	 * 代理，server哨兵，java哨兵
//	 * @param key
//	 * @return
//	 */
//	public static Jedis instanceJedisFactory() {
//		
//		Jedis jedis = null ;
//		switch (useWayCode) {
//			case "Agent":
//				jedis = JedisAgent.newJedisInstance(MOUDLE) ;
//		}
//		
//		return jedis ;
//	}
//
//	/**
//	 * 释放redis资源
//	 * @param jedis
//	 */
//	public static void releaseResource(Jedis jedis) {
//		if (jedis != null) {
//			jedis.close();
//			logger.info("jedis is close success...");
////			jedisSentinelPool.destroy(); 
////			jedisSentinelPool.returnResource(jedis);
//		}
//	}
//
//	/**
//	 * 删除Redis中的所有key
//	 * 
//	 * @param jedis
//	 * @throws Exception
//	 */
//	public  static void flushAll(Object key) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			jedis.flushAll();
//		} catch (Exception e) {
//			logger.error("Cache清空失败：" , e);
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//
//	/**
//	 * 保存一个对象到Redis中(缓存过期时间:使用此工具类中的默认时间) . <br/>
//	 * 
//	 * @param key
//	 *            键 . <br/>
//	 * @param object
//	 *            缓存对象 . <br/>
//	 * @return true or false . <br/>
//	 * @throws Exception
//	 */
//	public  static Boolean save(String key, String value) {
//		return save(key, value, DEFAULT_CACHE_SECONDS);
//	}
//
//	/**
//	 * 保存一个对象到redis中并指定过期时间
//	 * 
//	 * @param key
//	 *            键 . <br/>
//	 * @param object
//	 *            缓存对象 . <br/>
//	 * @param seconds
//	 *            过期时间（单位为秒）.<br/>
//	 * @return true or false .
//	 */
//	public static Boolean save(String key, String value, int seconds) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			jedis.set(key, value);
//			// = -1 不设置有效期，永久有效
//			if(seconds != -1){
//  			   jedis.expire(key, seconds);
//			}
//			return true;
//		} catch (Exception e) {
//			logger.error("Cache保存失败：", e);
//			return false;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//	
//	
//	/**
//	 * 保存一个对象到redis中并指定过期时间
//	 * 
//	 * @param key
//	 *            键 . <br/>
//	 * @param object
//	 *            缓存对象 . <br/>
//	 * @param seconds
//	 *            过期时间（单位为秒）.<br/>
//	 * @return true or false .
//	 */
//	public static Boolean save(Object key, Object object, int seconds) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			jedis.set(SerializeUtils.serialize(key), SerializeUtils.serialize(object));
//			jedis.expire(SerializeUtils.serialize(key), seconds);
//			return true;
//		} catch (Exception e) {
//			logger.error("Cache保存失败：", e);
//			return false;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//	
//	
//	
//	/**
//	 * 设置String 类型的key.<br/>
//	 * 
//	 * @param key
//	 *            键.<br/>
//	 * @return Object .<br/>
//	 * @throws Exception
//	 */
//	public static boolean set(String key, String value) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			jedis.set(key, value);
//			return true;
//		} catch (Exception e) {
//			logger.error("Cache-String 保存失败：" , e);
//			return false;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//	
//
//	/**
//	 * 根据缓存键获取Redis缓存中的值.<br/>
//	 * 
//	 * @param key
//	 *            键.<br/>
//	 * @return Object .<br/>
//	 * @throws Exception
//	 */
//	public static String get(String key) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			String value = jedis.get(key);
//			return value == null ? "" : value;
//		} catch (Exception e) {
//			logger.error("Cache获取失败：" , e);
//			return null;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//	
//	
//	/**
//	 * 根据缓存键获取Redis缓存中的值.<br/>
//	 * 
//	 * @param key
//	 *            键.<br/>
//	 * @return Object .<br/>
//	 * @throws Exception
//	 */
//	public static Object get(Object key) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			byte[] obj = jedis.get(SerializeUtils.serialize(key));
//			return obj == null ? null : SerializeUtils.unSerialize(obj);
//		} catch (Exception e) {
//			logger.error("Cache获取失败：" , e);
//			return null;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//	
//
//	/**
//	 * 根据缓存键清除Redis缓存中的值.<br/>
//	 * 
//	 * @param key
//	 * @return
//	 * @throws Exception
//	 */
//	public static Boolean del(Object key) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			jedis.del(SerializeUtils.serialize(key));
//			return true;
//		} catch (Exception e) {
//			logger.error("Cache删除失败：" , e);
//			return false;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//
//	/**
//	 * 根据缓存键清除Redis缓存中的值.<br/>
//	 * 
//	 * @param keys
//	 * @return
//	 * @throws Exception
//	 */
//	public static Boolean del(Object... keys) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			jedis.del(SerializeUtils.serialize(keys));
//			return true;
//		} catch (Exception e) {
//			logger.error("Cache删除失败：" , e);
//			return false;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//
//	/**
//	 * 
//	 * @param key
//	 * @param seconds
//	 *            超时时间（单位为秒）
//	 * @return
//	 */
//	public static Boolean expire(Object key, int seconds) {
//
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			jedis.expire(SerializeUtils.serialize(key), seconds);
//			return true;
//		} catch (Exception e) {
//			logger.error("Cache设置超时时间失败：" , e);
//			return false;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//
//	/**
//	 * 添加一个内容到指定key的hash中
//	 * 
//	 * @param key
//	 * @param field
//	 * @param value
//	 * @return
//	 */
//	public static Boolean addHash(String key, String field, String value) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			jedis.hset(key, field, value); 
//			return true;
//		} catch (Exception e) {
//			logger.error("Cache保存失败：" , e);
//			return false;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//
//	/**
//	 * 从指定hash中拿一个对象
//	 * 
//	 * @param key
//	 * @param field
//	 * @return
//	 */
//	public static String getHash(String key, String field) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			String value = jedis.hget(key, field);
//			return value;
//		} catch (Exception e) {
//			logger.error("Cache读取失败：" , e);
//			return null;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//
//	/**
//	 * 从hash中删除指定filed的值
//	 * 
//	 * @param key
//	 * @param field
//	 * @return
//	 */
//	public static Boolean delHash(Object key, Object field) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			long result = jedis.hdel(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
//			return result == 1 ? true : false;
//		} catch (Exception e) {
//			logger.error("Cache删除失败：" , e);
//			return null;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//	
//	
//	/**
//	 * 验证该值，在hash中是否存在。
//	 * 
//	 * @param key
//	 * @param field
//	 * @return
//	 */
//	public static Boolean existsHash(Object key, Object field) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			Boolean result = jedis.hexists(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
//			return result  ;
//		} catch (Exception e) {
//			logger.error("Cache验证hash 是否存在该值失败：" , e);
//			return null;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//	
//
//	/**
//	 * 拿到缓存中所有符合pattern的key
//	 * 
//	 * @param pattern
//	 * @return
//	 */
//	public static Set<byte[]> keys(String pattern) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			Set<byte[]> allKey = jedis.keys(("*" + pattern + "*").getBytes());
//			return allKey;
//		} catch (Exception e) {
//			logger.error("Cache获取失败：" , e);
//			return new HashSet<byte[]>();
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//
//	/**
//	 * 获得hash中的所有key value
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public static Map<byte[], byte[]> getAllHash(Object key) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			Map<byte[], byte[]> map = jedis.hgetAll(SerializeUtils.serialize(key));
//			return map;
//		} catch (Exception e) {
//			logger.error("Cache获取失败：" , e);
//			return null;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//	
//	/**
//	 * 获得hash中的所有 values
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public static List<String> getAllValues(Object key) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			List<String> list = jedis.hvals(String.valueOf(key));
//			return list;
//		} catch (Exception e) {
//			logger.error("Cache获取失败：" , e);
//			return null;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//	
//	/**
//	 * 获得hash中的所有 key
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public static Set<String> getAllKeys(Object key) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			Set<String> list = jedis.hkeys(String.valueOf(key));
//			return list;
//		} catch (Exception e) {
//			logger.error("Cache获取失败：" , e);
//			return null;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//
//	/**
//	 * 判断一个key是否存在
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public static Boolean exists(String key) {
//		
//		Boolean result = false;
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			result = jedis.exists(key);
//			return result;
//		} catch (Exception e) {
//			logger.error("Cache获取失败：" , e);
//			return false;
//		} finally{
//			releaseResource(jedis);
//		}
//	}
//	
//	/**
//	 * 获取Redis中的key 的 ttl
//	 * 
//	 * @param jedis
//	 * @throws Exception
//	 */
//	public static long getTtl(String key ) {
//		
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			return jedis.ttl(key) ;
//		} catch (Exception e) {
//			logger.error("Cache获取ttl失败：" , e);
//		} finally{
//			releaseResource(jedis);
//		}
//		return  0 ;
//	}
//	
//	
//	
//	private static final String LOCK_NODE ="LOCK";
//	private static final String LOCK_SUCCESS = "OK";
//	private static final String SET_IF_NOT_EXIST = "NX";
//	private static final String SET_WITH_EXPIRE_TIME = "PX";
//
//	/**
//	 * 尝试获取分布式锁
//	 * 
//	 * @param jedis      Redis客户端
//	 * @param requestId  请求标识（随机数）
//	 * @param expireTime 超期时间
//	 * @return 是否获取成功
//	 */
//	public static boolean tryGetDistributedLock(String requestId, int expireTime) {
//
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			String result = jedis.set(LOCK_NODE, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//			
//			if (LOCK_SUCCESS.equals(result)) {
//				return true;
//			}
//			return false;
//		} finally {
//			releaseResource(jedis);
//		}
//		
//		
//	}
//
//	private static final Long RELEASE_SUCCESS = 1L;
//
//	/**
//	 * 释放分布式锁
//	 * 
//	 * @param jedis     Redis客户端
//	 * @param requestId 请求标识（随机数）
//	 * @return 是否释放成功
//	 */
//	public static boolean releaseDistributedLock(String requestId) {
//
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//			Object result = jedis.eval(script, Collections.singletonList(LOCK_NODE), Collections.singletonList(requestId));
//			
//			if (RELEASE_SUCCESS.equals(result)) {
//				return true;
//			}
//			return false;
//		} finally {
//			releaseResource(jedis);
//		}
//	}
//
//	/**
//	 * 获取Incr
//	 * 
//	 * @param key
//	 * @param field
//	 * @return
//	 */
//	public static String getIncr(String key) {
//
//		Jedis jedis = null;
//		try {
//			jedis = instanceJedisFactory();
//			String result = String.valueOf(jedis.incr(key));
//			return result;
//		} catch (Exception e) {
//			logger.error("自增1失败 是否存在该值失败：", e);
//			return null;
//		} finally {
//			releaseResource(jedis);
//		}
//	}
//	
//	
//	
//	
//	
//	
//	
//
//}
