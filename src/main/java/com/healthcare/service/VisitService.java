package com.healthcare.service;

import com.healthcare.model.entity.Visit;

public interface VisitService {
	Visit save(Visit visit);

	Visit findById(Long id);

	void deleteById(Long id);
}
