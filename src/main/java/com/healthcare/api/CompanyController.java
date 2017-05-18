package com.healthcare.api;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.model.entity.Company;
import com.healthcare.service.CompanyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jean Antunes on 18/05/17.
 */
@CrossOrigin
@RestController(value = "CompanyRestAPI")
@RequestMapping(value = "/api/company")
public class CompanyController extends AbstractBasedAPI {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "post company", notes = "post company")
    @ApiParam(name = "company", value = "post company", required = true)
    @PostMapping()
    public ResponseEntity<Company> create(@RequestBody Company company) {
        company = companyService.save(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @ApiOperation(value = "get company", notes = "get company")
    @ApiImplicitParam(name = "id", value = "get company", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Company read(@PathVariable Long id) {
        logger.info("id : " + id);
        return companyService.findById(id);
    }

    @ApiOperation(value = "update company", notes = "update company")
    @ApiParam(name = "company", value = "update company", required = true)
    @PutMapping()
    public ResponseEntity<Company> update(@RequestBody Company company) {
        company = companyService.save(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @ApiOperation(value = "delete company", notes = "delete company")
    @ApiImplicitParam(name = "id", value = "delete company", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.info("id : " + id);
        companyService.deleteById(id);
    }
}
