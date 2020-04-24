package com.lt;
//测试Redis的分片(搭建3台Redis)

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class TestRedisShards {
/**
 * 把多台Redis当做一台来使用，以下的对象以及解决办法API
 */
	@Test
	public void testShards() {
		//1台Redis是一个JedisShardInfo对象
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo info1 = new JedisShardInfo("123.57.223.232",6379);
		JedisShardInfo info2 = new JedisShardInfo("123.57.223.232",6380);
		JedisShardInfo info3 = new JedisShardInfo("123.57.223.232",6381);
		shards.add(info1);
		shards.add(info2);
		shards.add(info3);
		//操作分片Redis的工具类
 		ShardedJedis shardedJedis = new ShardedJedis(shards);
 		shardedJedis.set("1905", "1905班");
 		System.out.println(shardedJedis.get("1905"));
	}
}
