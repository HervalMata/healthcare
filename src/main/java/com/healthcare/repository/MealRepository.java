package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Meal;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long>, JpaRepository<Meal, Long> {

}