package com.healthcare.api;

import com.healthcare.model.entity.Review;
import com.healthcare.service.ReviewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.internal.verification.VerificationModeFactory.only;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        ReviewController controller = new ReviewController(reviewService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCreate() throws Exception {
        // given
        final Integer reviewContent = 2;
        final Long reviewId = 1L;
        final Review review = new Review();
        review.setContent(reviewContent);
        final Review expected = new Review();
        expected.setId(reviewId);

        given(reviewService.save(any(Review.class)))
                .willReturn(expected);
        // when
        mockMvc.perform(
                    post("/api/review")
                        .param("content", reviewContent.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(reviewId.toString()));
        // then
        verify(reviewService, only()).save(review);
    }

    @Test
    public void testGet() throws Exception {
        // given
        final Long reviewId = 1L;
        final Review review = new Review();
        review.setId(reviewId);
        final String expectedContent = "{" +
                    "\"createdAt\":null," +
                    "\"updatedAt\":null," +
                    "\"id\":1," +
                    "\"user\":null," +
                    "\"employee\":null," +
                    "\"affectStart\":null," +
                    "\"affectEnd\":null," +
                    "\"content\":null," +
                    "\"user1\":null" +
                "}";

        given(reviewService.get(anyLong()))
                .willReturn(review);
        // when
        mockMvc.perform(
                    get("/api/review/" + reviewId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent));
        // then
        verify(reviewService, only()).get(reviewId);
    }

    @Test
    public void testGetWithIncorrectIdReturnBadRequestStatus() throws Exception {
        // given
        final String reviewId = "abc";
        // given
        mockMvc.perform(
                    get("/api/review/" + reviewId))
                .andExpect(status().isBadRequest());
        // then
        verifyZeroInteractions(reviewService);
    }

    @Test
    public void testSave() throws Exception {
        // given
        final Integer reviewContent = 2;
        final Long reviewId = 1L;
        final Review review = new Review();
        review.setId(reviewId);
        review.setContent(reviewContent);

        given(reviewService.save(any(Review.class)))
                .willReturn(review);
        // when
        mockMvc.perform(
                    post("/api/review/" + reviewId)
                        .param("id", reviewId.toString())
                        .param("content", reviewContent.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyString()));
        // then
        verify(reviewService, only()).save(review);
    }

    @Test
    public void testSaveWithIncorrectIdReturnBadRequestStatus() throws Exception {
        // given
        final Integer reviewContent = 2;
        final String reviewId = "abc";
        // when
        mockMvc.perform(
                    post("/api/review/" + reviewId)
                        .param("id", reviewId)
                        .param("name", reviewContent.toString()))
                .andExpect(status().isBadRequest());
        // then
        verifyZeroInteractions(reviewService);
    }

    @Test
    public void testDelete() throws Exception {
        // given
        final Long reviewId = 1L;
        // when
        mockMvc.perform(
                    delete("/api/review/" + reviewId))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyString()));
        // then
        verify(reviewService, only()).delete(reviewId);
    }

    @Test
    public void testDeleteWithIncorrectIdReturnBadRequestStatus() throws Exception {
        // given
        final String reviewId = "abc";
        // when
        mockMvc.perform(
                    delete("/api/review/" + reviewId))
                .andExpect(status().isBadRequest());
        // then
        verifyZeroInteractions(reviewService);
    }
}
