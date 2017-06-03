package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.User;
import com.healthcare.model.entity.Visit;
import com.healthcare.repository.VisitRepository;
import com.healthcare.service.VisitService;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {
	private static final String KEY = Visit.class.getSimpleName();

	@Autowired
	public VisitRepository visitRepository;

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Override
	public Visit save(Visit visit) {
		visit = visitRepository.save(visit);
		redisTemplate.opsForHash().put(KEY, visit.getId(), visit);
		return visit;
	}

	@Override
	public Visit findById(Long id) {
		if (redisTemplate.opsForHash().hasKey(KEY, id)) {
			return (Visit) redisTemplate.opsForHash().get(KEY, id);
		}
		return visitRepository.findOne(id);
	}

	@Override
	public Long deleteById(Long id) {
		visitRepository.delete(id);
		return redisTemplate.opsForHash().delete(KEY, id);
	}

}
