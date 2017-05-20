package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.AdminPost;
import com.healthcare.repository.AdminPostRepository;
import com.healthcare.service.AdminPostService;

@Service
@Transactional
public class AdminPostServiceImpl implements AdminPostService {
	@Autowired
	AdminPostRepository adminPostRepository;

	@Autowired
	private RedisTemplate<String, AdminPost> adminPostRedisTemplate;
	
	private static String ADMINPOST_KEY = "AdminPost";

	@Override
	public AdminPost save(AdminPost adminPost) {
		adminPost = adminPostRepository.save(adminPost);
		adminPostRedisTemplate.opsForHash().put(ADMINPOST_KEY, adminPost.getId(), adminPost);
		return adminPost;
	}

	@Override
	public void deleteById(Long id) {
		adminPostRepository.delete(id);
		adminPostRedisTemplate.opsForHash().delete(ADMINPOST_KEY, id);
	}

	@Override
	public AdminPost findById(Long id) {
		AdminPost adminPost = (AdminPost) adminPostRedisTemplate.opsForHash().get(ADMINPOST_KEY, id);
		if (adminPost == null)
			adminPost = adminPostRepository.findOne(id);
		return adminPost;
	}
}
