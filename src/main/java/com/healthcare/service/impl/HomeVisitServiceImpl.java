package com.healthcare.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.dto.HomeVisitDto;
import com.healthcare.model.entity.HomeVisit;
import com.healthcare.model.entity.ServicePlan;
import com.healthcare.repository.HomeVisitRepository;
import com.healthcare.service.HomeVisitService;
import com.healthcare.service.ServicePlanService;
import com.healthcare.util.DateUtils;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class HomeVisitServiceImpl implements HomeVisitService {
	private static final String KEY = HomeVisit.class.getSimpleName();

	@Autowired
	HomeVisitRepository homeVisitRepository;
	
	@Autowired
	ServicePlanService servicePlanService;

	@Autowired
	private RedisTemplate<String, HomeVisit> homeVisitRedisTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public HomeVisit save(HomeVisit homeVisit) {
		homeVisit = homeVisitRepository.save(homeVisit);
		homeVisitRedisTemplate.opsForHash().put(KEY, homeVisit.getId(), homeVisit);
		return homeVisit;
	}

	@Override
	public HomeVisit findById(Long id) {
		HomeVisit homeVisit = (HomeVisit) homeVisitRedisTemplate.opsForHash().get(KEY, id);
		if (homeVisit == null)
			homeVisit = homeVisitRepository.findOne(id);
		return homeVisit;
	}

	@Override
	public Long deleteById(Long id) {
		homeVisitRepository.delete(id);
		return homeVisitRedisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public List<HomeVisit> findAll() {
		Map<Object, Object> homeVisitMap = homeVisitRedisTemplate.opsForHash().entries(KEY);
		List<HomeVisit> homeVisitList = Collections.arrayToList(homeVisitMap.values().toArray());
		if (homeVisitMap.isEmpty())
			homeVisitList = homeVisitRepository.findAll();
		return homeVisitList;
	}
	
	/**
	 * generate service calendar (Home visit)
	 * @param Long serviceplanId
	 * @return List<HomeVisitDto>
	 */
	@Override
	public List<HomeVisitDto> serviceCalendarGeneration(Long serviceplanId) {
		// local variables
		List<HomeVisitDto> resultList = new ArrayList<>();
		List<HomeVisit> listHomeVisitByServicePlan = new ArrayList<>();
			
		//get ServicePlan
		ServicePlan servicePlan = servicePlanService.findById(serviceplanId);
		
		//get all schudled dates between start and end  service plan 
		List<Date> generateCalendar = DateUtils.getDaysBetweenDates(servicePlan.getPlanStart(),
				servicePlan.getPlanEnd(), servicePlan.getDays());
		if(logger.isDebugEnabled()){
			logger.debug("HCS_service plan start after : "+servicePlan.getPlanStart());
			logger.debug("HCS_service plan end before : "+servicePlan.getPlanStart());
			logger.debug("HCS_list of days between start and end service plan : "+generateCalendar.size());
		}
			
		//find list of homeVisit by service plan from Redis
		listHomeVisitByServicePlan = findHomeVisitsByServicePlanFromRedis(serviceplanId);
		if(listHomeVisitByServicePlan.isEmpty()){		//find homeVisit by service plan from DB
			//find homeVisit by service plan from DB
			listHomeVisitByServicePlan = homeVisitRepository.findAllByServiceplan(servicePlan);
			if(logger.isDebugEnabled())
			logger.info("HCS_load home visit by servicePlan (REDIS) : SIZE IS : "+listHomeVisitByServicePlan.size());
		}
		
		// convert to HomeVisitDto object
		if(!generateCalendar.isEmpty() && !listHomeVisitByServicePlan.isEmpty()){
			logger.info("HCS_To Dto conversion");
			for (int i = 0; i < listHomeVisitByServicePlan.size(); i++) {
				for (int j = 0; j < generateCalendar.size(); j++) {
					if(i == j){
						resultList.add(new HomeVisitDto(listHomeVisitByServicePlan.get(i), generateCalendar.get(j)));
					}
				}
			}
		}
		return resultList;
	}
	
	/**
	 * find home visits by service plan (Redis)
	 * @param serviceplanId
	 * @return
	 */
	private List<HomeVisit> findHomeVisitsByServicePlanFromRedis(Long serviceplanId){
		//result list
		List<HomeVisit> resultList = new ArrayList<>();
		
		// find All homeVisit from Redis
		Map<Object, Object> homeVisitMap = homeVisitRedisTemplate.opsForHash().entries(KEY);
		List<HomeVisit> listHomeVisit = Collections.arrayToList(homeVisitMap.values().toArray());
				
		if(listHomeVisit != null && !listHomeVisit.isEmpty()){
			for(HomeVisit homeVisit : listHomeVisit){
				if(serviceplanId.equals(homeVisit.getServiceplan().getId())) resultList.add(homeVisit);
			}
		}
		return resultList;
	}
}
