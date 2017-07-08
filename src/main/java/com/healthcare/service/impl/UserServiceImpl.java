package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.api.auth.model.AuthRequest;
import com.healthcare.model.entity.User;
import com.healthcare.model.response.Response;
import com.healthcare.repository.UserRepository;
import com.healthcare.service.UserService;
import com.healthcare.util.PasswordUtils;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final String KEY = User.class.getSimpleName();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Response login(AuthRequest authenticationRequest) {
		Response response = null;
		User user = null;
		try {
			user = userRepository.findByUsername(authenticationRequest.getUsername());
			if (user != null) {
				if (PasswordUtils.checkPassword(authenticationRequest.getPassword(), user.getPassword())) {
					response = new Response(Response.ResultCode.SUCCESS, user);
				} else {
					response = new Response(Response.ResultCode.INVALID_PASSWORD, null, "Invalid password");
				}
			} else {
				response = new Response(Response.ResultCode.INVALID_USERNAME, null, "Invalid username");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in AdminServiceImpl, login(), e: " + e.toString());
			response = new Response(Response.ResultCode.ERROR, null, e.getMessage());
		}

		return response;
	}

	@Override
	public Response logout(String sessionId) {
		// TODO Auto-generated method stub
		return null;
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
