package com.healthcare.api.auth.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.api.auth.AuthTokenUtil;
import com.healthcare.api.auth.AuthUserFactory;
import com.healthcare.api.auth.model.AuthRequest;
import com.healthcare.api.auth.model.AuthResponse;
import com.healthcare.model.entity.Admin;
import com.healthcare.model.response.Response;
import com.healthcare.model.response.Response.ResultCode;
import com.healthcare.service.AdminService;

/**
 * 
 * @author orange
 *
 */
@RestController
public class AuthenticationAPI extends AbstractBasedAPI {

	// Token key required in header for every request
	private String tokenHeader = "token";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthTokenUtil tokenUtil;

	@Autowired
	private AdminService adminService;

	/**
	 * 
	 * @param authenticationRequest
	 * @return
	 * @throws AuthenticationException
	 */
	@RequestMapping(value = "/api/auth/login", method = RequestMethod.POST, produces = { "application/json" })
	public Response login(@RequestBody AuthRequest authenticationRequest) throws AuthenticationException {

		// Check user name & password
		Response checkUserResponse = adminService.login(authenticationRequest);
		if (checkUserResponse != null && checkUserResponse.getData() != null) {
			if (checkUserResponse.getResult() == ResultCode.SUCCESS) {
				// Get authen Admin
				Admin adminUser = (Admin) checkUserResponse.getData();
				// Perform the security
				final Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
								authenticationRequest.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				// Create user detail
				UserDetails userDetails = AuthUserFactory.create(adminUser);
				// Generate token for authen admin user
				final String token = tokenUtil.generateToken(userDetails);
				// Update token for current Admin login
				adminUser.setRemember_token(token);
				adminService.save(adminUser);
				// Return result
				AuthResponse authResponse = new AuthResponse(token, adminUser);
				return responseBulder.buildResponse(Response.ResultCode.SUCCESS, authResponse, "OK");

			} else {
				return checkUserResponse;
			}
		}
		return responseBulder.buildResponse(Response.ResultCode.ERROR, "", "Wrong user name or password");
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/auth/logout", method = RequestMethod.POST, produces = { "application/json" })
	public Response logout(HttpServletRequest request) {
		Response response = null;
		String authToken = request.getHeader(this.tokenHeader);
		boolean result = tokenUtil.removeToken(authToken);
		SecurityContextHolder.getContext().setAuthentication(null);
		if (result) {
			response = responseBulder.successResponse("", "Logout success! Token has been removed");
		} else {
			response = responseBulder.errorResponse("Token is not valid");
		}

		return response;
	}

	// API current user
	@RequestMapping(value = "/api/auth/profile", method = RequestMethod.GET, produces = {
			"application/json;charset=utf-8" })
	@ResponseStatus(HttpStatus.OK)
	public Response getUser(HttpServletRequest request) {
		// This function will also auto hendle expire token & reponse error
		Admin currentAuthUser = getCurrentAuthenUser(request);
		return responseBulder.successResponse(currentAuthUser, "Logout success! Token has been removed");
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/auth/token/refresh", method = RequestMethod.GET)
	public Response refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = tokenUtil.getUsernameFromToken(token);
		Admin adminUser = (Admin) adminService.getUser(username);
		if (adminUser != null) {
			UserDetails userDetails = AuthUserFactory.create(adminUser);
			if (tokenUtil.validateToken(token, userDetails)) {
				String refreshedToken = tokenUtil.refreshToken(token);
				adminUser.setRemember_token(refreshedToken);
				adminService.save(adminUser);
				// Return result
				AuthResponse authResponse = new AuthResponse(refreshedToken, adminUser);
				return responseBulder.buildResponse(Response.ResultCode.SUCCESS, authResponse, "OK");

			} else {
				return responseBulder.buildResponse(Response.ResultCode.ERROR, "", "Invalid token");

			}
		}
		return responseBulder.buildResponse(Response.ResultCode.ERROR, "", "Invalid token");

	}

}
