package com.healthcare.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.entity.Content;
import com.healthcare.service.ContentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by Hitesh on 07/06/17.
 */
@CrossOrigin
@RestController(value = "ContentRestAPI")
@RequestMapping(value = "/api/content")
public class ContentController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContentService contentService;

	@ApiOperation(value = "save content", notes = "save content")
	@ApiParam(name = "content", value = "content to save", required = true)
	@PostMapping()
	public ResponseEntity<Content> create(@RequestBody Content content) {
		content = contentService.save(content);
		return new ResponseEntity<Content>(content, HttpStatus.OK);
	}

	@ApiOperation(value = "get content by id", notes = "get content by id")
	@ApiImplicitParam(name = "id", value = "content id", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/{id}")
	public Content read(@PathVariable("id") Long id) {
		logger.info("id : " + id);
		return contentService.findById(id);
	}

	@ApiOperation(value = "get all content", notes = "get all content")
	@GetMapping()
	public List<Content> readAll() {
		return contentService.findAll();
	}

	@ApiOperation(value = "update content", notes = "update content")
	@ApiParam(name = "content", value = "content to update", required = true)
	@PutMapping()
	public ResponseEntity<Content> update(@RequestBody Content content) {
		content = contentService.save(content);
		return new ResponseEntity<Content>(content, HttpStatus.OK);
	}

	@ApiOperation(value = "delete content", notes = "delete content")
	@ApiImplicitParam(name = "id", value = "content id", required = true, dataType = "Long", paramType = "path")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		logger.info("id : " + id);
		contentService.deleteById(id);
	}
}
