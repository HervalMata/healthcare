package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Report;
import com.healthcare.repository.ReportRepository;
import com.healthcare.service.ReportService;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	ReportRepository reportRepository;

	@Autowired
	public ReportServiceImpl(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	@Override
	public Report save(Report report) {
		return reportRepository.save(report);
	}

	@Override
	public void delete(Long id) {
		reportRepository.delete(id);

	}

	@Override
	public Report get(Long id) {
		return reportRepository.getOne(id);
	}

}
