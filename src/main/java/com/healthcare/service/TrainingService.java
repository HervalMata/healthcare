package com.healthcare.service;

import com.healthcare.model.entity.Training;

import java.util.List;

public interface TrainingService extends IService<Training> {
    Training save(Training training);

    List<Training> findAll();
}
