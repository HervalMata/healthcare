package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>, JpaRepository<Company, Long> {

}