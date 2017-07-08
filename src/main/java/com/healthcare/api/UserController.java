package com.healthcare.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.entity.User;
import com.healthcare.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@ApiOperation(value = "save user", notes = "save a new user")
	@ApiParam(name = "user", value = "user to update", required = true)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) {
		user = userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "get user by Id", notes = "get user info by userId")
	@ApiImplicitParam(name = "id", value = "user Id", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User read(@PathVariable Long id) {
		return userService.findById(id);
	}

	@ApiOperation(value = "update user", notes = "update a user")
	@ApiParam(name = "user", value = "user to update", required = true)
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<User> update(@RequestBody User user) {
		user = userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "delete user", notes = "delete a user")
	@ApiImplicitParam(name = "id", value = "user Id", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		userService.deleteById(id);
	}
	
	@ApiOperation(value = "get all user", notes = "get all user")
	@GetMapping()
	public List<User> readAll() {
		return userService.findAll();
	}

}
