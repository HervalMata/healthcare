package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Review;
import com.healthcare.repository.ReviewRepository;
import com.healthcare.service.ReviewService;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	private static final String KEY = Review.class.getSimpleName();

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	private RedisTemplate<String, Review> reviewRedisTemplate;

	@Override
	public Review save(Review review) {
		review = reviewRepository.save(review);
		reviewRedisTemplate.opsForHash().put(KEY, review.getId(), review);
		return review;
	}

	@Override
	public void deleteById(Long id) {
		reviewRepository.delete(id);
		reviewRedisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public Review findById(Long id) {
		if (reviewRedisTemplate.opsForHash().hasKey(KEY, id)) {
			return (Review) reviewRedisTemplate.opsForHash().get(KEY, id);
		}
		return reviewRepository.findOne(id);
	}

	@Override
	public List<Review> findAll() {
		Map<Object, Object> reviewMap = reviewRedisTemplate.opsForHash().entries(KEY);
		List<Review> reviewList = Collections.arrayToList(reviewMap.values().toArray());
		if (reviewMap.isEmpty())
			reviewList = reviewRepository.findAll();
		return reviewList;
	}
}
