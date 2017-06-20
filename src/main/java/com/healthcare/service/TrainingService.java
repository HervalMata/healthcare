package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Training;

public interface TrainingService extends IService<Training> {
	Training save(Training training);

	List<Training> findAll();
}
