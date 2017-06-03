package com.healthcare.service.impl;

import com.healthcare.model.entity.Activity;
import com.healthcare.repository.ActivityRepository;
import com.healthcare.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    private static final String REDIS_KEY = Activity.class.getSimpleName();

    private ActivityRepository activityRepository;
    private RedisTemplate<String, Activity> redisTemplate;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, RedisTemplate<String, Activity> redisTemplate) {
        this.activityRepository = activityRepository;
        this.redisTemplate = redisTemplate;
    }

    @Nonnull
    @Override
    public Activity save(@Nonnull Activity activity) {
        notNull(activity, "Activity must not be null");

        Activity savedActivity = activityRepository.save(activity);
        redisTemplate.opsForHash().put(REDIS_KEY, savedActivity.getId(), savedActivity);

        return savedActivity;
    }

    @Nullable
    @Override
    public Activity findById(@Nonnull Long id) {
        notNull(id, "Activity Id must not be null");

        Object activity = redisTemplate.opsForHash().get(REDIS_KEY, id);
        if (activity != null) {
            return ((Activity) activity);
        }

        return activityRepository.findOne(id);
    }

    @Override
    public Long deleteById(@Nonnull Long id) {
        notNull(id, "Activity Id must not be null");

        activityRepository.delete(id);
        return redisTemplate.opsForHash().delete(REDIS_KEY, id);
    }
}
