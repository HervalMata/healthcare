package com.healthcare.service.impl;

import com.healthcare.model.entity.Employee;
import com.healthcare.repository.EmployeeRepository;
import com.healthcare.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Jean Antunes on 11/05/17.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee getFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }
}
