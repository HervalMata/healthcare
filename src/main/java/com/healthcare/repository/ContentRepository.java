package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
	
}