package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Company;
import com.healthcare.repository.CompanyRepository;
import com.healthcare.service.CompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyRepository companyRepository;

	@Override
	public Company save(Company menu) {
		return companyRepository.save(menu);
	}

	@Override
	public void deleteById(Long id) {
		companyRepository.delete(id);
	}

	@Override
	public Company findById(Long id) {
		return companyRepository.findOne(id);
	}
}
