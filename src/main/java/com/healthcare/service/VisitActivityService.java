package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.VisitActivity;
import com.healthcare.model.entity.VisitActivityPK;

public interface VisitActivityService extends IService<VisitActivity> {

	VisitActivity findById(VisitActivityPK pk);

	Long deleteById(VisitActivityPK pk);

	List<VisitActivity> findVisitActivityByActivityId(Long id);

	List<VisitActivity> findVisitActivityByVisitId(Long id);

}
