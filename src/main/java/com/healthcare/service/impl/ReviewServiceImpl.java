package com.healthcare.service.impl;

import com.healthcare.model.entity.Review;
import com.healthcare.repository.ReviewRepository;
import com.healthcare.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static org.springframework.util.Assert.notNull;

/**
 * Review service
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Nonnull
    @Override
    public Review save(@Nonnull Review review) {
        notNull(review, "Review must not be null");

        return reviewRepository.save(review);
    }

    @Nullable
    @Override
    public Review get(@Nonnull Long id) {
        notNull(id, "Review Id must not be null");

        return reviewRepository.getOne(id);
    }

    @Override
    public void delete(@Nonnull Long id) {
        notNull(id, "Review Id must not be null");

        reviewRepository.delete(id);
    }
}
