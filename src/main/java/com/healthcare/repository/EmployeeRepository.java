package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>, JpaRepository<Employee, Long> {

    Employee findById(long l);

}