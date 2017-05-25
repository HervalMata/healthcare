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

import com.healthcare.model.entity.Agency;
import com.healthcare.service.AgencyService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/agency")
public class AgencyController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AgencyService agencyService;

	@ApiOperation(value = "save agency", notes = "save agency")
	@ApiParam(name = "agency", value = "agency to save", required = true)
	@PostMapping()
	public ResponseEntity<Agency> create(@RequestBody Agency agency) {
		agency = agencyService.save(agency);
		return new ResponseEntity<Agency>(agency, HttpStatus.OK);
	}

	@ApiOperation(value = "get agency by id", notes = "get agency by id")
	@ApiImplicitParam(name = "id", value = "agency id", required = true, dataType = "Long")
	@GetMapping("/{id}")
	public Agency read(@PathVariable Long id) {
		logger.info("id : " + id);
		return agencyService.findById(id);
	}

	@ApiOperation(value = "get all agency", notes = "get all agency")
	@GetMapping()
	public List<Agency> readAll() {
		return agencyService.findAll();
	}

	@ApiOperation(value = "update agency", notes = "update agency")
	@ApiParam(name = "agency", value = "agency to update", required = true)
	@PutMapping()
	public ResponseEntity<Agency> update(@RequestBody Agency agency) {
		agency = agencyService.save(agency);
		return new ResponseEntity<Agency>(agency, HttpStatus.OK);
	}

	@ApiOperation(value = "delete agency", notes = "delete agency")
	@ApiImplicitParam(name = "id", value = "agency id", required = true, dataType = "Long")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		logger.info("id : " + id);
		agencyService.deleteById(id);
	}
}
