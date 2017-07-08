package com.healthcare.api;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.model.entity.EmployeeActivity;
import com.healthcare.service.EmployeeActivityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jean on 14/06/17.
 */
@CrossOrigin
@RestController(value = "EmployeeActivityRestAPI")
@RequestMapping(value = "/api/employee_activity")
public class EmployeeActivityController extends AbstractBasedAPI {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeActivityService employeeActivityService;

    @ApiOperation(value = "save employee activity", notes = "save employee activity")
    @ApiParam(name = "employee activity", value = "employee activity to save", required = true)
    @PostMapping()
    public ResponseEntity<EmployeeActivity> create(@RequestBody EmployeeActivity employeeActivity) {
        employeeActivity = employeeActivityService.save(employeeActivity);
        return new ResponseEntity<EmployeeActivity>(employeeActivity, HttpStatus.OK);
    }

    @ApiOperation(value = "get employee activity by id", notes = "get employee activity by id")
    @ApiImplicitParam(name = "id", value = "employee activity id", required = true, dataType = "Long")
    @GetMapping("/{employee_activity}")
    public EmployeeActivity read(@PathVariable Long id) {
        logger.info("id : " + id);
        return employeeActivityService.findById(id);
    }

    @ApiOperation(value = "get all employee activity", notes = "get all employee activity")
    @GetMapping()
    public List<EmployeeActivity> readAll() {
        return employeeActivityService.findAll();
    }

    @ApiOperation(value = "update employee activity", notes = "update employee activity")
    @ApiParam(name = "employee activity", value = "employee activity to update", required = true)
    @PutMapping()
    public ResponseEntity<EmployeeActivity> update(@RequestBody EmployeeActivity employeeActivity) {
        employeeActivity = employeeActivityService.save(employeeActivity);
        return new ResponseEntity<EmployeeActivity>(employeeActivity, HttpStatus.OK);
    }

    @ApiOperation(value = "delete employee activity", notes = "delete employee activity")
    @ApiImplicitParam(name = "id", value = "employee activity id", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.info("id : " + id);
        employeeActivityService.deleteById(id);
    }


}
