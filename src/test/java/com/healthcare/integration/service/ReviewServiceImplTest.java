package com.healthcare.integration.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.healthcare.DbUnitIntegrationTestConfiguration;
import com.healthcare.model.BodyStatus;
import com.healthcare.model.ReviewForm;
import com.healthcare.model.entity.Employee;
import com.healthcare.model.entity.Review;
import com.healthcare.model.entity.User;
import com.healthcare.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@TestExecutionListeners(
        value = {DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@DatabaseSetup(value = "/dataset/service/ReviewServiceImplIntegrationTest.xml")
@ContextConfiguration(classes = {DbUnitIntegrationTestConfiguration.class})
@Transactional
@SpringBootTest
public class ReviewServiceImplTest {

    @Autowired
    private ReviewService sut;

    @Autowired
    private EntityManager em;

    @Test
    public void testCreate() {
        // given
        final Long employeeId = 100L;
        final Long userId = 100L;
        final Review review = new Review();

        final Employee employee = new Employee();
        employee.setId(employeeId);
        review.setEmployee(employee);

        final User user = new User();
        user.setId(userId);
        review.setUser(user);
        review.setUser1(user);
        // when
        Review result = sut.save(review);
        // then
        assertThat(result, notNullValue());
        assertThat(result.getId(), notNullValue());
    }

    @Test
    @ExpectedDatabase(
            value = "/dataset/service/ReviewServiceImplIntegrationTest.testUpdate.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    public void testUpdate() {
        // given
        final Long reviewId = 100L;
        final Long employeeId = 100L;
        final Long userId = 100L;
        final Review review = new Review();
        review.setId(reviewId);
        review.setFormData(getReviewForm());

        final Employee employee = new Employee();
        employee.setId(employeeId);
        review.setEmployee(employee);

        final User user = new User();
        user.setId(userId);
        review.setUser(user);
        review.setUser1(user);
        // when
        Review result = sut.save(review);
        // then
        assertThat(result, notNullValue());

        em.flush();
    }

    @Test
    public void testGet() {
        // given
        final Long reviewId = 100L;
        // when
        Review result = sut.findById(reviewId);
        // then
        assertThat(result, notNullValue());
        assertThat(result.getId(), equalTo(reviewId));
    }

    @Test
    @ExpectedDatabase(
            value = "/dataset/service/ReviewServiceImplIntegrationTest.testDelete.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    public void testDelete() {
        // given
        final Long reviewId = 100L;
        // when
        sut.deleteById(reviewId);
        // then
        em.flush();
    }

    private ReviewForm getReviewForm() {
        ReviewForm result = new ReviewForm();

        BodyStatus bodyStatus = new BodyStatus();
        bodyStatus.setBloodPressureDiastolic(80);
        bodyStatus.setBloodPressureSystolic(120);
        bodyStatus.setPulse(70);
        bodyStatus.setHeight(180);
        bodyStatus.setWeight(90);
        result.setBodyStatus(bodyStatus);

        return result;
    }
}
