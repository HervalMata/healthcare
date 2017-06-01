package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Employee;

public interface EmployeeService extends IService<Employee> {	
	List<Employee> findAll();
}
