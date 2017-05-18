package com.healthcare.api;

import com.healthcare.api.auth.AbstractBasedAPI;
import com.healthcare.model.entity.Agency;
import com.healthcare.service.AgencyService;
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
@RestController(value = "AgencyRestAPI")
@RequestMapping(value = "/api/agency")
public class AgencyController extends AbstractBasedAPI {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AgencyService agencyService;

    @ApiOperation(value = "post agency", notes = "post agency")
    @ApiParam(name = "agency", value = "post agency", required = true)
    @PostMapping()
    public ResponseEntity<Agency> create(@RequestBody Agency agency) {
        agency = agencyService.save(agency);
        return new ResponseEntity<>(agency, HttpStatus.OK);
    }

    @ApiOperation(value = "get agency", notes = "get agency")
    @ApiImplicitParam(name = "id", value = "get agency", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Agency read(@PathVariable Long id) {
        logger.info("id : " + id);
        return agencyService.findById(id);
    }

    @ApiOperation(value = "update agency", notes = "update agency")
    @ApiParam(name = "agency", value = "update agency", required = true)
    @PutMapping()
    public ResponseEntity<Agency> update(@RequestBody Agency agency) {
        agency = agencyService.save(agency);
        return new ResponseEntity<>(agency, HttpStatus.OK);
    }

    @ApiOperation(value = "delete agency", notes = "delete agency")
    @ApiImplicitParam(name = "id", value = "delete agency", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.info("id : " + id);
        agencyService.deleteById(id);
    }
}
