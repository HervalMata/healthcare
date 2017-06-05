package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Visit;

public interface VisitService extends IService<Visit> {
	List<Visit> findAllByServicePlanId(Long servicePlanId);
}
