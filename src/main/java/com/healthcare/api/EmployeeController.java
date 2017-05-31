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

import java.util.List;

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

    @ApiOperation(value = "save employee", notes = "save employee")
    @ApiParam(name = "employee", value = "employee to save", required = true)
    @PostMapping()
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employee = employeeService.save(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "get employee by id", notes = "get employee by id")
    @ApiImplicitParam(name = "id", value = "employee id", required = true, dataType = "Long")
    @GetMapping("/{employee}")
    public Employee read(@PathVariable Long id) {
        logger.info("id : " + id);
        return employeeService.findById(id);
    }

    @ApiOperation(value = "get all employee", notes = "get all employee")
    @GetMapping()
    public List<Employee> readAll() {
        return employeeService.findAll();
    }

    @ApiOperation(value = "update employee", notes = "update employee")
    @ApiParam(name = "employee", value = "employee to update", required = true)
    @PutMapping()
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        employee = employeeService.save(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "delete employee", notes = "delete employee")
    @ApiImplicitParam(name = "id", value = "employee id", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.info("id : " + id);
        employeeService.deleteById(id);
    }
}
