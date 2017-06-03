package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.ServicePlan;

public interface ServicePlanService extends IService<ServicePlan> {
	List<ServicePlan> findAll();
}
