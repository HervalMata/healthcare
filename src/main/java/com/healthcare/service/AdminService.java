package com.healthcare.service;

import com.healthcare.api.auth.model.AuthRequest;
import com.healthcare.model.entity.Admin;
import com.healthcare.model.response.Response;

public interface AdminService extends IService<Admin> {

	Admin getUser(String username);

	Response login(AuthRequest authenticationRequest);

	Response logout(String sessionId);
}
