package com.healthcare.service;

import com.healthcare.model.entity.Employee;

/**
 * Created by Jean Antunes on 11/05/17.
 */
public interface EmployeeService {
    
    Employee save(Employee employee);

    Employee findById(Long id);

    void deleteById(Long id);
}
