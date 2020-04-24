package com.lt;
//测试哨兵机制
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
public class TestSentinel {
//masterName 代表主机的变量名称
    @Test
	public void test() {
	Set<String> sentinel = new HashSet<>();
	sentinel.add("123.57.223.232:26379");
	JedisSentinelPool sentinelPool=new JedisSentinelPool("mymaster",sentinel); 
	Jedis jedis = sentinelPool.getResource();
	jedis.set("baba", "laobaba");
	System.out.println(jedis.get("baba"));
	jedis.close();//关闭连接
	
}
}
