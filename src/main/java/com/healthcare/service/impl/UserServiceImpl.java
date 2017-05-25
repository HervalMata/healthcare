package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.User;
import com.healthcare.repository.UserRepository;
import com.healthcare.service.UserService;

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
	public void deleteById(Long id) {
		userRepository.delete(id);
		redisTemplate.opsForHash().delete(KEY, id);
	}
}
