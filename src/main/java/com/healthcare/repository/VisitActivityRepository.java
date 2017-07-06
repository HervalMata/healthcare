package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.entity.VisitActivity;
import com.healthcare.model.entity.VisitActivityPK;

@Repository
public interface VisitActivityRepository extends JpaRepository<VisitActivity, VisitActivityPK> {
	List<VisitActivity> findByVisit(Visit visit);

	List<VisitActivity> findByActivity(Activity activity);
}
