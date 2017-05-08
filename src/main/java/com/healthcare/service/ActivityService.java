package com.healthcare.service;

import com.healthcare.model.entity.Activity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Activity service methods
 */
public interface ActivityService {

    /**
     * Save activity
     *
     * @param activity    activity to update
     *
     * @return saved instance
     */
    @Nonnull
    Activity save(@Nonnull Activity activity);

    /**
     * Gets activity
     *
     * @param id    activity Id
     *
     * @return found instance, null - if nothing found
     */
    @Nullable
    Activity get(@Nonnull Long id);

    /**
     * Delete activity
     *
     * @param id    activity Id
     */
    void delete(@Nonnull Long id);
}
