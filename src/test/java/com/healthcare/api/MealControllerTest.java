package com.healthcare.api;

import com.healthcare.model.entity.Meal;
import com.healthcare.service.MealService;
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
public class MealControllerTest {

    @Mock
    private MealService mealService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MealController controller = new MealController(mealService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCreate() throws Exception {
        // given
        final String mealName = "Meal name";
        final Long mealId = 1L;
        final Meal meal = new Meal();
        meal.setName(mealName);
        final Meal expected = new Meal();
        expected.setId(mealId);

        given(mealService.save(any(Meal.class)))
                .willReturn(expected);
        // when
        mockMvc.perform(
                    post("/api/meal")
                        .param("name", mealName))
                .andExpect(status().isOk())
                .andExpect(content().string(mealId.toString()));
        // then
        verify(mealService, only()).save(meal);
    }

    @Test
    public void testGet() throws Exception {
        // given
        final Long mealId = 1L;
        final Meal meal = new Meal();
        meal.setId(mealId);
        final String expectedContent = "{" +
                    "\"createdAt\":null," +
                    "\"updatedAt\":null," +
                    "\"id\":" + mealId + "," +
                    "\"mealClass\":null," +
                    "\"name\":null," +
                    "\"ingredients\":null," +
                    "\"notes\":null," +
                    "\"verifiedByNutritionist\":null," +
                    "\"status\":null" +
                "}";

        given(mealService.get(anyLong()))
                .willReturn(meal);
        // when
        mockMvc.perform(
                    get("/api/meal/" + mealId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent));
        // then
        verify(mealService, only()).get(mealId);
    }

    @Test
    public void testGetWithIncorrectIdReturnBadRequestStatus() throws Exception {
        // given
        final String mealId = "abc";
        // given
        mockMvc.perform(
                    get("/api/meal/" + mealId))
                .andExpect(status().isBadRequest());
        // then
        verifyZeroInteractions(mealService);
    }

    @Test
    public void testSave() throws Exception {
        // given
        final String mealName = "Meal name";
        final Long mealId = 1L;
        final Meal meal = new Meal();
        meal.setId(mealId);
        meal.setName(mealName);

        given(mealService.save(any(Meal.class)))
                .willReturn(meal);
        // when
        mockMvc.perform(
                    post("/api/meal/" + mealId)
                        .param("id", mealId.toString())
                        .param("name", mealName))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyString()));
        // then
        verify(mealService, only()).save(meal);
    }

    @Test
    public void testSaveWithIncorrectIdReturnBadRequestStatus() throws Exception {
        // given
        final String mealName = "Meal name";
        final String mealId = "abc";
        // when
        mockMvc.perform(
                post("/api/meal/" + mealId)
                        .param("id", mealId)
                        .param("name", mealName))
                .andExpect(status().isBadRequest());
        // then
        verifyZeroInteractions(mealService);
    }

    @Test
    public void testDelete() throws Exception {
        // given
        final Long mealId = 1L;
        // when
        mockMvc.perform(
                    delete("/api/meal/" + mealId))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyString()));
        // then
        verify(mealService, only()).delete(mealId);
    }

    @Test
    public void testDeleteWithIncorrectIdReturnBadRequestStatus() throws Exception {
        // given
        final String mealId = "abc";
        // when
        mockMvc.perform(
                delete("/api/meal/" + mealId))
                .andExpect(status().isBadRequest());
        // then
        verifyZeroInteractions(mealService);
    }
}
