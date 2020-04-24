package com.lt.redisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

@Service
public class RedisService {
@Autowired(required = false)//表示调用时才注入
private JedisSentinelPool jedisSentinelPool;
public String get(String key) {
	Jedis jedis = jedisSentinelPool.getResource();//开启连接
	String string = jedis.get(key);
	jedis.close();
	return string;
}
public void set(String key,String value) {
	Jedis jedis = jedisSentinelPool.getResource();
	jedis.set(key, value);
	jedis.close();
}
public void setex(String key,int seconds,String value) {
	Jedis jedis = jedisSentinelPool.getResource();
	jedis.setex(key, seconds, value);
	jedis.close();
}
}
