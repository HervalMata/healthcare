package com.healthcare.service;

import com.healthcare.model.entity.Visit;

public interface VisitService extends IService<Visit> {

	Visit checkIn(Visit visit); 
		
	Visit findByUserBarcodeId(String userBarcodeId);
}
