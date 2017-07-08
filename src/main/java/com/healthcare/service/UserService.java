package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.User;

public interface UserService extends IService<User> {

	List<User> findAll();
	
}
