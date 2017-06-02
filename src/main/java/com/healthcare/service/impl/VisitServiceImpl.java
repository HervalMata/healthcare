package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Visit;
import com.healthcare.repository.VisitRepository;
import com.healthcare.service.VisitService;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {
	@Autowired
	public VisitRepository visitRepository;

	@Override
	public Visit save(Visit visit) {
		return visitRepository.save(visit);
	}

	@Override
	public Visit findById(Long id) {
		return visitRepository.findOne(id);
	}

	@Override
	public void deleteById(Long id) {
		visitRepository.delete(id);
	}

}
