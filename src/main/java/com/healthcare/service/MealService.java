package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Meal;

public interface MealService extends IService<Meal> {

	List<Meal> findAll();
}
