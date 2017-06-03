package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.model.entity.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {

}
