package com.healthcare.integration.service;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
import com.healthcare.model.entity.VisitActivity;
import com.healthcare.model.entity.VisitActivityPK;
import com.healthcare.service.VisitActivityService;

@RunWith(SpringRunner.class)
@TestExecutionListeners(
        value = {DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@DatabaseSetup(value = "/dataset/service/VisitActivityServiceImplIntegrationTest.xml")
@ContextConfiguration(classes = {DbUnitIntegrationTestConfiguration.class})
@Transactional
@SpringBootTest
public class VisitActivityServiceImplTest {

    @Autowired
    private VisitActivityService visitActivityService;

    @Autowired
    private EntityManager em;

    // Remove data added during test from redis once test case executed successfully
    public void cleanup(Long visitId,Long activityId){
  	  visitActivityService.deleteById(new VisitActivityPK(visitId, activityId));
    }
    
    @Test
	public void testCreateVisitActivity() {
		// given
		final VisitActivity visitActivity = new VisitActivity();
		visitActivity.setActivityId(100L);
		visitActivity.setVisitId(100L);
		
		// when
		VisitActivity result = visitActivityService.save(visitActivity);
		// then
		assertThat(result, notNullValue());
		assertThat(result.getActivityId(), notNullValue());
		assertThat(result.getVisitId(), notNullValue());
		cleanup(result.getVisitId(), result.getActivityId());
	}

   @Test
    @ExpectedDatabase(
            value = "/dataset/service/VisitActivityServiceImplIntegrationTest.testUpdate.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    public void testUpdateVisitActivity() {
       String dbTableName = "table name";
       String updatedTableName = "table name updated";
       
	   // given
		final VisitActivity visitActivity = visitActivityService.findById(new VisitActivityPK(100L,100L));
		assertEquals(dbTableName, visitActivity.getTableName());
		
		visitActivity.setTableName(updatedTableName);
		
       // when
        VisitActivity result = visitActivityService.save(visitActivity);
        // then
        assertThat(result, notNullValue());
        assertEquals(updatedTableName, result.getTableName());
		
        em.flush();
        cleanup(100L, 100L);
    }

    @Test
    public void testFindById() {
        // given
        final VisitActivityPK pk  = new VisitActivityPK(100L,100L);
        // when
        VisitActivity result = visitActivityService.findById(pk);
        // then
        assertThat(result, notNullValue());
    }

    @Test
    public void testFindByActivityId() {
        // given
        final Long activityId = 100L;
        // when
        List<VisitActivity> result = visitActivityService.findVisitActivityByActivityId(activityId);
        // then
        assertThat(result, notNullValue());
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getTableName(), "table name");
    }
    
    @Test
    public void testFindByVisitId() {
        // given
        final Long visitId = 100L;
        // when
        List<VisitActivity> result = visitActivityService.findVisitActivityByVisitId(visitId);
        // then
        assertThat(result, notNullValue());
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getTableName(), "table name");
    }
    
    @Test
    @ExpectedDatabase(
            value = "/dataset/service/VisitActivityServiceImplIntegrationTest.testDelete.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    public void testDeleteById() {
        // given
    	 final VisitActivityPK pk  = new VisitActivityPK(100L,100L);
         // when
        visitActivityService.deleteById(pk);
        // then
        em.flush();
    }

}