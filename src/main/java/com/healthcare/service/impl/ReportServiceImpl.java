package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Report;
import com.healthcare.repository.ReportRepository;
import com.healthcare.service.ReportService;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportRepository reportRepository;

	@Autowired
	private RedisTemplate<String, Report> reportRedisTemplate;
	
	private static String REPORT_KEY = "Report";

	@Override
	public Report save(Report report) {
		report = reportRepository.save(report);
		reportRedisTemplate.opsForHash().put(REPORT_KEY, report.getId(), report);
		return report;
	}

	@Override
	public void deleteById(Long id) {
		reportRepository.delete(id);
		reportRedisTemplate.opsForHash().delete(REPORT_KEY, id);
	}

	@Override
	public Report findById(Long id) {
		Report report = (Report) reportRedisTemplate.opsForHash().get(REPORT_KEY, id);
		if (report == null)
			report = reportRepository.findOne(id);
		return report;
	}
}
