package com.healthcare.service;

import com.healthcare.model.entity.User;

public interface UserService {
	User save(User user);

	User findById(Long id);

	void deleteById(Long id);
}
