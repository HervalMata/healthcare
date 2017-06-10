package com.healthcare.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.entity.VisitActivity;
import com.healthcare.model.entity.VisitActivityPK;
import com.healthcare.service.VisitActivityService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/visitactivity")
public class VisitActivityController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VisitActivityService visitActivityService;

	@ApiOperation(value = "save visit activity", notes = "save visit activity")
	@ApiParam(name = "visitActivity", value = "visit activity to save", required = true)
	@PostMapping()
	public ResponseEntity<VisitActivity> create(@RequestBody VisitActivity visitActivity) {
		visitActivity = visitActivityService.save(visitActivity);
		return new ResponseEntity<VisitActivity>(visitActivity, HttpStatus.OK);
	}

	@ApiOperation(value = "get visit activity by id", notes = "get visit activity by id")
	@ApiImplicitParams({ @ApiImplicitParam(name = "visitId", value = "visit id", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "activityId", value = "activity id", required = true, dataType = "Long") })
	@GetMapping("/{visitId}/{activityId}")
	public VisitActivity read(@PathVariable Long visitId, @PathVariable Long activityId) {
		logger.info("visitId : " + visitId + ", activityId : " + activityId);
		return visitActivityService.findById(new VisitActivityPK(visitId, activityId));
	}

	@ApiOperation(value = "get all visit activity", notes = "get all visit activity")
	@GetMapping()
	public List<VisitActivity> readAll() {
		return visitActivityService.findAll();
	}
	
	@ApiOperation(value = "get visit activity by visit id", notes = "get visit activity by visit id")
	@ApiImplicitParam(name = "visitId", value = "visit id", required = true, dataType = "Long")
	@GetMapping("/visit/{visitId}")
	public List<VisitActivity> readByVisit(@PathVariable Long visitId) {
		logger.info("visitId : " + visitId);
		Visit visit = new Visit();
		visit.setId(visitId);
		return visitActivityService.findByVisit(visit);
	}
	
	@ApiOperation(value = "get visit activity by activity id", notes = "get visit activity by activity id")
	@ApiImplicitParam(name = "activityId", value = "activity id", required = true, dataType = "Long")
	@GetMapping("/activity/{activityId}")
	public List<VisitActivity> readByActivity(@PathVariable Long activityId) {
		logger.info("visitId : " + activityId);
		Activity activity = new Activity();
		activity.setId(activityId);
		return visitActivityService.findByActivity(activity);
	}
	
	@ApiOperation(value = "update visit activity", notes = "update visit activity")
	@ApiParam(name = "visitActivity", value = "visit activity to update", required = true)
	@PutMapping()
	public ResponseEntity<VisitActivity> update(@RequestBody VisitActivity visitActivity) {
		visitActivity = visitActivityService.save(visitActivity);
		return new ResponseEntity<VisitActivity>(visitActivity, HttpStatus.OK);
	}

	@ApiOperation(value = "delete visit activity", notes = "delete visit activity")
	@ApiImplicitParams({ @ApiImplicitParam(name = "visitId", value = "visit id", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "activityId", value = "activity id", required = true, dataType = "Long") })
	@DeleteMapping("/{visitId}/{activityId}")
	public void delete(@PathVariable Long visitId, @PathVariable Long activityId) {
		logger.info("visitId : " + visitId + ", activityId : " + activityId);
		visitActivityService.deleteById(new VisitActivityPK(visitId, activityId));
	}
}
