package com.healthcare.api;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.entity.AdminPost;
import com.healthcare.service.AdminPostService;

@RestController
@RequestMapping(value = "/api/adminpost")
public class AdminPostController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AdminPostService adminPostService;

	@Autowired
	public AdminPostController(AdminPostService adminPostService) {
		this.adminPostService = adminPostService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdminPost> get(@PathVariable Long id) {
		logger.info("id : " + id);
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(adminPostService.get(id));
	}

	@PostMapping("/{id}")
	public void save(@ModelAttribute AdminPost adminPost) {
		adminPostService.save(adminPost);
	}

	@PostMapping()
	public ResponseEntity<Long> create(@ModelAttribute AdminPost adminPost) {

		return ResponseEntity.ok(adminPostService.save(adminPost).getId());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		logger.info("id : " + id);
		if (id == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return;
		}

		adminPostService.delete(id);
	}
}
