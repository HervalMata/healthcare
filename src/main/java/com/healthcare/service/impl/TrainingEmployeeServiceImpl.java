package com.healthcare.service.impl;

import com.healthcare.model.entity.TrainingEmployee;
import com.healthcare.repository.TrainingEmployeeRepository;
import com.healthcare.service.TrainingEmployeeService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class TrainingEmployeeServiceImpl implements TrainingEmployeeService {
    private static final String KEY = TrainingEmployee.class.getSimpleName();

    @Autowired
    TrainingEmployeeRepository trainingEmployeeRepository;

    @Autowired
    private RedisTemplate<String, TrainingEmployee> trainingEmployeeRedisTemplate;


    @Override
    public TrainingEmployee save(TrainingEmployee trainingEmployee) {
        trainingEmployee = trainingEmployeeRepository.save(trainingEmployee);
        trainingEmployeeRedisTemplate.opsForHash().put(KEY, trainingEmployee, trainingEmployee);
        return trainingEmployee;
    }

    @Override
    public TrainingEmployee findById(Long id) {
        TrainingEmployee trainingEmployee = (TrainingEmployee) trainingEmployeeRedisTemplate.opsForHash().get(KEY, id);
        if (trainingEmployee == null)
            trainingEmployee = trainingEmployeeRepository.findOne(id);
        return trainingEmployee;
    }

    @Override
    public Long deleteById(Long id) {
        trainingEmployeeRepository.delete(id);
        return trainingEmployeeRedisTemplate.opsForHash().delete(KEY, id);
    }

    public Long deleteByTrainingEmployee(TrainingEmployee trainingEmployee) {
        trainingEmployeeRepository.delete(trainingEmployee);
        return trainingEmployeeRedisTemplate.opsForHash().delete(KEY, trainingEmployee);
    }

    @Override
    public Long findByTrainingEmployee(TrainingEmployee trainingEmployee) {
        trainingEmployeeRedisTemplate.opsForHash().get(KEY, trainingEmployee);
        if (trainingEmployee == null)
            trainingEmployee = trainingEmployeeRepository.findOne(trainingEmployee.getId());
        return trainingEmployee.getId();
    }

    @Override
    public List<TrainingEmployee> findAll() {
        Map<Object, Object> trainingEmployeeMap = trainingEmployeeRedisTemplate.opsForHash().entries(KEY);
        List<TrainingEmployee> trainingEmployeeList = Collections.arrayToList(trainingEmployeeMap.values().toArray());
        if (trainingEmployeeMap.isEmpty())
            trainingEmployeeList = trainingEmployeeRepository.findAll();
        return trainingEmployeeList;
    }
}
