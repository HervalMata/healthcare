package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Agency;
import com.healthcare.repository.AgencyRepository;
import com.healthcare.service.AgencyService;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {
	@Autowired
	AgencyRepository agencyRepository;

	@Override
	public Agency save(Agency agency) {
		return agencyRepository.save(agency);
	}

	@Override
	public void deleteById(Long id) {
		agencyRepository.delete(id);
	}

	@Override
	public Agency findById(Long id) {
		return agencyRepository.findOne(id);
	}
}
