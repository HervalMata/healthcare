package com.healthcare.service;

import com.healthcare.api.model.VisitRequest;
import com.healthcare.model.entity.Visit;

public interface VisitService extends IService<Visit> {

	Visit checkIn(VisitRequest visit);

	Visit checkOut(VisitRequest visit);

}
