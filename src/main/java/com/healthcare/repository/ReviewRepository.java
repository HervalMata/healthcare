package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>, JpaRepository<Review, Long> {

}