package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.AdminPost;

@Repository
public interface AdminPostRepository extends CrudRepository<AdminPost, Long>, JpaRepository<AdminPost, Long> {

}