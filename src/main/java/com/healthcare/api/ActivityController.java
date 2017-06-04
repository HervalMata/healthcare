package com.healthcare.api;

import com.healthcare.model.entity.Activity;
import com.healthcare.service.ActivityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "Create activity", notes = "Create an activity")
    @ApiParam(name = "activity", value = "activity to create", required = true)
    @PostMapping
    public ResponseEntity create(@RequestBody Activity activity) {

        return ResponseEntity.ok(
                activityService.save(activity).getId()
        );
    }

    @ApiOperation(value = "Get activity by Id", notes = "Get activity info by activityId")
    @ApiImplicitParam(name = "id", value = "activity Id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") String id) {
        Long activityId = parseId(id);

        if (activityId == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                activityService.findById(activityId)
        );
    }

    @ApiOperation(value = "Update activity", notes = "Update an activity")
    @ApiParam(name = "activity", value = "activity to update", required = true)
    @PutMapping
    public void save(@RequestBody Activity activity) {
        activityService.save(activity);
    }

    @ApiOperation(value = "Delete activity", notes = "Delete an activity")
    @ApiImplicitParam(name = "id", value = "activity Id", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id, HttpServletResponse response) {
        Long activityId = parseId(id);

        if (activityId == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        activityService.deleteById(activityId);
    }
}
