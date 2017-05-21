package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Report;

public interface ReportService extends IService<Report> {
	List<Report> findAll();
}
