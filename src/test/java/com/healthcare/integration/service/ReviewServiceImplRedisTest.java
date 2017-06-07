package com.healthcare.integration.service;

import com.healthcare.model.entity.Review;
import com.healthcare.repository.ReviewRepository;
import com.healthcare.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.only;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewServiceImplRedisTest {

    @Autowired
    private ReviewService sut;

    @MockBean
    private ReviewRepository reviewRepository;

    @Test
    public void testCreate() {
        // given
        final Long reviewId = 1L;
        final Review review = new Review();
        review.setId(reviewId);

        given(reviewRepository.save(any(Review.class)))
                .willReturn(review);
        // when
        sut.save(review);
        // then
        verify(reviewRepository, only()).save(review);

        Review result = sut.findById(review.getId());

        assertThat(result, notNullValue());
    }

    @Test
    public void testUpdate() {
        // given
        final Long reviewId = 1L;
        final Integer content = 2;
        final Review review = new Review();
        review.setId(reviewId);

        given(reviewRepository.save(any(Review.class)))
                .willReturn(review);
        sut.save(review);
        review.setContent(content);
        // when
        sut.save(review);
        // then
        verify(reviewRepository, atLeast(1)).save(review);

        Review result = sut.findById(review.getId());

        assertThat(result, notNullValue());
        assertThat(result.getContent(), equalTo(content));
    }

    @Test
    public void testDeleteById() {
        // given
        final Long reviewId = 1L;
        final Review review = new Review();
        review.setId(reviewId);

        given(reviewRepository.save(any(Review.class)))
                .willReturn(review);
        sut.save(review);
        // when
        Long result = sut.deleteById(review.getId());
        // then
        verify(reviewRepository).delete(review.getId());
        assertThat(result, notNullValue());

        Review savedReview = sut.findById(review.getId());

        assertThat(savedReview, nullValue());
    }
}
