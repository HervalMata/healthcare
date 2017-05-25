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
import com.healthcare.model.entity.Admin;
import com.healthcare.model.response.Response;
import com.healthcare.repository.AdminRepository;
import com.healthcare.service.AdminService;
import com.healthcare.util.PasswordUtils;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	private static final String KEY = Admin.class.getSimpleName();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	private RedisTemplate<String, Admin> adminRedisTemplate;

	@Override
	public Admin getUser(String username) {
		// Admin admin = (Admin) adminRedisTemplate.opsForHash().get(ADMIN_KEY,
		// id);
		return adminRepository.findByUsername(username);
	}

	@Override
	public Response logout(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin save(Admin admin) {
		admin = adminRepository.save(admin);
		adminRedisTemplate.opsForHash().put(KEY, admin.getId(), admin);
		return admin;
	}

	@Override
	public Response login(AuthRequest authenticationRequest) {
		Response response = null;
		Admin admin = null;
		try {
			admin = adminRepository.findByUsername(authenticationRequest.getUsername());
			if (admin != null) {
				if (PasswordUtils.checkPassword(authenticationRequest.getPassword(), admin.getPassword())) {
					response = new Response(Response.ResultCode.SUCCESS, admin);
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
	public void deleteById(Long id) {
		adminRepository.delete(id);
		adminRedisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public Admin findById(Long id) {
		if (adminRedisTemplate.opsForHash().hasKey(KEY, id))
			return (Admin) adminRedisTemplate.opsForHash().get(KEY, id);
		return adminRepository.findOne(id);
	}

	@Override
	public List<Admin> findAll() {
		Map<Object, Object> adminMap = adminRedisTemplate.opsForHash().entries(KEY);
		List<Admin> adminList = Collections.arrayToList(adminMap.values().toArray());
		if (adminMap.isEmpty())
			adminList = adminRepository.findAll();
		return adminList;
	}
}
