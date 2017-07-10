package com.healthcare.integration.service;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.entity.VisitActivity;
import com.healthcare.model.entity.VisitActivityPK;
import com.healthcare.repository.VisitActivityRepository;
import com.healthcare.service.VisitActivityService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VisitActivityServiceRedisTest {
    @MockBean
    private VisitActivityRepository visitActivityRepository;

    @Autowired
    private VisitActivityService visitActivityService;

    @Before
    public void setup() {
    }
    

	private Long visitId = 100L;
	private Long activityId = 100L;

	@After
	public void rollback() {
		visitActivityService.deleteById(getPk(visitId, activityId));
	}
    
    private VisitActivityPK getPk(Long visitId,Long activityId) {
		return new VisitActivityPK(visitId,activityId);
	}
    

    @Test
    public void testSaveVisitActivityToRedisAndRetrievedItFromRedis() {
    	//given
		final VisitActivity visitActivity = getVisitActivity();
		visitActivity.setVisitId(visitId);
		visitActivity.setActivityId(activityId);
		
		//Mock
        Mockito.when(visitActivityRepository.save(visitActivity)).thenReturn(visitActivity);
        visitActivityService.save(visitActivity);
        
        //Execute
        VisitActivity visitActivitySaved = visitActivityService.findById(getPk(visitId, activityId));
        
        //Assert
        Assert.assertNotNull(visitActivitySaved);
    }

    @Test
    public void testUpdateVisitActivityToRedis() {
    	String tableName = "table name updated in redis";
    	
    	//given
		final VisitActivity visitActivity = getVisitActivity();
	    visitActivity.setActivityId(activityId);
	    visitActivity.setVisitId(visitId);
        
	    //Mock
	    Mockito.when(visitActivityRepository.save(visitActivity)).thenReturn(visitActivity);
        visitActivityService.save(visitActivity);
        
        VisitActivityPK pk = new VisitActivityPK(visitId,activityId);
        VisitActivity savedVisitActivityInRedis = visitActivityService.findById(pk);
        savedVisitActivityInRedis.setTableName(tableName);
        
        Mockito.when(visitActivityRepository.save(savedVisitActivityInRedis)).thenReturn(savedVisitActivityInRedis);
        visitActivityService.save(savedVisitActivityInRedis);

        VisitActivity modifiedVisitActivityFromRedis = visitActivityService.findById(pk);
        
        //Assert
        Assert.assertEquals(modifiedVisitActivityFromRedis.getTableName(), tableName);
    }

    @Test
    public void testDeleteVisitActivityFromRedis() {
    	//given and save the data 
		final VisitActivity visitActivity = getVisitActivity();
		Mockito.when(visitActivityRepository.save(visitActivity)).thenReturn(visitActivity);
        visitActivityService.save(visitActivity);
        
        // mock for delete
		VisitActivityPK pk = new VisitActivityPK(visitId, activityId);
        Mockito.doNothing().when(visitActivityRepository).delete(pk);
        
        //Assert delete
        Assert.assertNotNull(visitActivityService.deleteById(pk));
    }
    
    //If activity or visit is not null then fetch from redis
    @Test
    public void testFindVisitActivityByActivityId_ActivityAndVisitAreNoteNull(){
    	final VisitActivity visitActivity = getVisitActivity();
    	Mockito.when(visitActivityRepository.save(visitActivity)).thenReturn(visitActivity);
        visitActivityService.save(visitActivity);
        
        List<VisitActivity> visitActivityList = visitActivityService.findVisitActivityByActivityId(activityId);
        assertEquals(1,visitActivityList.size());
        
    }
    
    // If activity or visit is null in object then fetch from database and store in redis
    @Test
    public void testFindVisitActivityByActivityId_ActivityOrVisitIsNull(){
    	final VisitActivity visitActivity = getVisitActivity();
    	visitActivity.setActivity(null);
    	Mockito.when(visitActivityRepository.save(visitActivity)).thenReturn(visitActivity);
        visitActivityService.save(visitActivity);
        
        // Return list
        VisitActivity visitActivityNotNullVisitAndActivity = getVisitActivity();
        Mockito.when(visitActivityRepository.findVisitActivityByActivityId(visitId)).thenReturn(Collections.singletonList(visitActivityNotNullVisitAndActivity));
        
        List<VisitActivity> visitActivityList = visitActivityService.findVisitActivityByActivityId(visitId);
        assertEquals(1,visitActivityList.size());
    }
    
  //If activity or visit is not null then fetch from redis
    @Test
    public void testFindVisitActivityByVisitId_ActivityAndVisitAreNoteNull(){
    	final VisitActivity visitActivity = getVisitActivity();
    	Mockito.when(visitActivityRepository.save(visitActivity)).thenReturn(visitActivity);
        visitActivityService.save(visitActivity);
        
        List<VisitActivity> visitActivityList = visitActivityService.findVisitActivityByVisitId(visitId);
        assertEquals(1,visitActivityList.size());
        
    }
    
    // If activity or visit is null in object then fetch from database and store in redis
    @Test
    public void testFindVisitActivityByVisitId_ActivityOrVisitIsNull(){
    	final VisitActivity visitActivity = getVisitActivity();
    	visitActivity.setVisit(null);
    	Mockito.when(visitActivityRepository.save(visitActivity)).thenReturn(visitActivity);
        visitActivityService.save(visitActivity);
        
        // Return list
        VisitActivity visitActivityNotNullVisitAndActivity = getVisitActivity();
        Mockito.when(visitActivityRepository.findVisitActivityByVisitId(visitId)).thenReturn(Collections.singletonList(visitActivityNotNullVisitAndActivity));
        
        List<VisitActivity> visitActivityList = visitActivityService.findVisitActivityByVisitId(visitId);
        assertEquals(1,visitActivityList.size());
    }
    
    public Activity getActivity(){
    	Activity activity = new Activity();
    	activity.setId(activityId);
    	activity.setStatus(1);
    	activity.setName("activity name");
    	
    	return activity;
    }
    
    public Visit getVisit(){
    	Visit visit = new Visit();
    	visit.setId(visitId);
    	visit.setStatus("1");
    	visit.setNotes("visit notes");
    	
    	return visit;
    }
    
    
	private VisitActivity getVisitActivity() {
		VisitActivity visitActivity = new VisitActivity();
		visitActivity.setActivityId(activityId);
		visitActivity.setVisitId(visitId);
		visitActivity.setTableName("table name");
		visitActivity.setVisit(getVisit());
		visitActivity.setActivity(getActivity());
		return visitActivity;
	}
}
