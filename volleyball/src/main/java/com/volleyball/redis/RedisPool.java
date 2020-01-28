package com.volleyball.redis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author liwenxue
 * @date 创建时间：2020年1月19日 下午12:07:14
 * @version 1.0
 **/
public class RedisPool implements RedisUtils {
	
	@Autowired
	private JedisPool jedisPool;

	@Override
	public void set(String key, String value) {
		Jedis jedis = this.jedisPool.getResource();
		jedis.set(key, value);
		jedis.close();
	}

	@Override
	public String get(String key) {
		Jedis jedis = this.jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public void del(String key) {
		Jedis jedis = this.jedisPool.getResource();
		jedis.del(key);
		jedis.close();
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = this.jedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = this.jedisPool.getResource();
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	@Override
	public Map<String, String> hgetAll(String hkey) {
		Jedis jedis = this.jedisPool.getResource();
		Map<String, String> map = jedis.hgetAll(hkey);
		jedis.close();
		return map;
	}

	@Override
	public long hdel(String hkey, String... key) {
		Jedis jedis = this.jedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}

	@Override
	public void expire(String key, Integer seconds) {
		Jedis jedis = this.jedisPool.getResource();
		jedis.expire(key, seconds);
		jedis.close();
	}

	@Override
	public void set(String key, String value, Integer seconds) {
		Jedis jedis = this.jedisPool.getResource();
		jedis.setex(key, seconds, value);
		jedis.close();
	}
	
	@Override
	public long hset(String hkey, String key, String value, Integer seconds) {
		Jedis jedis = this.jedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.expire(hkey, seconds);
		jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = this.jedisPool.getResource();
		Long count = jedis.incr(key);
		jedis.close();
		return count;
	}

	@Override
	public String hmset(String key, Map<String, String> map,Integer seconds) {
		Jedis jedis = this.jedisPool.getResource();
		String result = jedis.hmset(key, map);
		jedis.expire(key, seconds);
		jedis.close();
		return result;
	}

	@Override
	public List<String> hmget(String key,String fields) {
		Jedis jedis = this.jedisPool.getResource();
		List<String> result = jedis.hmget(key, fields);
		jedis.close();
		return result;
	}

}