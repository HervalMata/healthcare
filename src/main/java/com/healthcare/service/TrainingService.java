package com.healthcare.service;

import com.healthcare.model.entity.Training;

import java.util.List;

/**
 * Created by jean on 23/05/17.
 */
public interface TrainingService extends IService<Training> {
    String KEY = Training.class.getSimpleName();

    List<Training> findAll();
}
