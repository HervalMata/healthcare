package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Company;
import com.healthcare.repository.CompanyRepository;
import com.healthcare.service.CompanyService;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	private RedisTemplate<String, Company> companyRedisTemplate;

	private static String COMPANY_KEY = "Company";

	@Override
	public Company save(Company company) {
		company = companyRepository.save(company);
		companyRedisTemplate.opsForHash().put(COMPANY_KEY, company.getId(), company);
		return company;
	}

	@Override
	public void deleteById(Long id) {
		companyRepository.delete(id);
		companyRedisTemplate.opsForHash().delete(COMPANY_KEY, id);
	}

	@Override
	public Company findById(Long id) {
		Company company = (Company) companyRedisTemplate.opsForHash().get(COMPANY_KEY, id);
		if (company == null)
			company = companyRepository.findOne(id);
		return company;
	}

	@Override
	public List<Company> findAll() {
		Map<Object, Object> companyMap = companyRedisTemplate.opsForHash().entries(COMPANY_KEY);
		List<Company> companyList = Collections.arrayToList(companyMap.values().toArray());
		if (companyMap.isEmpty())
			companyList = companyRepository.findAll();
		return companyList;
	}
}
