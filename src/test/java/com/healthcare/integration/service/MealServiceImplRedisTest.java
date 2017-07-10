package com.healthcare.integration.service;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.Meal;
import com.healthcare.repository.MealRepository;
import com.healthcare.service.MealService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealServiceImplRedisTest {

    @Autowired
    private MealService sut;

    @MockBean
    private MealRepository mealRepository;


	private Long id = 1L;

	@After
	public void rollback() {
		sut.deleteById(id);
	}

    @Test
    public void testCreate() {
        // given
        final Long mealId = id;
        final Meal meal = new Meal();
        meal.setId(mealId);

        given(mealRepository.save(any(Meal.class)))
                .willReturn(meal);
        // when
        sut.save(meal);
        // then
        verify(mealRepository, only()).save(meal);

        Meal result = sut.findById(meal.getId());

        assertThat(result, notNullValue());
    }

    @Test
    public void testUpdate() {
        // given
        final Long mealId = id;
        final String name = "Meal name";
        final Meal meal = new Meal();
        meal.setId(mealId);

        given(mealRepository.save(any(Meal.class)))
                .willReturn(meal);
        sut.save(meal);
        meal.setName(name);
        // when
        sut.save(meal);
        // then
        verify(mealRepository, atLeast(1)).save(meal);

        Meal result = sut.findById(meal.getId());

        assertThat(result, notNullValue());
        assertThat(result.getName(), equalTo(name));
    }

    @Test
    public void testDeleteById() {
        // given
        final Long mealId = id;
        final Meal meal = new Meal();
        meal.setId(mealId);

        given(mealRepository.save(any(Meal.class)))
                .willReturn(meal);
        sut.save(meal);
        // when
        Long result = sut.deleteById(meal.getId());
        // then
        verify(mealRepository).delete(meal.getId());
        assertThat(result, notNullValue());

        Meal savedMeal = sut.findById(meal.getId());

        assertThat(savedMeal, nullValue());
    }
}
