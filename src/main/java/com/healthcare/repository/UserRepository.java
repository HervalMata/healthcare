package com.healthcare.repository;

import org.springframework.data.repository.Repository;

import com.healthcare.model.entity.User;

public interface UserRepository extends Repository<User, Long> {

}
