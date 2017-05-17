package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.AgencyType;
import com.healthcare.repository.AgencyTypeRepository;
import com.healthcare.service.AgencyTypeService;

@Service
@Transactional
public class AgencyTypeServiceImpl implements AgencyTypeService {
	@Autowired
	AgencyTypeRepository agencyTypeRepository;

	@Override
	public AgencyType save(AgencyType agencyType) {
		return agencyTypeRepository.save(agencyType);
	}

	@Override
	public void deleteById(Long id) {
		agencyTypeRepository.delete(id);
	}

	@Override
	public AgencyType findById(Long id) {
		return agencyTypeRepository.findOne(id);
	}
}
