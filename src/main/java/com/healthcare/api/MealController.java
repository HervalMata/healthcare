package com.healthcare.api;

import com.healthcare.model.entity.Meal;
import com.healthcare.service.MealService;
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

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping()
    public ResponseEntity create(@RequestBody Meal meal) {

        return ResponseEntity.ok(
                mealService.save(meal).getId()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") String id) {
        Long mealId = parseId(id);

        if (mealId == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                mealService.findById(mealId)
        );
    }

    @PutMapping
    public void save(@RequestBody Meal meal) {
        mealService.save(meal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id, HttpServletResponse response) {
        Long mealId = parseId(id);

        if (mealId == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        mealService.deleteById(mealId);
    }
}
