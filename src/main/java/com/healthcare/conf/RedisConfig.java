package com.healthcare.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.healthcare.model.entity.Admin;
import com.healthcare.model.entity.AdminPost;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Menu;
import com.healthcare.model.entity.Report;
import com.healthcare.model.entity.Role;
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
	
	@Bean
	public RedisTemplate<String, Admin> adminRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Admin> template = new RedisTemplate<String, Admin>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	
	@Bean
	public RedisTemplate<String, AdminPost> adminPostRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, AdminPost> template = new RedisTemplate<String, AdminPost>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	
	@Bean
	public RedisTemplate<String, Agency> agencyRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Agency> template = new RedisTemplate<String, Agency>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	
	@Bean
	public RedisTemplate<String, AgencyType> agencyTypeRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, AgencyType> template = new RedisTemplate<String, AgencyType>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	
	@Bean
	public RedisTemplate<String, Company> companyRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Company> template = new RedisTemplate<String, Company>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	
	@Bean
	public RedisTemplate<String, Menu> menuRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Menu> template = new RedisTemplate<String, Menu>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	
	@Bean
	public RedisTemplate<String, Report> reportRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Report> template = new RedisTemplate<String, Report>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	
	@Bean
	public RedisTemplate<String, Role> roleRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Role> template = new RedisTemplate<String, Role>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}

}