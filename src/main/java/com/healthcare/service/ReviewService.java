package com.healthcare.service;

import com.healthcare.model.entity.Review;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Review service methods
 */
public interface ReviewService {

    /**
     * Save review
     *
     * @param review    review to update
     *
     * @return saved instance
     */
    @Nonnull
    Review save(@Nonnull Review review);

    /**
     * Gets review
     *
     * @param id    review Id
     *
     * @return found instance, null - if nothing found
     */
    @Nullable
    Review get(@Nonnull Long id);

    /**
     * Delete review
     *
     * @param id    review Id
     */
    void delete(@Nonnull Long id);
}
