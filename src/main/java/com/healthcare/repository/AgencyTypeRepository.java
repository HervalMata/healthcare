package com.healthcare.repository;

import com.healthcare.model.entity.AgencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyTypeRepository extends JpaRepository<AgencyType, Long> {

}