package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.AgencyType;
import com.healthcare.repository.AgencyTypeRepository;
import com.healthcare.service.AgencyTypeService;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class AgencyTypeServiceImpl implements AgencyTypeService {
	@Autowired
	AgencyTypeRepository agencyTypeRepository;

	@Autowired
	private RedisTemplate<String, AgencyType> agencyTypeRedisTemplate;

	private static String AGENCYTYPE_KEY = "AgencyType";

	@Override
	public AgencyType save(AgencyType agencyType) {
		agencyType = agencyTypeRepository.save(agencyType);
		agencyTypeRedisTemplate.opsForHash().put(AGENCYTYPE_KEY, agencyType.getId(), agencyType);
		return agencyType;
	}

	@Override
	public void deleteById(Long id) {
		agencyTypeRepository.delete(id);
		agencyTypeRedisTemplate.opsForHash().delete(AGENCYTYPE_KEY, id);
	}

	@Override
	public AgencyType findById(Long id) {
		AgencyType agencyType = (AgencyType) agencyTypeRedisTemplate.opsForHash().get(AGENCYTYPE_KEY, id);
		if (agencyType == null)
			agencyType = agencyTypeRepository.findOne(id);
		return agencyType;
	}

	@Override
	public List<AgencyType> findAll() {
		Map<Object, Object> agencyTypeMap = agencyTypeRedisTemplate.opsForHash().entries(AGENCYTYPE_KEY);
		List<AgencyType> agencyTypeList = Collections.arrayToList(agencyTypeMap.values().toArray());
		if (agencyTypeMap.isEmpty())
			agencyTypeList = agencyTypeRepository.findAll();
		return agencyTypeList;
	}
}