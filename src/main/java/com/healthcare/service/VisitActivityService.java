package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.entity.VisitActivity;
import com.healthcare.model.entity.VisitActivityPK;

public interface VisitActivityService{
	VisitActivity save(VisitActivity visitActivity);
	
	VisitActivity findById(VisitActivityPK id);
	
	List<VisitActivity> findByVisit(Visit visit);

	List<VisitActivity> findByActivity(Activity activity);

	List<VisitActivity> findAll();
	
	Long deleteById(VisitActivityPK id);
}
