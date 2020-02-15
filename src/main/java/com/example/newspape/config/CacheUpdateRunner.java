package com.example.newspape.config;

 

import java.util.Set;

 

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;

import org.springframework.data.redis.connection.RedisConnection;

import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
public class CacheUpdateRunner implements CommandLineRunner {

   

	@SuppressWarnings("rawtypes")

	@Autowired

	private RedisTemplate redisTemplate;

	

	private static final String CACHE_KEY_PREFIX = "age" ;

	@Override

	public void run(String... args) throws Exception {	

		  String pattern = CACHE_KEY_PREFIX + "*" ;

	        RedisConnection connection = redisTemplate

	                .getConnectionFactory().getConnection();

 

	        Set<byte[]> caches = connection.keys(pattern.getBytes());

	        if(!caches.isEmpty()){

	            connection.del(caches.toArray(new byte[][]{}));

	        }

 

	}
}
