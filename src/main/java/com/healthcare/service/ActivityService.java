package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Activity;

/**
 * Activity service methods
 */
public interface ActivityService extends IService<Activity> {
	List<Activity> findAll();
}
