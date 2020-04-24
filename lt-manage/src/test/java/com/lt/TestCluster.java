package com.lt;
//测试Redis集群的API
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
public class TestCluster {
	@Test
	public void testCluster() {
	Set<HostAndPort> nodes = new HashSet<>();
//以下两种方法都可以，挑方便的来
//	HostAndPort port0 = new HostAndPort("123.57.223.232", 7000);
//	HostAndPort port1 = new HostAndPort("123.57.223.232", 7001);
//	HostAndPort port2 = new HostAndPort("123.57.223.232", 7002);
//	HostAndPort port3 = new HostAndPort("123.57.223.232", 7003);
//	HostAndPort port4 = new HostAndPort("123.57.223.232", 7004);
//	HostAndPort port5 = new HostAndPort("123.57.223.232", 7005);
//	nodes.add(port0);
//	nodes.add(port1);
//	nodes.add(port2);
//	nodes.add(port3);
//	nodes.add(port4);
//	nodes.add(port5);
	nodes.add(new HostAndPort("123.57.223.232", 7000));
	nodes.add(new HostAndPort("123.57.223.232", 7001));
	nodes.add(new HostAndPort("123.57.223.232", 7002));
	nodes.add(new HostAndPort("123.57.223.232", 7003));
	nodes.add(new HostAndPort("123.57.223.232", 7004));
	nodes.add(new HostAndPort("123.57.223.232", 7005));
JedisCluster jedis = new JedisCluster(nodes);
jedis.set("1905", "1905班");
System.out.println(jedis.get("1905"));
}
}