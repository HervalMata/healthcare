package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthcare.model.entity.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
	Visit findByUserBarcodeId(String userBarcodeId);
	
	@Query(value = "select * from visit v where v.serviceplan_id = ?1", nativeQuery = true)
	List<Visit> findAllByServicePlanId(@Param("servicePlanId") long servicePlanId);
}
