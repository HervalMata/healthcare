package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
