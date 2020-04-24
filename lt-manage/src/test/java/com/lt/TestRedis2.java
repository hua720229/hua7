package com.lt;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;


public class TestRedis2 {
  //测试hashi/list/事务控制
	@Test
	public void  testRedis() {
	Jedis jedis=new Jedis("123.57.223.232", 6379);
	jedis.hset("user1","id","100");
	jedis.hset("user1", "name", "Tomcat服务器");
	String a =jedis.hget("user1", "id");
	System.out.println(a); //指定获取的Key
	System.out.println(jedis.hgetAll("user1"));//获取user1里所有的属性值
	
 }
	@Test  //测试list集合在redis里的消息队列
	public void testlist() {
		Jedis jedis=new Jedis("123.57.223.232", 6379);
		jedis.lpush("list", "1","2","3","4","5");
		System.out.println(jedis.rpop("list"));
	}
	//redis的事务控制
	public void testT() {
		Jedis jedis=new Jedis("123.57.223.232", 6379);
		Transaction b=jedis.multi();//开启redis事务 自行查看redis命令
		try {
			b.set("fff", "ffff");
			b.set("hhh", null);
			b.exec();
		} catch (Exception e) {
			b.discard();
		}
	}
}