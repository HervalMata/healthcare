package com.healthcare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.healthcare.model.entity.Meal;
import com.healthcare.repository.MealRepository;
import com.healthcare.service.MealService;

public class MealServiceImpl implements MealService {
	@Autowired
	private MealRepository mealRepository;

	@Override
	public Meal save(Meal meal) {
		return mealRepository.save(meal);
	}

	@Override
	public Meal findById(Long id) {
		return mealRepository.findOne(id);
	}

	@Override
	public void deleteById(Long id) {
		mealRepository.delete(id);
	}

}
