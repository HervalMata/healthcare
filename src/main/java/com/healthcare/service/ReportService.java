package com.healthcare.service;

import com.healthcare.model.entity.Report;

public interface ReportService {
	Report save(Report report);

	void delete(Long id);

	Report get(Long id);
}
