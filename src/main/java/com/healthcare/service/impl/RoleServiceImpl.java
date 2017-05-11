package com.healthcare.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Role;
import com.healthcare.repository.RoleRepository;
import com.healthcare.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Long id) {
		roleRepository.delete(id);

	}

	@Override
	public Role get(Long id) {
		return roleRepository.getOne(id);
	}

	@Override
	public Role findByLevel(long level) {
		return roleRepository.findByLevel(level);
	}

	@Override
	public List<Role> findByStatus(long status) {
		return roleRepository.findByStatus(status);
	}

	@Override
	public List<Role> findByIdAndStatus(long id, long status) {
		return roleRepository.findByIdAndStatus(id, status);
	}

}
