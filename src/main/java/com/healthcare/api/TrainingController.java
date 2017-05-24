package com.healthcare.api;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.model.entity.Training;
import com.healthcare.service.TrainingService;
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
 * Created by jean on 23/05/17.
 */
@CrossOrigin
@RestController(value = "TrainingRestAPI")
@RequestMapping(value = "/api/training")
public class TrainingController extends AbstractBasedAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TrainingService trainingService;

    @ApiOperation(value = "save training", notes = "save training")
    @ApiParam(name = "training", value = "training to save", required = true)
    @PostMapping()
    public ResponseEntity<Training> create(@RequestBody Training training) {
        training = trainingService.save(training);
        return new ResponseEntity<Training>(training, HttpStatus.OK);
    }

    @ApiOperation(value = "get training by id", notes = "get training by id")
    @ApiImplicitParam(name = "id", value = "training id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Training read(@PathVariable Long id) {
        logger.info("id : " + id);
        return trainingService.findById(id);
    }

    @ApiOperation(value = "get all training", notes = "get all training")
    @GetMapping()
    public List<Training> readAll() {
        return trainingService.findAll();
    }

    @ApiOperation(value = "update training", notes = "update training")
    @ApiParam(name = "training", value = "training to update", required = true)
    @PutMapping()
    public ResponseEntity<Training> update(@RequestBody Training training) {
        training = trainingService.save(training);
        return new ResponseEntity<Training>(training, HttpStatus.OK);
    }

    @ApiOperation(value = "delete training", notes = "delete training")
    @ApiImplicitParam(name = "id", value = "training id", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.info("id : " + id);
        trainingService.deleteById(id);
    }


}
