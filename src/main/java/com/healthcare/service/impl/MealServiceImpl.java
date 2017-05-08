package com.healthcare.service.impl;

import com.healthcare.model.entity.Meal;
import com.healthcare.repository.MealRepository;
import com.healthcare.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.transaction.Transactional;

import static org.springframework.util.Assert.notNull;

/**
 * Meal service
 */
@Service
@Transactional
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Nonnull
    @Override
    public Meal save(@Nonnull Meal meal) {
        notNull(meal, "Meal must not be null");

        return mealRepository.save(meal);
    }

    @Nullable
    @Override
    public Meal get(@Nonnull Long id) {
        notNull(id, "Meal Id must not be null");

        return mealRepository.getOne(id);
    }

    @Override
    public void delete(@Nonnull Long id) {
        notNull(id, "Meal Id must not be null");

        mealRepository.delete(id);
    }
}
