package com.taotao.test.jedis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.content.jedis.JedisClient;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestJedis {
	//测试单机版redis
	public void testJedis(){
//		Jedis jedis = new Jedis("192.168.1.110", 6379);
		JedisPool jedisPool = new JedisPool("192.168.1.110", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.set("keyPool", "keyPool");
		System.out.println(jedis.get("keyPool"));
		jedis.close();
		jedisPool.close();
	}
	//测试集群版
	public void testJedisCluster(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.110", 7001));
		nodes.add(new HostAndPort("192.168.1.110", 7002));
		nodes.add(new HostAndPort("192.168.1.110", 7003));
		nodes.add(new HostAndPort("192.168.1.110", 7004));
		nodes.add(new HostAndPort("192.168.1.110", 7005));
		nodes.add(new HostAndPort("192.168.1.110", 7006));
		
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("key", "test");
		System.out.println(cluster.get("key"));
		cluster.close();
	}
	
	public void testJedisdanji(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		JedisClient bean = context.getBean(JedisClient.class);
		bean.set("WY", "爱你哟");
		System.out.println(bean.get("WY"));
	}
}
