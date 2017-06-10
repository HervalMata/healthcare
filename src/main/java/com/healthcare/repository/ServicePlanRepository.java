package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.ServicePlan;

@Repository
public interface ServicePlanRepository extends JpaRepository<ServicePlan, Long>, ServicePlanRepositoryCustom {
}