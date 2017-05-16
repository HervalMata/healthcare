package com.healthcare.api;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/get_employee", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody String getFirstName(@RequestParam("first_name") String firstName) {

        return firstName;
    }
}
