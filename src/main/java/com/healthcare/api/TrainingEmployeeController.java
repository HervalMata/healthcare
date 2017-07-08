package com.healthcare.api;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.model.entity.TrainingEmployee;
import com.healthcare.service.TrainingEmployeeService;
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
 * Created by jean on 03/07/17.
 */
@CrossOrigin
@RestController(value = "TrainingHasEmployeeRestAPI")
@RequestMapping(value = "/api/training_has_employee")
public class TrainingEmployeeController extends AbstractBasedAPI {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TrainingEmployeeService trainingEmployeeService;

    @ApiOperation(value = "save training employee", notes = "save training employee")
    @ApiParam(name = "training employee", value = "training employee to save", required = true)
    @PostMapping()
    public ResponseEntity<TrainingEmployee> create(@RequestBody TrainingEmployee trainingEmployee) {
        trainingEmployee = trainingEmployeeService.save(trainingEmployee);
        return new ResponseEntity<TrainingEmployee>(trainingEmployee, HttpStatus.OK);
    }

    @ApiOperation(value = "get training employee by id", notes = "get training employee by id")
    @ApiImplicitParam(name = "id", value = "training employee id", required = true, dataType = "Long")
    @GetMapping("/{training_has_employee}")
    public TrainingEmployee read(@PathVariable Long id) {
        logger.info("id : " + id);
        return trainingEmployeeService.findById(id);
    }

    @ApiOperation(value = "get all training employee", notes = "get all training employee")
    @GetMapping()
    public List<TrainingEmployee> readAll() {
        return trainingEmployeeService.findAll();
    }

    @ApiOperation(value = "update training employee", notes = "update training employee")
    @ApiParam(name = "training employee", value = "training employee to update", required = true)
    @PutMapping()
    public ResponseEntity<TrainingEmployee> update(@RequestBody TrainingEmployee trainingEmployee) {
        trainingEmployee = trainingEmployeeService.save(trainingEmployee);
        return new ResponseEntity<TrainingEmployee>(trainingEmployee, HttpStatus.OK);
    }

    @ApiOperation(value = "delete training employee", notes = "delete training employee")
    @ApiImplicitParam(name = "id", value = "training employee id", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.info("id : " + id);
        trainingEmployeeService.deleteById(id);
    }


}

