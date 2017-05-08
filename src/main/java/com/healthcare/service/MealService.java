package com.healthcare.service;

import com.healthcare.model.entity.Meal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Meal service methods
 */
public interface MealService {

    /**
     * Save meal
     *
     * @param meal    meal to update
     *
     * @return saved instance
     */
    @Nonnull
    Meal save(@Nonnull Meal meal);

    /**
     * Gets meal
     *
     * @param id    meal Id
     *
     * @return found instance, null - if nothing found
     */
    @Nullable
    Meal get(@Nonnull Long id);

    /**
     * Delete meal
     *
     * @param id    meal Id
     */
    void delete(@Nonnull Long id);
}
