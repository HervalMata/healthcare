package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Training;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Long>, JpaRepository<Training, Long> {

}