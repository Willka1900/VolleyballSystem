package volleyball;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @author liwenxue
 * @date 创建时间：2020年1月6日 下午9:30:02
 * @version 1.0
 **/
public class JedisTest {
	public static void main(String[] args) {
		// 测试连接redis
		// new RedisJava().connectRedis();
		// 测试Redis Java String(字符串) 实例
		// new RedisJava02().redisStringJava();
		// 测试Redis Java List(列表) 实例
		// new RedisJava02().redisListJava();
		// 测试Redis Java Keys 实例
		// new RedisJava02().redisKeyJava();
			new RedisJava02().redisHashJava();
	}
}

class RedisJava {
	public Jedis connectRedis() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		// 查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());
		return jedis;
	}
}

class RedisJava02 {
	public static Jedis jedis;

	/**
	 * Redis Java String(字符串) 实例
	 */
	public void redisStringJava() {
		// 连接数据库
		jedis = new RedisJava().connectRedis();

		// 设置 redis 字符串数据
		jedis.set("jedis_string", "my name is summer!");

		// 获取存储的数据并输出
		System.out.println("redis 存储的字符串为: " + jedis.get("jedis_string"));

	}

	/**
	 * Redis Java List(列表) 实例
	 */
	public void redisListJava() {
		// 连接数据库
		jedis = new RedisJava().connectRedis();

		// 存储数据到列表中
		jedis.lpush("site-list", "Runoob");
		jedis.lpush("site-list", "Google");
		jedis.lpush("site-list", "Taobao");
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("site-list", 0, 2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("列表项为: " + list.get(i));
		}
	}

	/**
	 * Redis Java Keys 实例
	 */
	public void redisKeyJava() {
		// 连接数据库
		jedis = new RedisJava().connectRedis();

		// 获取数据并输出
		Set<String> keys = jedis.keys("*");
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key);
		}
	}

	/**
	 * Redis Java hash 实例
	 */
	public void redisHashJava() {
		// 连接数据库
		jedis = new RedisJava().connectRedis();

		// 获取数据并输出
		jedis.hset("favorite", "color", "black");
		jedis.hset("favorite", "food", "ice_cream");
		jedis.hset("favorite", "animal", "dog");
		Map<String, String> map = new HashMap<String, String>();
		map = jedis.hgetAll("favorite");
		for (Map.Entry<String, String> m : map.entrySet()) {
			System.out.println("Key:" + m.getKey() + "; value:" + m.getValue());
		}
	}

}
