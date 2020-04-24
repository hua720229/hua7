package com.lt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//测试Redis(String类型的操作方式)
//配置Linux的IP和Redis的端口号来操作Redis
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lt.pojo.ItemDesc;
import redis.clients.jedis.Jedis;
public class TestRedis {
@Test
public void redis() {
	Jedis jedis = new Jedis("123.57.223.232", 6379);
	jedis.set("110","110爸爸");
	jedis.expire("110", 10); //设置key的生效时间
	System.out.println(jedis.get("110"));
}
       //设定超时时间的两种方法
       //涉及到分布式锁的问题
@Test
public void redisbaba() throws InterruptedException {
	Jedis jedis = new Jedis("123.57.223.232", 6379);
	jedis.setex("1100", 10, "1100爸爸");//以上两个方法的组合赋值+超时
	System.out.println(jedis.get("1100"));
	Thread.sleep(3000);
	//当key存在时操作失败，当key不存在时操作成功setnx是redis加锁的时候用的(类似于加锁)
	Long long1=jedis.setnx("1100", "1000");
	System.out.println("输出数据:"+long1+jedis.get("1100"));
}
@Test
public void itemDesc() throws IOException {
	ItemDesc itemDesc = new ItemDesc();
	itemDesc.setItemId(1000L);
	itemDesc.setItemDesc("熊大");
	ObjectMapper objectMapper = new ObjectMapper();
	//测试把对象转化为json字符串的方法 必须得有get set方法否则程序会有bug
	String string=objectMapper.writeValueAsString(itemDesc);
     System.out.println(string);
     //json转化对象的方法
    System.out.println(objectMapper.readValue(string, ItemDesc.class));
}
       //实现List集合与json的转换
@SuppressWarnings("unchecked")//压制警告，
@Test
public void itemB() throws IOException {
	ItemDesc itemDesc = new ItemDesc();
	itemDesc.setItemId(1000L);
	itemDesc.setItemDesc("熊大");
	ItemDesc itemDesc1 = new ItemDesc();
	itemDesc.setItemId(1000L);
	itemDesc.setItemDesc("熊二");
	List<ItemDesc>desc = new ArrayList<>();
	desc.add(itemDesc);
	desc.add(itemDesc1);
	ObjectMapper mapper = new ObjectMapper();
	String json = mapper.writeValueAsString(desc);
	System.out.println("集合转化为JSON"+json); //转化为json串
	//存入redis中
	Jedis jedis = new Jedis("123.57.223.232", 6379);
	jedis.set("自己起的名字aa", json);
	//获取redis中的数据    
	String string = jedis.get("自己起的名字aa");
	//将集合中的json转化为对象对象得类型getclass
	List<ItemDesc> read = mapper.readValue(string, desc.getClass());
	System.out.println(read);	
}
}
