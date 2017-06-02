package com.healthcare.service;

import com.healthcare.model.entity.Employee;

import java.util.List;

/**
 * Created by Jean Antunes on 11/05/17.
 */
public interface EmployeeService extends IService<Employee> {

    Employee save(Employee employee);

    List<Employee> findAll();
}
