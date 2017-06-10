package com.healthcare.repository;

import java.util.Date;
import java.util.List;

import com.healthcare.model.entity.ServicePlan;

public interface ServicePlanRepositoryCustom {
	List<Date> getServiceCalendar(ServicePlan servicePlan);
}
