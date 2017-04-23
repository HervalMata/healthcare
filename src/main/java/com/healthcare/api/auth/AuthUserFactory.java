package com.healthcare.api.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.healthcare.api.auth.model.AuthUser;
import com.healthcare.model.entity.Admin;
import com.healthcare.model.entity.AdminPermission;

/**
 * 
 * @author orange
 *
 */
public final class AuthUserFactory {
	private AuthUserFactory() {
	}

	public static AuthUser create(Admin user) {
		return new AuthUser(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(),
				user.getPassword(), mapToGrantedAuthorities(user.getAdminPermission()), (user.getStatus() == 1));
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(AdminPermission permission) {
		List<GrantedAuthority> authoritys = new ArrayList<GrantedAuthority>();
		authoritys.add(new SimpleGrantedAuthority(permission.getLevelName()));
		return authoritys;
	}
}
