package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Activity;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long>, JpaRepository<Activity, Long> {

}