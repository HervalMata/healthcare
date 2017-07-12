package com.healthcare.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.entity.Meal;
import com.healthcare.service.MealService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Meal controller
 */
@RestController
@RequestMapping("/api/meal")
public class MealController extends BaseController {

	private MealService mealService;

	@Autowired
	public MealController(MealService mealService) {
		this.mealService = mealService;
	}

	@ApiOperation(value = "Create meal", notes = "Create meal")
	@ApiParam(name = "meal", value = "meal to create", required = true)
	@PostMapping()
	public ResponseEntity create(@RequestBody Meal meal) {

		return ResponseEntity.ok(mealService.save(meal).getId());
	}

	@ApiOperation(value = "Get meal by Id", notes = "Get meal info by mealId")
	@ApiImplicitParam(name = "id", value = "meal Id", required = true, dataType = "Long")
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") String id) {
		Long mealId = parseId(id);

		if (mealId == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(mealService.findById(mealId));
	}

	@ApiOperation(value = "Update meal", notes = "Update meal")
	@ApiParam(name = "meal", value = "meal to update", required = true)
	@PutMapping
	public void save(@RequestBody Meal meal) {
		mealService.save(meal);
	}

	@ApiOperation(value = "Delete meal", notes = "Delete meal")
	@ApiImplicitParam(name = "id", value = "meal Id", required = true, dataType = "Long")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id, HttpServletResponse response) {
		Long mealId = parseId(id);

		if (mealId == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return;
		}

		mealService.deleteById(mealId);
	}
	
	@ApiOperation(value = "Get all meals", notes = "Get all meals")
	@GetMapping()
	public ResponseEntity<List<Meal>> findAll() {
		return ResponseEntity.ok(mealService.findAll());
	}
}
