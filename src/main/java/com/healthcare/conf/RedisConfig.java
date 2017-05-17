package com.healthcare.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.healthcare.model.entity.User1;
import com.healthcare.util.RedisObjectSerializer;
/**
 * 
 * @author zhao
 *
 */
@Configuration
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String localhost;
	@Value("${spring.redis.port}")
	private int port;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(localhost);
		factory.setPort(port);
		return factory;
	}

	@Bean
	public RedisTemplate<String, User1> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, User1> template = new RedisTemplate<String, User1>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}

}