package com.healthcare.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Admin;
import com.healthcare.repository.AdminRepository;

/**
 * 
 * @author orange
 *
 */

@Service
public class AuthUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Admin userAdmin = adminRepository.findByUsername(userName);
		if (userAdmin == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userName));
		} else {
			return AuthUserFactory.create(userAdmin);
		}
	}
}
