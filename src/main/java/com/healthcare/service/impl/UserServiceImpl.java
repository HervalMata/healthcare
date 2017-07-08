package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.User;
import com.healthcare.repository.UserRepository;
import com.healthcare.service.UserService;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final String KEY = User.class.getSimpleName();
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Override
	public User save(User user) {
		user = userRepository.save(user);
		redisTemplate.opsForHash().put(KEY, user.getId(), user);
		return user;
	}

	@Override
	public User findById(Long id) {
		if (redisTemplate.opsForHash().hasKey(KEY, id)) {
			return (User) redisTemplate.opsForHash().get(KEY, id);
		}
		return userRepository.findOne(id);
	}

	@Override
	public Long deleteById(Long id) {
		userRepository.delete(id);
		return redisTemplate.opsForHash().delete(KEY, id);
	}
	
	@Override
	public List<User> findAll() {
		Map<Object, Object> userMap = redisTemplate.opsForHash().entries(KEY);
		List<User> userList = Collections.arrayToList(userMap.values().toArray());
		if (userMap.isEmpty())
			userList = userRepository.findAll();
		return userList;
	}
}
