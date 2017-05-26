package com.healthcare.integration.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.healthcare.DbUnitIntegrationTestConfiguration;
import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Employee;
import com.healthcare.service.ActivityService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@TestExecutionListeners(
        value = {DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@DatabaseSetup(value = "/dataset/service/ActivityServiceImplIntegrationTest.xml")
@ContextConfiguration(classes = {DbUnitIntegrationTestConfiguration.class})
@Transactional
@SpringBootTest
public class ActivityServiceImplRedisIntegrationTest {

    private static final String REDIS_KEY = Activity.class.getSimpleName();

    @Autowired
    private ActivityService sut;

    @Autowired
    private RedisTemplate<String, Activity> redisTemplate;

    @Before
    public void setUp() {
        redisTemplate.delete(REDIS_KEY);
    }

    @After
    public void tearDown() {
        redisTemplate.delete(REDIS_KEY);
    }

    @Test
    public void testCreate() {
        // given
        final Activity activity = getActivity();
        // when
        Activity result = sut.save(activity);
        // then
        assertThat(result, notNullValue());
        assertThat(result.getId(), notNullValue());

        // when
        Object redisResult = redisTemplate.opsForHash().get(REDIS_KEY, result.getId());
        // then
        assertThat(redisResult, notNullValue());
        assertThat(redisResult, equalTo(result));
    }

    @Test
    public void testGet() {
        // given
        final Long activityId = 100L;
        Activity result = loadActivityInRedis(activityId);
        // when
        sut.get(activityId);
        // then

        // when
        Object redisResult = redisTemplate.opsForHash().get(REDIS_KEY, activityId);
        // then
        assertThat(redisResult, notNullValue());
        assertThat(redisResult, equalTo(result));
    }

    @Test
    public void testDelete() {
        // given
        final Long activityId = 100L;
        loadActivityInRedis(activityId);
        // when
        sut.delete(activityId);
        // then

        // when
        Object redisResult = redisTemplate.opsForHash().get(REDIS_KEY, activityId);
        // then
        assertThat(redisResult, nullValue());
    }

    private Activity getActivity() {
        final Activity activity = new Activity();
        activity.setName("Name");

        final Long employeeId = 100L;
        final Employee employee = new Employee();
        employee.setId(employeeId);
        activity.setInstructorEmployee(employee);

        return activity;
    }

    private Activity loadActivityInRedis(Long activityId) {
        final Activity result = getActivity();
        result.setId(activityId);

        redisTemplate.opsForHash().put(REDIS_KEY, activityId, result);

        return result;
    }
}