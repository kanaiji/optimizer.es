// package web.application.com.middleware.redis;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import web.application.com.common.config.RedisConfig;
//
//public class JedisAgent {
//	
//	private static Logger logger = LoggerFactory.getLogger(JedisAgent.class);
//
//	private static List<JedisPool> servers = new ArrayList<JedisPool>();
//	
//	
//	private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//	
//	static {
//		
////		<!-- 最大连接数 -->
//		jedisPoolConfig.setMaxTotal(Integer.valueOf(RedisConfig.readConfig("jedis.config.maxTotal")));
////		<!-- 最大空闲连接数 -->
//		jedisPoolConfig.setMaxIdle(Integer.valueOf(RedisConfig.readConfig("jedis.config.maxIdle")));
////		<!-- 获取连接时的最大等待毫秒数,小于零:阻塞不确定的时间,默认-1 -->
//		jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(RedisConfig.readConfig("jedis.config.maxWaitMillis")));
////		<!-- 在获取连接的时候检查有效性, 默认false -->
//		jedisPoolConfig.setTestOnReturn(Boolean.valueOf(RedisConfig.readConfig("jedis.config.testOnReturn")));
////      在borrow（借用）一个jedis实例时，是否提前进行validate(验证)操作，如果为true，则得到的jedis实例都是可用的
//		jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(RedisConfig.readConfig("jedis.config.testOnBorrow")));
//		
////		<!-- 连接最小空闲时间 -->
//		jedisPoolConfig.setMinEvictableIdleTimeMillis(Integer.valueOf(RedisConfig.readConfig("jedis.config.minEvictableIdleTimeMillis")));
////		<!-- 每次释放连接的最大数目 -->
//		jedisPoolConfig.setNumTestsPerEvictionRun(Integer.valueOf(RedisConfig.readConfig("jedis.config.numTestsPerEvictionRun")));
////		<!-- 释放连接的扫描间隔（毫秒） -->
//		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Long.valueOf(RedisConfig.readConfig("jedis.config.numTestsPerEvictionRun")));
////		<!-- 连接最小空闲时间 --> -->
//		jedisPoolConfig.setMinEvictableIdleTimeMillis(Long.valueOf(RedisConfig.readConfig("jedis.config.timeBetweenEvictionRunsMillis")));
////		<!-- 连接空闲多久后释放, 当空闲时间>该值 且 空闲连接>最大空闲连接数 时直接释放 -->
//		jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(Long.valueOf(RedisConfig.readConfig("jedis.config.softMinEvictableIdleTimeMillis")));
////		<!-- 在空闲时检查有效性, 默认false -->
//		jedisPoolConfig.setTestWhileIdle(Boolean.valueOf(RedisConfig.readConfig("jedis.config.testWhileIdle")));
////		<!-- 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true -->
//		jedisPoolConfig.setBlockWhenExhausted(Boolean.valueOf(RedisConfig.readConfig("jedis.config.blockWhenExhausted")));
//
//
//	}
//	
//	static {
//		//"127.0.0.1:6379;127.0.0.1:6380;127.0.0.1:6381" ;
//		String jedisAddress = RedisConfig.readConfig("jedis.agent.address") ; 
//		String[] urls = jedisAddress.split(";") ;
//		for(String url : urls){
//	        String[] serverInfo = url.split(":");
//	        String host = serverInfo[0] ;
//	        int port = Integer.parseInt(serverInfo[1]) ;
//	        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, 100000, RedisConfig.readConfig("redis.auth.password"));
//			servers.add(jedisPool) ;
//		}
//	}
//
//	// 最简单的代理实现负载均衡
//	public static Jedis newJedisInstance(Object key){
//		
//		int defmod = 0 ;
//        // 获取key的长度
//        int keyLenth = String.valueOf(key).length();
//        // 根据key长度取模
//        int mod = keyLenth % servers.size();
//        
//        try {
//        	 if(mod > servers.size()){
//             	// 取默认地址
//             	return servers.get(defmod).getResource();
//             }
//             // 根据取模结果获取地址
//             return servers.get(mod).getResource();
//		} catch (Exception e) {
//			logger.error("获取jedis 失败 ：" , e);
//			return null ;
//		}
//        
//       
//        
//                    
//    }
//
//}
