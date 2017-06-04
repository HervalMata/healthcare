package com.healthcare.repository;

import com.healthcare.model.entity.TrainingEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingEmployeeRepository extends JpaRepository<TrainingEmployee, Long> {

}