package com.healthcare.integration.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.healthcare.DbUnitIntegrationTestConfiguration;
import com.healthcare.model.entity.Meal;
import com.healthcare.model.entity.Visit;
import com.healthcare.service.MealService;
import org.hamcrest.CoreMatchers;
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

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@TestExecutionListeners(
        value = {DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@DatabaseSetup(value = "/dataset/service/MealServiceImplIntegrationTest.xml")
@ContextConfiguration(classes = {DbUnitIntegrationTestConfiguration.class})
@Transactional
@SpringBootTest
public class MealServiceImplRedisIntegrationTest {

    private static final String REDIS_KEY = Meal.class.getSimpleName();

    @Autowired
    private MealService sut;

    @Autowired
    private RedisTemplate<String, Meal> redisTemplate;

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
        final Meal meal = getMeal();
        // when
        Meal result = sut.save(meal);
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
    public void testFindById() {
        // given
        final Long mealId = 100L;
        final Meal result = loadMealInRedis(mealId);
        // when
        sut.findById(mealId);
        // then

        // when
        Object redisResult = redisTemplate.opsForHash().get(REDIS_KEY, mealId);
        // then
        assertThat(redisResult, notNullValue());
        assertThat(redisResult, equalTo(result));

    }

    @Test
    public void testDeleteById() {
        // given
        final Long mealId = 100L;
        loadMealInRedis(mealId);
        // when
        sut.deleteById(mealId);
        // then

        // when
        Object redisResult = redisTemplate.opsForHash().get(REDIS_KEY, mealId);
        // then
        assertThat(redisResult, nullValue());
    }

    private Meal getMeal() {
        final Meal meal = new Meal();
        meal.setMealClass("Class");
        meal.setName("Name");

        final Long visitId = 100L;
        final Visit visit = new Visit();
        visit.setId(visitId);
        meal.setVisit(visit);

        return meal;
    }

    private Meal loadMealInRedis(Long mealId) {
        final Meal result = getMeal();
        result.setId(mealId);

        redisTemplate.opsForHash().put(REDIS_KEY, mealId, result);

        return result;
    }
}
