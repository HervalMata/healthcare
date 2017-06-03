package com.healthcare.repository;

import com.healthcare.model.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<Training, Long>, JpaRepository<Training, Long> {

    Training findById(long l);
}