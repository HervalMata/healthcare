package com.healthcare.api;

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

import com.healthcare.model.entity.Menu;
import com.healthcare.service.MenuService;

@RestController
@RequestMapping(value = "/api/menu")
public class MenuController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Menu> get(@PathVariable Long id) {
		logger.info("id : " + id);
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(menuService.get(id));
	}

	@PostMapping("/{id}")
	public void save(@RequestBody Menu menu) {
		menuService.save(menu);
	}

	@PostMapping()
	public ResponseEntity<Long> create(@RequestBody Menu menu) {

		return ResponseEntity.ok(menuService.save(menu).getId());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		logger.info("id : " + id);
		if (id == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return;
		}

		menuService.delete(id);
	}
}
