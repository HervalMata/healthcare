package com.healthcare.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.entity.Visit;
import com.healthcare.service.VisitService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/visit")
public class VisitController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VisitService visitService;

	@ApiOperation(value = "save visit", notes = "save a new visit")
	@ApiParam(name = "visit", value = "visit to update", required = true)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Visit> create(@RequestBody Visit visit) {
		visit = visitService.save(visit);
		return new ResponseEntity<Visit>(visit, HttpStatus.OK);
	}

	@ApiOperation(value = "get visit by Id", notes = "get visit info by id")
	@ApiImplicitParam(name = "id", value = "visit Id", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Visit read(@PathVariable Long id) {
		return visitService.findById(id);
	}

	@ApiOperation(value = "update visit", notes = "update a visit")
	@ApiParam(name = "visit", value = "visit to update", required = true)
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Visit> update(@RequestBody Visit visit) {
		visit = visitService.save(visit);
		return new ResponseEntity<Visit>(visit, HttpStatus.OK);
	}

	@ApiOperation(value = "delete visit", notes = "delete a visit")
	@ApiImplicitParam(name = "id", value = "visit Id", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		visitService.deleteById(id);
	}
}