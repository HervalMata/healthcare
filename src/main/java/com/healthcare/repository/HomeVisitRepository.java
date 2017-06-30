package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.HomeVisit;

@Repository
public interface HomeVisitRepository extends JpaRepository<HomeVisit, Long> {
	
}