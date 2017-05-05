package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Audit;

@Repository
public interface AuditRepository extends CrudRepository<Audit, Long>, JpaRepository<Audit, Long> {

}