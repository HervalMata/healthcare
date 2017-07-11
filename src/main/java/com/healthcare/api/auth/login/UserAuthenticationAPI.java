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
import com.healthcare.api.auth.UtilsResponse;
import com.healthcare.api.auth.model.AuthRequest;
import com.healthcare.api.auth.model.AuthUserResponse;
import com.healthcare.model.entity.User;
import com.healthcare.model.response.Response;
import com.healthcare.model.response.Response.ResultCode;
import com.healthcare.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author orange
 *
 */
@RestController(value = "UserLoginRestAPI")
@RequestMapping(value = "/api/user/auth")
public class UserAuthenticationAPI extends AbstractBasedAPI {

	// Token key required in header for every request
	private String tokenHeader = "token";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthTokenUtil tokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	public UserAuthenticationAPI(AuthenticationManager authenticationManager, AuthTokenUtil tokenUtil,
			UserService userService, UtilsResponse responseBuilder) {
		super(responseBuilder, userService, tokenUtil);
		this.authenticationManager = authenticationManager;
		this.tokenUtil = tokenUtil;
		this.userService = userService;
	}

	/**
	 * 
	 * @param authenticationRequest
	 * @return
	 * @throws AuthenticationException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "login user", notes = "login user")
	@ApiParam(name = "login", value = "login user", required = true)
	public Response login(@RequestBody AuthRequest authenticationRequest) throws AuthenticationException {

		// Check user name & password
		Response checkUserResponse = userService.login(authenticationRequest);
		if (checkUserResponse != null && checkUserResponse.getData() != null) {
			if (checkUserResponse.getResult() == ResultCode.SUCCESS) {
				// Get authen Admin
				User user = (User) checkUserResponse.getData();
				// Perform the security
				final Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
								authenticationRequest.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				// Create user detail
				UserDetails userDetails = AuthUserFactory.create(user);
				// Generate token for authen user
				final String token = tokenUtil.generateToken(userDetails);
				// Update token for current user login
				user.setRememberToken(token);
				userService.save(user);
				// Return result
				AuthUserResponse authResponse = new AuthUserResponse(token, user);
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
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "logout user", notes = "logout user")
	@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "header")
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
	@RequestMapping(value = "/profile", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	@ApiOperation(value = "user profile", notes = "user profile")
	@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "header")
	@ResponseStatus(HttpStatus.OK)
	public Response getUser(HttpServletRequest request) {
		// This function will also auto hendle expire token & reponse error
		User currentAuthUser = getCurrentAuthenUser(request);
		return responseBulder.successResponse(currentAuthUser, "Logout success! Token has been removed");
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
	@ApiOperation(value = "refresh token", notes = "refresh token")
	@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "header")
	public Response refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = tokenUtil.getUsernameFromToken(token);
		User user = userService.getUser(username);
		if (user != null) {
			UserDetails userDetails = AuthUserFactory.create(user);
			if (tokenUtil.validateToken(token, userDetails)) {
				String refreshedToken = tokenUtil.refreshToken(token);
				user.setRememberToken(refreshedToken);
				userService.save(user);

				// Return result
				AuthUserResponse authResponse = new AuthUserResponse(refreshedToken, user);
				return responseBulder.buildResponse(Response.ResultCode.SUCCESS, authResponse, "OK");

			} else {
				return responseBulder.buildResponse(Response.ResultCode.ERROR, "", "Invalid token");

			}
		}
		return responseBulder.buildResponse(Response.ResultCode.ERROR, "", "Invalid token");

	}
}
