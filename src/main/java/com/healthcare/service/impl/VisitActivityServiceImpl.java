package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.entity.VisitActivity;
import com.healthcare.model.entity.VisitActivityPK;
import com.healthcare.repository.VisitActivityRepository;
import com.healthcare.service.VisitActivityService;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class VisitActivityServiceImpl implements VisitActivityService {
	private static final String KEY = VisitActivity.class.getSimpleName();

	@Autowired
	VisitActivityRepository visitActivityRepository;

	@Autowired
	private RedisTemplate<String, VisitActivity> visitActivityRedisTemplate;

	@Override
	public VisitActivity save(VisitActivity visitActivity) {
		visitActivity = visitActivityRepository.save(visitActivity);
		visitActivityRedisTemplate.opsForHash().put(KEY,
				new VisitActivityPK(visitActivity.getVisitId(), visitActivity.getActivityId()),
				visitActivity);
		return visitActivity;
	}

	@Override
	public Long deleteById(VisitActivityPK id) {
		visitActivityRepository.delete(id);
		return visitActivityRedisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public VisitActivity findById(VisitActivityPK id) {
		if (visitActivityRedisTemplate.opsForHash().hasKey(KEY, id)) {
			return (VisitActivity) visitActivityRedisTemplate.opsForHash().get(KEY, id);
		}
		return visitActivityRepository.findOne(id);
	}

	@Override
	public List<VisitActivity> findAll() {
		Map<Object, Object> visitActivityMap = visitActivityRedisTemplate.opsForHash().entries(KEY);
		List<VisitActivity> visitActivityList = Collections.arrayToList(visitActivityMap.values().toArray());
		if (visitActivityMap.isEmpty())
			visitActivityList = visitActivityRepository.findAll();
		return visitActivityList;
	}

	@Override
	public List<VisitActivity> findByVisit(Visit visit) {
		return visitActivityRepository.findByVisit(visit);
	}

	@Override
	public List<VisitActivity> findByActivity(Activity activity) {
		return visitActivityRepository.findByActivity(activity);
	}
}
