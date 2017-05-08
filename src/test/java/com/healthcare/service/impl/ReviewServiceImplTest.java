package com.healthcare.service.impl;

import com.healthcare.model.entity.Review;
import com.healthcare.repository.ReviewRepository;
import com.healthcare.service.ReviewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.only;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceImplTest {

    private ReviewService sut;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        sut = new ReviewServiceImpl(reviewRepository);
    }

    @Test
    public void testSave() {
        // given
        final Review review = new Review();
        final Review expected = new Review();

        given(reviewRepository.save(any(Review.class)))
                .willReturn(expected);
        // when
        Review result = sut.save(review);
        // then
        verify(reviewRepository, only()).save(review);

        assertThat(result, notNullValue());
        assertThat(result, sameInstance(expected));
    }

    @Test
    public void testGet() {
        // given
        final Long reviewId = 1L;
        final Review expected = new Review();

        given(reviewRepository.getOne(anyLong()))
                .willReturn(expected);
        // when
        Review result = sut.get(reviewId);
        // then
        verify(reviewRepository, only()).getOne(reviewId);

        assertThat(result, notNullValue());
        assertThat(result, sameInstance(expected));
    }

    @Test
    public void testDelete() {
        // given
        final Long reviewId = 1L;
        // when
        sut.delete(reviewId);
        // then
        verify(reviewRepository, only()).delete(reviewId);
    }
}
