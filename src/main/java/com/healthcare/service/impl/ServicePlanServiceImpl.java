package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.ServicePlan;
import com.healthcare.repository.ServicePlanRepository;
import com.healthcare.service.ServicePlanService;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class ServicePlanServiceImpl implements ServicePlanService {
	private static final String KEY = ServicePlan.class.getSimpleName();

	@Autowired
	ServicePlanRepository servicePlanRepository;

	@Autowired
	private RedisTemplate<String, ServicePlan> servicePlanRedisTemplate;

	@Override
	public ServicePlan save(ServicePlan servicePlan) {
		servicePlan = servicePlanRepository.save(servicePlan);
		servicePlanRedisTemplate.opsForHash().put(KEY, servicePlan.getId(), servicePlan);
		return servicePlan;
	}

	@Override
	public void deleteById(Long id) {
		servicePlanRepository.delete(id);
		servicePlanRedisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public ServicePlan findById(Long id) {
		if (servicePlanRedisTemplate.opsForHash().hasKey(KEY, id))
			return (ServicePlan) servicePlanRedisTemplate.opsForHash().get(KEY, id);
		return servicePlanRepository.findOne(id);
	}

	@Override
	public List<ServicePlan> findAll() {
		Map<Object, Object> servicePlanMap = servicePlanRedisTemplate.opsForHash().entries(KEY);
		List<ServicePlan> servicePlanList = Collections.arrayToList(servicePlanMap.values().toArray());
		if (servicePlanMap.isEmpty())
			servicePlanList = servicePlanRepository.findAll();
		return servicePlanList;
	}
}