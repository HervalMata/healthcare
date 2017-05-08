package com.healthcare.service.impl;

import com.healthcare.model.entity.Activity;
import com.healthcare.repository.ActivityRepository;
import com.healthcare.service.ActivityService;
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
public class ActivityServiceImplTest {

    private ActivityService sut;

    @Mock
    private ActivityRepository activityRepository;

    @Before
    public void setUp() {
        sut = new ActivityServiceImpl(activityRepository);
    }

    @Test
    public void testSave() {
        // given
        final Activity activity = new Activity();
        final Activity expected = new Activity();

        given(activityRepository.save(any(Activity.class)))
                .willReturn(expected);
        // when
        Activity result = sut.save(activity);
        // then
        verify(activityRepository, only()).save(activity);

        assertThat(result, notNullValue());
        assertThat(result, sameInstance(expected));
    }

    @Test
    public void testGet() {
        // given
        final Long activityId = 1L;
        final Activity expected = new Activity();

        given(activityRepository.getOne(anyLong()))
                .willReturn(expected);
        // when
        Activity result = sut.get(activityId);
        // then
        verify(activityRepository, only()).getOne(activityId);

        assertThat(result, notNullValue());
        assertThat(result, sameInstance(expected));
    }

    @Test
    public void testDelete() {
        // given
        final Long activityId = 1L;
        // when
        sut.delete(activityId);
        // then
        verify(activityRepository, only()).delete(activityId);
    }
}
