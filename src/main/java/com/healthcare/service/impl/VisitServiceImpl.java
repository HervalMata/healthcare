package com.healthcare.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.api.model.VisitRequest;
import com.healthcare.exception.ApplicationException;
import com.healthcare.model.entity.User;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.enums.VisitStatusEnum;
import com.healthcare.model.response.Response;
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

	@Override
	public Visit checkIn(VisitRequest visitRequest) {
		Visit visit = null;
		if (visitRequest.getId() != null && visitRequest.getId()>0) {
			visit = findById(visitRequest.getId());
		} else {
			visit = visitRepository.findByUserBarcodeId(visitRequest.getUserBarcodeId());
		}
		
		// if no visit found
		if(visit==null){
			throw new ApplicationException(Response.ResultCode.VISIT_NOT_FOUND, "Visit not found");
		}
		
		// check in
		if (VisitStatusEnum.BOOKED.equals(VisitStatusEnum.valueOf(visit.getStatus()))) {
			visit.setCheckInTime(new Timestamp(new Date().getTime()));
			visit.setStatus(VisitStatusEnum.REGISTERED.name());
		}
		else
		{
			throw new ApplicationException(Response.ResultCode.INVALID_STATUS, "Invalid visit status");
		}
		// save visit
		return save(visit);
	}

	@Override
	public Visit checkOut(VisitRequest visitRequest) {
		Visit visit = null;
		if (visitRequest.getId() != null && visitRequest.getId()>0) {
			visit = findById(visitRequest.getId());
		} else {
			visit = visitRepository.findByUserBarcodeId(visitRequest.getUserBarcodeId());
		}
		// if no visit found
		if(visit==null){
			throw new ApplicationException(Response.ResultCode.VISIT_NOT_FOUND, "Visit not found");
		}

		// check out
		if (VisitStatusEnum.REGISTERED.equals(VisitStatusEnum.valueOf(visit.getStatus()))) {
			visit.setCheckOutTime(new Timestamp(new Date().getTime()));
			visit.setStatus(VisitStatusEnum.FINISHED.name());
		}
		else
		{
			throw new ApplicationException(Response.ResultCode.INVALID_STATUS, "Invalid visit status");
		}
		// save visit
		return save(visit);
	}
}
