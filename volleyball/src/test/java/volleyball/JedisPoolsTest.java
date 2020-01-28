package volleyball;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * @author liwenxue
 * @date 创建时间：2020年1月19日 下午2:57:41
 * @version 1.0
 **/
/*无效测试
 *spring扫描不到此包 
 **/
public class JedisPoolsTest {

	public static void main(String[] args) {
		//创建连接池对象
        JedisPool jedispool = new JedisPool("127.0.0.1",6379);
        //从连接池中获取一个连接
        Jedis jedis = jedispool.getResource(); 
        //使用jedis操作redis
        jedis.set("test", "my forst jedis");
        String str = jedis.get("test");
        System.out.println(str);
        //使用完毕 ，关闭连接，连接池回收资源
        jedis.close();
        //关闭连接池
        jedispool.close();
	}
}
