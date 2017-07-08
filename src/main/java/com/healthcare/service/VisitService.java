package com.healthcare.service;

import java.util.List;

import com.healthcare.api.model.VisitRequest;
import com.healthcare.model.entity.Visit;

public interface VisitService extends IService<Visit> {
	List<Visit> findAllByServicePlanId(Long servicePlanId);

	Visit checkIn(VisitRequest visit);

	Visit checkOut(VisitRequest visit);

	List<Visit> findAll();
}
