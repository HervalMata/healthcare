package com.healthcare.integration.service;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.Activity;
import com.healthcare.repository.ActivityRepository;
import com.healthcare.service.ActivityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityServiceImplRedisTest {

	@Autowired
	private ActivityService sut;

	@MockBean
	private ActivityRepository activityRepository;

	@Test
	public void testCreate() {
		// given
		final Long activityId = 1L;
		final Activity activity = new Activity();
		activity.setId(activityId);

		given(activityRepository.save(any(Activity.class))).willReturn(activity);
		// when
		sut.save(activity);
		// then
		verify(activityRepository, only()).save(activity);

		Activity result = sut.findById(activity.getId());

		assertThat(result, notNullValue());
	}

	@Test
	public void testFindAll() {
		// given
		final Long activityId = 1L;
		final Activity activity = new Activity();
		activity.setId(activityId);

		given(activityRepository.save(any(Activity.class))).willReturn(activity);
		// when
		sut.save(activity);
		// then
		verify(activityRepository, only()).save(activity);

		List<Activity> result = sut.findAll();

		assertTrue(result.size() > 0);
	}

	@Test
	public void testUpdate() {
		// given
		final Long activityId = 1L;
		final String name = "Activity name";
		final Activity activity = new Activity();
		activity.setId(activityId);

		given(activityRepository.save(any(Activity.class))).willReturn(activity);
		sut.save(activity);
		activity.setName(name);
		// when
		sut.save(activity);
		// then
		verify(activityRepository, atLeast(1)).save(activity);

		Activity result = sut.findById(activity.getId());

		assertThat(result, notNullValue());
		assertThat(result.getName(), IsEqual.equalTo(name));
	}

	@Test
	public void testDeleteById() {
		// given
		final Long activityId = 1L;
		final Activity activity = new Activity();
		activity.setId(activityId);

		given(activityRepository.save(any(Activity.class))).willReturn(activity);
		sut.save(activity);
		// when
		Long result = sut.deleteById(activity.getId());
		// then
		verify(activityRepository).delete(activity.getId());
		assertThat(result, notNullValue());

		Activity savedActivity = sut.findById(activity.getId());

		assertThat(savedActivity, nullValue());
	}
}
