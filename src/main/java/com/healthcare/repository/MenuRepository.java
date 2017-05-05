package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long>, JpaRepository<Menu, Long> {

}