package com.healthcare.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.entity.Role;
import com.healthcare.service.RoleService;

@RestController
@RequestMapping(value = "/api/role")
public class RoleController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Role> get(@PathVariable Long id) {
		logger.info("id : " + id);
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(roleService.get(id));
	}

	@PostMapping("/{id}")
	public void save(@RequestBody Role role) {
		roleService.save(role);
	}

	@PostMapping()
	public ResponseEntity<Long> create(@RequestBody Role role) {
		
		return ResponseEntity.ok(roleService.save(role).getId());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		logger.info("id : " + id);
		if (id == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return;
		}

		roleService.delete(id);
	}

	@GetMapping("/level/{level}")
	public ResponseEntity<Role> findByLevel(@PathVariable Long level) {
		logger.info("level : " + level);
		if (level == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(roleService.findByLevel(level));
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<Role>> findByStatus(@PathVariable Long status) {
		logger.info("status : " + status);
		if (status == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(roleService.findByStatus(status));
	}

	@GetMapping("/{id}/status/{status}")
	public ResponseEntity<List<Role>> findByIdAndStatus(@PathVariable Long id, @PathVariable Long status) {
		logger.info("id: " + id + ", status: " + status);
		if (status == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(roleService.findByIdAndStatus(id, status));
	}
}
