package com.healthcare.api;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.model.entity.Employee;
import com.healthcare.service.EmployeeService;
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
 * Created by Jean Antunes on 11/05/17.
 */
@CrossOrigin
@RestController(value = "EmployeeRestAPI")
@RequestMapping(value = "/api/employee")
public class EmployeeController extends AbstractBasedAPI {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "post employee", notes = "post employee")
    @ApiParam(name = "employee", value = "post employee", required = true)
    @PostMapping()
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employee = employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "get employee", notes = "get employee")
    @ApiImplicitParam(name = "id", value = "get employee", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Employee read(@PathVariable Long id) {
        logger.info("id : " + id);
        return employeeService.findById(id);
    }

    @ApiOperation(value = "update employee", notes = "update employee")
    @ApiParam(name = "employee", value = "update employee", required = true)
    @PutMapping()
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        employee = employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "delete employee", notes = "delete employee")
    @ApiImplicitParam(name = "id", value = "delete employee", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.info("id : " + id);
        employeeService.deleteById(id);
    }
}
