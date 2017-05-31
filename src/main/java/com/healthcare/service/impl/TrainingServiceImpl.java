package com.healthcare.service.impl;

import com.healthcare.model.entity.Training;
import com.healthcare.repository.TrainingRepository;
import com.healthcare.service.TrainingService;
import io.jsonwebtoken.lang.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static sun.security.x509.CertificateX509Key.KEY;

/**
 * Created by Jean Antunes on 24/05/17.
 */

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    private RedisTemplate<String, Training> trainingRedisTemplate;


    @Override
    public Training save(Training user) {
        user = trainingRepository.save(user);
        trainingRedisTemplate.opsForHash().put(KEY, user.getId(), user);
        return user;
    }

    @Override
    public Training findById(Long id) {
        Training training = (Training) trainingRedisTemplate.opsForHash().get(KEY, id);
        if (training == null)
            training = trainingRepository.findOne(id);
        return training;
    }

    @Override
    public void deleteById(Long id) {
        trainingRepository.delete(id);
        trainingRedisTemplate.opsForHash().delete(KEY, id);
    }

    @Override
    public List<Training> findAll() {
        Map<Object, Object> trainingMap = trainingRedisTemplate.opsForHash().entries(KEY);
        List<Training> trainingList = Collections.arrayToList(trainingMap.values().toArray());
        if (trainingMap.isEmpty())
            trainingList = trainingRepository.findAll();
        return trainingList;
    }
}
