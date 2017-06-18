package com.healthcare.api;

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

import com.healthcare.model.entity.Review;
import com.healthcare.service.ReviewService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Review controller
 */
@RestController
@RequestMapping("/api/review")
public class ReviewController extends BaseController {

	private ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@ApiOperation(value = "Create review", notes = "Create review")
	@ApiParam(name = "review", value = "review to create", required = true)
	@PostMapping
	public ResponseEntity create(@RequestBody Review review) {

		return ResponseEntity.ok(reviewService.save(review).getId());
	}

	@ApiOperation(value = "Get review by Id", notes = "Get review info by reviewId")
	@ApiImplicitParam(name = "id", value = "review Id", required = true, dataType = "Long")
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") String id) {
		Long reviewId = parseId(id);

		if (reviewId == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(reviewService.findById(reviewId));
	}

	@ApiOperation(value = "Update review", notes = "Update review")
	@ApiParam(name = "review", value = "review to update", required = true)
	@PutMapping
	public void save(@RequestBody Review review) {
		reviewService.save(review);
	}

	@ApiOperation(value = "Delete review", notes = "Delete review")
	@ApiImplicitParam(name = "id", value = "review Id", required = true, dataType = "Long")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id, HttpServletResponse response) {
		Long reviewId = parseId(id);

		if (reviewId == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return;
		}

		reviewService.deleteById(reviewId);
	}
}
