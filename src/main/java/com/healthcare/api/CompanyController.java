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

import com.healthcare.model.entity.Company;
import com.healthcare.service.CompanyService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CompanyService companyService;

	@ApiOperation(value = "save company", notes = "save company")
	@ApiParam(name = "company", value = "company to save", required = true)
	@PostMapping()
	public ResponseEntity<Company> create(@RequestBody Company company) {
		company = companyService.save(company);
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	@ApiOperation(value = "get company by id", notes = "get company by id")
	@ApiImplicitParam(name = "id", value = "company id", required = true, dataType = "Long")
	@GetMapping("/{id}")
	public Company read(@PathVariable Long id) {
		logger.info("id : " + id);
		return companyService.findById(id);
	}

	@ApiOperation(value = "get all company", notes = "get all company")
	@GetMapping()
	public List<Company> readAll() {
		return companyService.findAll();
	}

	@ApiOperation(value = "update company", notes = "update company")
	@ApiParam(name = "company", value = "company to update", required = true)
	@PutMapping()
	public ResponseEntity<Company> update(@RequestBody Company company) {
		company = companyService.save(company);
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	@ApiOperation(value = "delete company", notes = "delete company")
	@ApiImplicitParam(name = "id", value = "company id", required = true, dataType = "Long")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		logger.info("id : " + id);
		companyService.deleteById(id);
	}
}
