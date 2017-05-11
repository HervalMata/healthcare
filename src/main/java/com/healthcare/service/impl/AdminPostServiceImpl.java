package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.AdminPost;
import com.healthcare.repository.AdminPostRepository;
import com.healthcare.service.AdminPostService;

@Service
@Transactional
public class AdminPostServiceImpl implements AdminPostService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	AdminPostRepository adminPostRepository;

	@Autowired
	public AdminPostServiceImpl(AdminPostRepository adminPostRepository) {
		this.adminPostRepository = adminPostRepository;
	}

	@Override
	public AdminPost save(AdminPost adminPost) {
		return adminPostRepository.save(adminPost);
	}

	@Override
	public void delete(Long id) {
		adminPostRepository.delete(id);

	}

	@Override
	public AdminPost get(Long id) {
		return adminPostRepository.getOne(id);
	}

}
