package com.healthcare.api;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.healthcare.model.entity.User;
import com.healthcare.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@ApiOperation(value = "get user by Id", notes = "get user info by userId")
	@ApiImplicitParam(name = "id", value = "user Id", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable Long id) {
		logger.info("===id==" + id);
		return "id: " + id;
	}

	@ApiOperation(value = "save user", notes = "save a new user")
	@ApiImplicitParam(name = "id", value = "user Id", required = true, dataType = "Long")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody User user) {
		user = userService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
