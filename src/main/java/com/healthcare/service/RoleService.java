package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Role;

public interface RoleService {
	Role save(Role role);

	void delete(Long id);

	Role get(Long id);

	Role findByLevel(long level);

	List<Role> findByStatus(long status);

	List<Role> findByIdAndStatus(long id, long status);
}
