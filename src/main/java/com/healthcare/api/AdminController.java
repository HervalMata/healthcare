package com.healthcare.api;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.model.entity.Admin;
import com.healthcare.service.AdminService;

@CrossOrigin
@RestController(value = "AdminRestAPI")
@RequestMapping(value = "/api/admin")
public class AdminController extends AbstractBasedAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Admin> get(@PathVariable Long id) {
		logger.info("id : " + id);
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(adminService.get(id));
	}

	@PostMapping("/{id}")
	public void save(@RequestBody Admin admin) {
		adminService.save(admin);
	}

	@PostMapping()
	public ResponseEntity<Long> create(@RequestBody Admin admin) {

		return ResponseEntity.ok(adminService.save(admin).getId());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		logger.info("id : " + id);
		if (id == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return;
		}

		adminService.delete(id);
	}
}
