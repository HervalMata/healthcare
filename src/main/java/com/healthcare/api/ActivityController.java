package com.healthcare.api;

import com.healthcare.model.entity.Activity;
import com.healthcare.service.ActivityService;
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
 * Activity controller
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController extends BaseController {

    private ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping()
    public ResponseEntity create(@ModelAttribute Activity activity) {

        return ResponseEntity.ok(
                activityService.save(activity).getId()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") String id) {
        Long activityId = parseId(id);

        if (activityId == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                activityService.get(activityId)
        );
    }

    @PostMapping("/{id}")
    public void save(@ModelAttribute Activity activity) {
        activityService.save(activity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id, HttpServletResponse response) {
        Long activityId = parseId(id);

        if (activityId == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        activityService.delete(activityId);
    }
}
