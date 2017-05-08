package com.healthcare.api;

import com.healthcare.model.entity.Meal;
import com.healthcare.service.MealService;
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

import javax.servlet.http.HttpServletResponse;

/**
 * Meal controller
 */
@RestController
@RequestMapping("/api/meal")
public class MealController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MealController.class);

    private MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping()
    public ResponseEntity create(@ModelAttribute Meal meal) {

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
                mealService.get(mealId)
        );
    }

    @PostMapping("/{id}")
    public void save(@ModelAttribute Meal meal) {
        mealService.save(meal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id, HttpServletResponse response) {
        Long mealId = parseId(id);

        if (mealId == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        mealService.delete(mealId);
    }

    /**
     * Parse entity Id
     *
     * @param id    Id to parse
     *
     * @return parsed Id, or null if non-numeric value
     */
    private Long parseId(String id) {
        Long result = null;

        try {
            result = Long.valueOf(id);
        } catch (NumberFormatException e) {
            LOGGER.error("Entity Id must be numeric: '{}'", id);
        }

        return result;
    }
}
