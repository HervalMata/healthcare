package com.healthcare.service.impl;

import com.healthcare.model.entity.Activity;
import com.healthcare.repository.ActivityRepository;
import com.healthcare.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.transaction.Transactional;

import static org.springframework.util.Assert.notNull;

/**
 * Activity service
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Nonnull
    @Override
    public Activity save(@Nonnull Activity activity) {
        notNull(activity, "Activity must not be null");

        return activityRepository.save(activity);
    }

    @Nullable
    @Override
    public Activity get(@Nonnull Long id) {
        notNull(id, "Activity Id must not be null");

        return activityRepository.getOne(id);
    }

    @Override
    public void delete(@Nonnull Long id) {
        notNull(id, "Activity Id must not be null");

        activityRepository.delete(id);
    }
}
