package com.healthcare.api;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.entity.Report;
import com.healthcare.service.ReportService;

@RestController
@RequestMapping(value = "/api/report")
public class ReportController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ReportService reportService;

	@Autowired
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Report> get(@PathVariable Long id) {
		logger.info("id : " + id);
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(reportService.get(id));
	}

	@PostMapping("/{id}")
	public void save(@RequestBody Report report) {
		reportService.save(report);
	}

	@PostMapping()
	public ResponseEntity<Long> create(@RequestBody Report report) {

		return ResponseEntity.ok(reportService.save(report).getId());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		logger.info("id : " + id);
		if (id == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return;
		}

		reportService.delete(id);
	}
}
