package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Employee;

/**
 * Created by Jean Antunes on 11/05/17.
 */
public interface EmployeeService extends IService<Employee> {

	Employee save(Employee employee);

	List<Employee> findAll();
}
