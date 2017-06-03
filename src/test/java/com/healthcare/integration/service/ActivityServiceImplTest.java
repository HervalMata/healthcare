package com.healthcare.integration.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.healthcare.DbUnitIntegrationTestConfiguration;
import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Employee;
import com.healthcare.service.ActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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
public class ActivityServiceImplTest {

    @Autowired
    private ActivityService sut;

    @Autowired
    private EntityManager em;

    @Test
    public void testCreate() {
        // given
        final Long employeeId = 100L;
        final Activity activity = new Activity();
        activity.setName("Name");

        final Employee employee = new Employee();
        employee.setId(employeeId);
        activity.setInstructorEmployee(employee);
        // when
        Activity result = sut.save(activity);
        // then
        assertThat(result, notNullValue());
        assertThat(result.getId(), notNullValue());
    }

    @Test
    @ExpectedDatabase(
            value = "/dataset/service/ActivityServiceImplIntegrationTest.testUpdate.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    public void testUpdate() {
        // given
        final Long activityId = 100L;
        final Long employeeId = 100L;
        final Activity activity = new Activity();
        activity.setId(activityId);
        activity.setName("Name 1");

        final Employee employee = new Employee();
        employee.setId(employeeId);
        activity.setInstructorEmployee(employee);
        // when
        Activity result = sut.save(activity);
        // then
        assertThat(result, notNullValue());

        em.flush();
    }

    @Test
    public void testFindById() {
        // given
        final Long activityId = 100L;
        // when
        Activity result = sut.findById(activityId);
        // then
        assertThat(result, notNullValue());
    }

    @Test
    @ExpectedDatabase(
            value = "/dataset/service/ActivityServiceImplIntegrationTest.testDelete.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    public void testDeleteById() {
        // given
        final Long activityId = 100L;
        // when
        sut.deleteById(activityId);
        // then
        em.flush();
    }
}