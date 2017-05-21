package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Report;
import com.healthcare.repository.ReportRepository;
import com.healthcare.service.ReportService;

import io.jsonwebtoken.lang.Collections;

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

	@Override
	public List<Report> findAll() {
		Map<Object, Object> reportMap = reportRedisTemplate.opsForHash().entries(REPORT_KEY);
		List<Report> reportList = Collections.arrayToList(reportMap.values().toArray());
		if(reportMap.isEmpty())
			reportList = reportRepository.findAll();
		return reportList;
	}
}
