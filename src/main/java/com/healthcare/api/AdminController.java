package com.healthcare.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.model.entity.Admin;
import com.healthcare.service.AdminService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin
@RestController(value = "AdminRestAPI")
@RequestMapping(value = "/api/admin")
public class AdminController extends AbstractBasedAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminService adminService;

	@ApiOperation(value = "save admin", notes = "save admin")
	@ApiParam(name = "admin", value = "admin to save", required = true)
	@PostMapping()
	public ResponseEntity<Admin> create(@RequestBody Admin admin) {
		admin = adminService.save(admin);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	@ApiOperation(value = "get admin by id", notes = "get admin by id")
	@ApiImplicitParam(name = "id", value = "admin id", required = true, dataType = "Long")
	@GetMapping("/{id}")
	public Admin read(@PathVariable Long id) {
		logger.info("id : " + id);
		return adminService.findById(id);
	}

	@ApiOperation(value = "get all admin", notes = "get all admin")
	@GetMapping()
	public List<Admin> readAll() {
		return adminService.findAll();
	}

	@ApiOperation(value = "update admin", notes = "update admin")
	@ApiParam(name = "admin", value = "admin to update", required = true)
	@PutMapping()
	public ResponseEntity<Admin> update(@RequestBody Admin admin) {
		admin = adminService.save(admin);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	@ApiOperation(value = "delete admin", notes = "delete admin")
	@ApiImplicitParam(name = "id", value = "admin id", required = true, dataType = "Long")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		logger.info("id : " + id);
		adminService.deleteById(id);
	}
}
