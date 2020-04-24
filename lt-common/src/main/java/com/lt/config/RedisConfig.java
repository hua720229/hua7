package com.lt.config;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
//整合Redis标明配置类
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
	//配置Redis的集群
	@Value("${redis.nodes}")
	private String jediscluster;
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> jedis = new HashSet<>();
		String string [] = jediscluster.split(",");
		for (String nodes : string) {
			String host=nodes.split(":")[0];
			int port = Integer.parseInt(nodes.split(":")[1]);
			jedis.add(new HostAndPort(host,port));
		}
		return new JedisCluster(jedis);
	}
	//操作哨兵的高可用
/*	@Value("${redis.sentinels}")
	private String jedisSentinelNodes;
	@Value("${redis.sentinel.masterName}")
	private String masterName;
	@Bean
	public JedisSentinelPool jedisSentinelPool() {
		Set<String> sentinels = new HashSet<>();
		sentinels.add(jedisSentinelNodes);
		return new JedisSentinelPool(masterName, sentinels);
	}*/
	//操作redis的分片
 /*   @Value("${jedis.nodes}")
	private String jedisNodes;
    @Bean
    public ShardedJedis shardedJedis() {
    	List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
    	String string[]=jedisNodes.split(",");
    	for (String nodes : string) {
			String host = nodes.split(":")[0];
			int port = Integer.parseInt(nodes.split(":")[1]);
			JedisShardInfo info = new JedisShardInfo(host,port);
			shards.add(info);
		}
    	return new ShardedJedis(shards);
    }*/
//单台redis的架构
//@Value("${jedis.host}")//ip
//private String  host;
//@Value("${jedis.port}")//端口
//private Integer port;
//@Bean//表示实例化
//public Jedis jedis() {
//	return new Jedis(host, port);
//}
}
