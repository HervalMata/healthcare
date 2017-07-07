package com.healthcare.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Activity;
import com.healthcare.repository.ActivityRepository;
import com.healthcare.service.ActivityService;

import io.jsonwebtoken.lang.Collections;

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

	@Override
	public List<Activity> findAll() {
		Map<Object, Object> activityMap = redisTemplate.opsForHash().entries(REDIS_KEY);
		List<Activity> activityList = Collections.arrayToList(activityMap.values().toArray());
		if (activityMap.isEmpty())
			activityList = activityRepository.findAll();
		return activityList;
	}
}
