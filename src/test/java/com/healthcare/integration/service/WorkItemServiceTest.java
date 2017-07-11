package com.healthcare.integration.service;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.EntityFactory;
import com.healthcare.model.entity.WorkItem;
import com.healthcare.service.WorkItemService;


/**
 * Created by Mostafa Hamed on 30/06/17.
 * @author mhamed
 * @version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WorkItemServiceTest extends EntityFactory{

	
	@Autowired 
	WorkItemService workItemService;
	
	
	@Before
	public void setup() {

	}


	private Long id = 0L;

	@After
	public void rollback() {
		if(id!=0L)
			workItemService.deleteById(id);
	}

	@Test
	public void saveWorkItem() {
		WorkItem workItem = createNewWorkItem();
		workItem = workItemService.save(workItem);
		Assert.assertNotNull(workItem.getId());
		id=workItem.getId();
	}
	
	@Test
	public void getWorkItem() {
		WorkItem workItem = createNewWorkItem();
		workItem = workItemService.save(workItem);
		Assert.assertNotNull(workItemService.findById(workItem.getId()));
		id=workItem.getId();
	}

	@Test
	public void updateWorkItem() {
		String newWorkItemName = "help on cooking";
		WorkItem workItem = createNewWorkItem();
		workItem = workItemService.save(workItem);
		Assert.assertEquals(workItem.getItemName(), itemName);
		WorkItem savedWorkItem = workItemService.findById(workItem.getId());
		savedWorkItem.setItemName(newWorkItemName);
		workItemService.save(savedWorkItem);
		WorkItem modifiedWorkItem = workItemService.findById(workItem.getId());
		Assert.assertEquals(modifiedWorkItem.getItemName(), newWorkItemName);
		
		id=modifiedWorkItem.getId();
	}

	@Test
	public void deleteWorkItem() {
		WorkItem workItem = createNewWorkItem();
		workItem = workItemService.save(workItem);
		Assert.assertNotNull(workItem.getId());
		workItemService.deleteById(workItem.getId());
		Assert.assertNull(workItemService.findById(workItem.getId()));
	}
	
}
