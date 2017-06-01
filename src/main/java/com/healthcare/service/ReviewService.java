package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Review;

public interface ReviewService extends IService<Review> {	
	List<Review> findAll();
}
