package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Report;
import com.healthcare.repository.ReportRepository;
import com.healthcare.service.ReportService;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportRepository reportRepository;

	@Override
	public Report save(Report report) {
		return reportRepository.save(report);
	}

	@Override
	public void deleteById(Long id) {
		reportRepository.delete(id);
	}

	@Override
	public Report findById(Long id) {
		return reportRepository.findOne(id);
	}
}
