package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Employee;
import com.healthcare.repository.EmployeeRepository;
import com.healthcare.service.EmployeeService;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private static final String KEY = Employee.class.getSimpleName();

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private RedisTemplate<String, Employee> employeeRedisTemplate;

	@Override
	public Employee save(Employee employee) {
		employee = employeeRepository.save(employee);
		employeeRedisTemplate.opsForHash().put(KEY, employee.getId(), employee);
		return employee;
	}

	@Override
	public Employee findById(Long id) {
		Employee employee = (Employee) employeeRedisTemplate.opsForHash().get(KEY, id);
		if (employee == null)
			employee = employeeRepository.findOne(id);
		return employee;
	}

	@Override
	public Long deleteById(Long id) {
		employeeRepository.delete(id);
		return employeeRedisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public List<Employee> findAll() {
		Map<Object, Object> employeeMap = employeeRedisTemplate.opsForHash().entries(KEY);
		List<Employee> employeeList = Collections.arrayToList(employeeMap.values().toArray());
		if (employeeMap.isEmpty())
			employeeList = employeeRepository.findAll();
		return employeeList;
	}
}
