package com.healthcare.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.ServicePlan;
import com.healthcare.repository.ServicePlanRepository;
import com.healthcare.service.ServicePlanService;
import com.healthcare.util.DateUtils;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class ServicePlanServiceImpl implements ServicePlanService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
	public Long deleteById(Long id) {
		servicePlanRepository.delete(id);
		return servicePlanRedisTemplate.opsForHash().delete(KEY, id);
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
	
	/**
	 * generate service calendar (Home visit)
	 * @param Long serviceplanId
	 * @return List<HomeVisitDto>
	 */
	@Override
	public List<Date> serviceCalendarGeneration(Long serviceplanId) {			
		ServicePlan servicePlan = findById(serviceplanId);
		
		//get all schudled dates between start and end  service plan 
		List<Date> generateCalendar = DateUtils.getDaysBetweenDates(servicePlan.getPlanStart(),
				servicePlan.getPlanEnd(), servicePlan.getDays());
		if(logger.isDebugEnabled()){
			logger.debug("HCS_service plan start after : "+servicePlan.getPlanStart());
			logger.debug("HCS_service plan end before : "+servicePlan.getPlanStart());
			logger.debug("HCS_list of days between start and end service plan : "+generateCalendar.size());
		}
		return generateCalendar;
	}
}
