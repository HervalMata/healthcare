package com.healthcare.service.impl;

import com.healthcare.model.entity.EmployeeActivity;
import com.healthcare.repository.EmployeeActivityRepository;
import com.healthcare.service.EmployeeActivityService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class EmployeeActivityServiceImpl implements EmployeeActivityService {
    private static final String KEY = EmployeeActivity.class.getSimpleName();

    @Autowired
    EmployeeActivityRepository employeeActivityRepository;

    @Autowired
    private RedisTemplate<String, EmployeeActivity> employeeActivityRedisTemplate;


    @Override
    public EmployeeActivity save(EmployeeActivity employeeActivity) {
        employeeActivity = employeeActivityRepository.save(employeeActivity);
        employeeActivityRedisTemplate.opsForHash().put(KEY, employeeActivity.getId(), employeeActivity);
        return employeeActivity;
    }

    @Override
    public EmployeeActivity findById(Long id) {
        EmployeeActivity employeeActivity = (EmployeeActivity) employeeActivityRedisTemplate.opsForHash().get(KEY, id);
        if (employeeActivity == null)
            employeeActivity = employeeActivityRepository.findOne(id);
        return employeeActivity;
    }

    @Override
    public Long deleteById(Long id) {
        employeeActivityRepository.delete(id);
        return employeeActivityRedisTemplate.opsForHash().delete(KEY, id);
    }

    @Override
    public Long deleteByEmployeeActivity(EmployeeActivity employeeActivity) {
        employeeActivityRepository.delete(employeeActivity);
        return employeeActivityRedisTemplate.opsForHash().delete(KEY, employeeActivity.getId());
    }

    @Override
    public Long findByEmployeeActivity(EmployeeActivity employeeActivity) {
        employeeActivityRedisTemplate.opsForHash().get(KEY, employeeActivity);
        if (employeeActivity == null)
            employeeActivity = employeeActivityRepository.findOne(employeeActivity.getId());
        return employeeActivity.getId();
    }

    @Override
    public List<EmployeeActivity> findAll() {
        Map<Object, Object> employeeActivityMap = employeeActivityRedisTemplate.opsForHash().entries(KEY);
        List<EmployeeActivity> employeeActivityList = Collections.arrayToList(employeeActivityMap.values().toArray());
        if (employeeActivityMap.isEmpty())
            employeeActivityList = employeeActivityRepository.findAll();
        return employeeActivityList;
    }
}
