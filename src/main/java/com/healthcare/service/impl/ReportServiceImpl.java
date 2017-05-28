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
	private static final String KEY = Report.class.getSimpleName();

	@Autowired
	ReportRepository reportRepository;

	@Autowired
	private RedisTemplate<String, Report> reportRedisTemplate;

	@Override
	public Report save(Report report) {
		report = reportRepository.save(report);
		reportRedisTemplate.opsForHash().put(KEY, report.getId(), report);
		return report;
	}

	@Override
	public void deleteById(Long id) {
		reportRepository.delete(id);
		reportRedisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public Report findById(Long id) {
		if (reportRedisTemplate.opsForHash().hasKey(KEY, id))
			return (Report) reportRedisTemplate.opsForHash().get(KEY, id);
		return reportRepository.findOne(id);
	}

	@Override
	public List<Report> findAll() {
		Map<Object, Object> reportMap = reportRedisTemplate.opsForHash().entries(KEY);
		List<Report> reportList = Collections.arrayToList(reportMap.values().toArray());
		if (reportMap.isEmpty())
			reportList = reportRepository.findAll();
		return reportList;
	}
}
