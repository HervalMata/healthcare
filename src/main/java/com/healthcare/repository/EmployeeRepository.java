package com.healthcare.repository;

import com.healthcare.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jean Antunes on 11/05/17.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>, JpaRepository<Employee, Long> {
    Employee findByFirstName(String firstName);
}