package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Agency;

@Repository
public interface AgencyRepository extends CrudRepository<Agency, Long>, JpaRepository<Agency, Long> {

}