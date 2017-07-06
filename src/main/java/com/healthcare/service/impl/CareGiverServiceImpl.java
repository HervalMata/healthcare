package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Caregiver;
import com.healthcare.repository.CareGiverRepository;
import com.healthcare.service.CareGiverService;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class CareGiverServiceImpl implements CareGiverService {
	private static final String KEY = Caregiver.class.getSimpleName();

	@Autowired
	CareGiverRepository careGiverRepository;

	@Autowired
	private RedisTemplate<String, Caregiver> careGiverRedisTemplate;

	@Override
	public Caregiver save(Caregiver careGiver) {
		careGiver = careGiverRepository.save(careGiver);
		careGiverRedisTemplate.opsForHash().put(KEY, careGiver.getId(), careGiver);
		return careGiver;
	}

	@Override
	public Caregiver findById(Long id) {
		Caregiver careGiver = (Caregiver) careGiverRedisTemplate.opsForHash().get(KEY, id);
		if (careGiver == null)
			careGiver = careGiverRepository.findOne(id);
		return careGiver;
	}

	@Override
	public Long deleteById(Long id) {
		careGiverRepository.delete(id);
		return careGiverRedisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public List<Caregiver> findAll() {
		Map<Object, Object> careGiverMap = careGiverRedisTemplate.opsForHash().entries(KEY);
		List<Caregiver> careGiverList = Collections.arrayToList(careGiverMap.values().toArray());
		if (careGiverMap.isEmpty())
			careGiverList = careGiverRepository.findAll();
		return careGiverList;
	}
}
