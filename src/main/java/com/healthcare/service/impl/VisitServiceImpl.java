package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Visit;
import com.healthcare.service.VisitService;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {

	@Override
	public Visit save(Visit visit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visit findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

}
