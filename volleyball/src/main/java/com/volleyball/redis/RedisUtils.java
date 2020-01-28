package com.volleyball.redis;

import java.util.List;
import java.util.Map;

/** 
 * @author liwenxue
 * @date 创建时间：2020年1月19日 下午12:08:33 
 * @version 1.0 
 **/
/**
 * 操作Redis的方法接口
 */
public interface RedisUtils {

	/**
	 * 插入String型缓存
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value);

	/**
	 * 根据key查询String型缓存
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key);

	/**
	 * 根据key删除String型缓存
	 * 
	 * @param key
	 */
	public void del(String key);

	/**
	 * 哈希表hkey中的域key的值设为value
	 * 
	 * @param hkey
	 * @param key
	 * @param value
	 * @return
	 */
	public long hset(String hkey, String key, String value);

	/**
	 * 根据hkey哈希表中key值查询value缓存
	 * 
	 * @param hkey
	 * @param key
	 * @return
	 */
	public String hget(String hkey, String key);

	/**
	 * 获取hkey哈希表中所有key-value缓存
	 * 
	 * @param hkey
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(String hkey);

	/**
	 * 根据hkey哈希表中key值删除缓存
	 * 
	 * @param hkey
	 * @param key
	 * @return
	 */
	public long hdel(String hkey, String... key);

	/**
	 * 插入Map<String,String>缓存,设置生存时间
	 * @param key
	 * @param map
	 * @param seconds
	 */
	public String hmset(String key, Map<String, String> map, Integer seconds);
	
	/**
	 * 根据key查询Map<String,String>值缓存
	 * @param key
	 */
	public List<String> hmget(String key,String fields);

	/**
	 * 根据key设置生存时间
	 * 
	 * @param key
	 * @param seconds
	 */
	public void expire(String key, Integer seconds);

	/**
	 * 保存并设置生存时间
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void set(String key, String value, Integer seconds);

	/**
	 * 保存并设置生存时间
	 * 
	 * @param hkey
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public long hset(String hkey, String key, String value, Integer seconds);

	/**
	 * value加一
	 * 
	 * @param key
	 * @return
	 */
	public Long incr(String key);

}