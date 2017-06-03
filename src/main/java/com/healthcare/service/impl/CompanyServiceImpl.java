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
	private static final String KEY = Company.class.getSimpleName();

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	private RedisTemplate<String, Company> companyRedisTemplate;

	@Override
	public Company save(Company company) {
		company = companyRepository.save(company);
		companyRedisTemplate.opsForHash().put(KEY, company.getId(), company);
		return company;
	}

	@Override
	public Long deleteById(Long id) {
		companyRepository.delete(id);
		return companyRedisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public Company findById(Long id) {
		if (companyRedisTemplate.opsForHash().hasKey(KEY, id))
			return (Company) companyRedisTemplate.opsForHash().get(KEY, id);
		return companyRepository.findOne(id);
	}

	@Override
	public List<Company> findAll() {
		Map<Object, Object> companyMap = companyRedisTemplate.opsForHash().entries(KEY);
		List<Company> companyList = Collections.arrayToList(companyMap.values().toArray());
		if (companyMap.isEmpty())
			companyList = companyRepository.findAll();
		return companyList;
	}
}
