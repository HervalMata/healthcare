package com.healthcare.repository;

import com.healthcare.model.entity.AdminPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPostRepository extends JpaRepository<AdminPost, Long> {

}