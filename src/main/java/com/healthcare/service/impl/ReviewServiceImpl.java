package com.healthcare.service.impl;

import com.healthcare.model.entity.Review;
import com.healthcare.repository.ReviewRepository;
import com.healthcare.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.transaction.Transactional;

import static org.springframework.util.Assert.notNull;

/**
 * Review service
 */
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private static final String REDIS_KEY = Review.class.getSimpleName();

    private ReviewRepository reviewRepository;
    private RedisTemplate<String, Review> redisTemplate;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, RedisTemplate<String, Review> redisTemplate) {
        this.reviewRepository = reviewRepository;
        this.redisTemplate = redisTemplate;
    }

    @Nonnull
    @Override
    public Review save(@Nonnull Review review) {
        notNull(review, "Review must not be null");

        Review savedReview = reviewRepository.save(review);
        redisTemplate.opsForHash().put(REDIS_KEY, savedReview.getId(), savedReview);

        return savedReview;
    }

    @Nullable
    @Override
    public Review get(@Nonnull Long id) {
        notNull(id, "Review Id must not be null");

        Object review = redisTemplate.opsForHash().get(REDIS_KEY, id);
        if (review != null) {
            return ((Review) review);
        }

        return reviewRepository.findOne(id);
    }

    @Override
    public void delete(@Nonnull Long id) {
        notNull(id, "Review Id must not be null");

        reviewRepository.delete(id);
        redisTemplate.opsForHash().delete(REDIS_KEY, id);
    }
}
