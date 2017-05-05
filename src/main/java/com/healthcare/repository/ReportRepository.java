package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long>, JpaRepository<Report, Long> {

}