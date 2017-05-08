package com.healthcare.api;

import com.healthcare.model.entity.Review;
import com.healthcare.service.ReviewService;
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

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping()
    public ResponseEntity create(@ModelAttribute Review review) {

        return ResponseEntity.ok(
                reviewService.save(review).getId()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") String id) {
        Long reviewId = parseId(id);

        if (reviewId == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                reviewService.get(reviewId)
        );
    }

    @PostMapping("/{id}")
    public void save(@ModelAttribute Review review) {
        reviewService.save(review);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id, HttpServletResponse response) {
        Long reviewId = parseId(id);

        if (reviewId == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        reviewService.delete(reviewId);
    }
}
