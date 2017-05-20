package com.healthcare.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Role;
import com.healthcare.repository.RoleRepository;
import com.healthcare.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private RedisTemplate<String, Role> roleRedisTemplate;
	
	private static String ROLE_KEY = "Role";

	@Override
	public Role save(Role role) {
		role = roleRepository.save(role);
		roleRedisTemplate.opsForHash().put(ROLE_KEY, role.getId(), role);
		return role;
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.delete(id);
		roleRedisTemplate.opsForHash().delete(ROLE_KEY, id);
	}

	@Override
	public Role findById(Long id) {
		Role role = (Role) roleRedisTemplate.opsForHash().get(ROLE_KEY, id);
		if (role == null)
			role = roleRepository.findOne(id);
		return role;
	}

	@Override
	public Role findByLevel(long level) {
		return roleRepository.findByLevel(level);
	}

	@Override
	public List<Role> findByStatus(long status) {
		return roleRepository.findByStatus(status);
	}
}
