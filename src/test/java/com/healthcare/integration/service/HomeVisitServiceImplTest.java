package com.healthcare.integration.service;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.healthcare.DbUnitIntegrationTestConfiguration;
import com.healthcare.model.entity.Caregiver;
import com.healthcare.model.entity.HomeVisit;
import com.healthcare.model.entity.Meal;
import com.healthcare.model.entity.ServicePlan;
import com.healthcare.model.entity.User;
import com.healthcare.service.HomeVisitService;

@RunWith(SpringRunner.class)
@TestExecutionListeners(
        value = {DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@DatabaseSetup(value = "/dataset/service/HomeVisitServiceImplIntegrationTest.xml")
@ContextConfiguration(classes = {DbUnitIntegrationTestConfiguration.class})
@Transactional
@SpringBootTest
public class HomeVisitServiceImplTest {

    @Autowired
    private HomeVisitService homeVisitService;

    @Autowired
    private EntityManager em;

    // Remove data added during test from redis once test case executed successfully
    public void cleanup(Long id){
  	  homeVisitService.deleteById(id);
    }
    
    @Test
	public void testCreateHomeVisit() {
		// given
		final ServicePlan serviceplan = getServicePlan();
		final User user = getUser();
		final Caregiver careGiver = getCareGiver();
		final HomeVisit homeVisit = getHomeVisit(serviceplan, user, careGiver);
		
		// when
		HomeVisit result = homeVisitService.save(homeVisit);
		// then
		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		cleanup(result.getId());
	}

   @Test
    @ExpectedDatabase(
            value = "/dataset/service/HomeVisitServiceImplIntegrationTest.testUpdate.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    public void testUpdateHomeVisit() {
       Timestamp dbCheckoutTime = Timestamp.valueOf("2017-05-01 21:00:000");
       Timestamp updatedCheckoutTime = Timestamp.valueOf("2018-05-01 09:00:000");
       
	   // given
		final HomeVisit homeVisit = homeVisitService.findById(100L);
		assertEquals(dbCheckoutTime, homeVisit.getCheckOutTime());
		
		homeVisit.setCheckOutTime(updatedCheckoutTime);
		
       // when
        HomeVisit result = homeVisitService.save(homeVisit);
        // then
        assertThat(result, notNullValue());
        assertEquals(updatedCheckoutTime, result.getCheckOutTime());
		
        em.flush();
    }

    @Test
    public void testFindById() {
        // given
        final Long careGiverId = 100L;
        // when
        HomeVisit result = homeVisitService.findById(careGiverId);
        // then
        assertThat(result, notNullValue());
    }

    @Test
    public void testFindAll() {
        // when
        List<HomeVisit> result = homeVisitService.findAll();
        // then
        assertTrue(result.size()>0);
    }
    
    @Test
    @ExpectedDatabase(
            value = "/dataset/service/HomeVisitServiceImplIntegrationTest.testDelete.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    public void testDeleteById() {
        // given
        final Long careGiverId = 100L;
        // when
        homeVisitService.deleteById(careGiverId);
        // then
        em.flush();
    }
    
    
    private HomeVisit getHomeVisit(final ServicePlan serviceplan,final User user,final Caregiver careGiver) {
		final HomeVisit homeVisit = new HomeVisit();
        homeVisit.setCheckInTime(Timestamp.valueOf("2017-05-01 09:00:000"));
        homeVisit.setCareReceiverSignature("sign");
        homeVisit.setCheckOutTime(Timestamp.valueOf("2017-05-01 21:00:000"));
        homeVisit.setCareReceiverComments("comments");
        homeVisit.setNotes("notes");
        homeVisit.setStatus("verified");
        homeVisit.setCreatedAt(Timestamp.valueOf("2017-05-01 09:00:000"));
        homeVisit.setUpdatedAt(Timestamp.valueOf("2017-05-01 09:00:000"));
        homeVisit.setServiceplan(serviceplan);
        homeVisit.setUser(user);
        homeVisit.setCareGiver(careGiver);
        return homeVisit;
	}

	private Meal getMeal() {
		final Long mealId = 100L;
        final Meal meal = new Meal();
        meal.setId(mealId);
		return meal;
	}
	
	private User getUser() {
		final Long userId = 100L;
        final User user = new User();
        user.setId(userId);
		return user;
	}
	
	private ServicePlan getServicePlan() {
		final Long servicePlanId = 100L;
        final ServicePlan servicePlan = new ServicePlan();
        servicePlan.setId(servicePlanId);
		return servicePlan;
	}
	
	private Caregiver getCareGiver() {
		final Long careGiverId = 100L;
        final Caregiver careGiver = new Caregiver();
        careGiver.setId(careGiverId);
		return careGiver;
	}

}