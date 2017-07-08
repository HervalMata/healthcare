package com.healthcare.service;

import java.util.List;

import com.healthcare.api.auth.model.AuthRequest;
import com.healthcare.model.entity.User;
import com.healthcare.model.response.Response;

public interface UserService extends IService<User> {
	User getUser(String username);

	Response login(AuthRequest authenticationRequest);

	Response logout(String sessionId);

	List<User> findAll();
}
