package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.AdminPost;
import com.healthcare.repository.AdminPostRepository;
import com.healthcare.service.AdminPostService;

@Service
@Transactional
public class AdminPostServiceImpl implements AdminPostService {
	@Autowired
	AdminPostRepository adminPostRepository;

	@Override
	public AdminPost save(AdminPost adminPost) {
		return adminPostRepository.save(adminPost);
	}

	@Override
	public void deleteById(Long id) {
		adminPostRepository.delete(id);
	}

	@Override
	public AdminPost findById(Long id) {
		return adminPostRepository.findOne(id);
	}
}
