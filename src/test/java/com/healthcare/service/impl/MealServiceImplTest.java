package com.healthcare.service.impl;

import com.healthcare.model.entity.Meal;
import com.healthcare.repository.MealRepository;
import com.healthcare.service.MealService;
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
public class MealServiceImplTest {

    private MealService sut;

    @Mock
    private MealRepository mealRepository;

    @Before
    public void setUp() {
        sut = new MealServiceImpl(mealRepository);
    }

    @Test
    public void testSave() {
        // given
        final Meal meal = new Meal();
        final Meal expected = new Meal();

        given(mealRepository.save(any(Meal.class)))
                .willReturn(expected);
        // when
        Meal result = sut.save(meal);
        // then
        verify(mealRepository, only()).save(meal);

        assertThat(result, notNullValue());
        assertThat(result, sameInstance(expected));
    }

    @Test
    public void testGet() {
        // given
        final Long mealId = 1L;
        final Meal expected = new Meal();

        given(mealRepository.getOne(anyLong()))
                .willReturn(expected);
        // when
        Meal result = sut.get(mealId);
        // then
        verify(mealRepository, only()).getOne(mealId);

        assertThat(result, notNullValue());
        assertThat(result, sameInstance(expected));
    }

    @Test
    public void testDelete() {
        // given
        final Long mealId = 1L;
        // when
        sut.delete(mealId);
        // then
        verify(mealRepository, only()).delete(mealId);
    }
}
