package com.healthcare.integration.service;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.EntityFactory;
import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Employee;
import com.healthcare.model.entity.User;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.entity.VisitActivity;
import com.healthcare.model.entity.VisitActivityPK;
import com.healthcare.repository.VisitActivityRepository;
import com.healthcare.service.ActivityService;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.EmployeeService;
import com.healthcare.service.UserService;
import com.healthcare.service.VisitActivityService;
import com.healthcare.service.VisitService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VisitActivityServiceRedisTest extends EntityFactory {
	@MockBean
	private VisitActivityRepository visitActivityRepository;

	@Autowired
	private VisitActivityService visitActivityService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AgencyTypeService agencyTypeService;

	@Autowired
	private VisitService visitService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private EmployeeService employeeService;

	User user;
	Employee employee;
	Company company;
	AgencyType agencyType;
	Agency agency;
	Visit visit;
	Activity activity;

	@Before
	public void setup() {
		init();
		company = createNewCompany();
		companyService.save(company);
		agencyType = createNewAgencyType();
		agencyTypeService.save(agencyType);
		agency = createNewAgency(agencyType, company);
		agencyService.save(agency);
		employee = createNewEmployee(agency);
		employeeService.save(employee);
		user = createNewUser();
		userService.save(user);
		visit = createNewVisit(user, agency);
		visitService.save(visit);
		activity = createNewActivity(employee);
		activityService.save(activity);
	}

	@Test
	public void testSaveVisitActivity() {
		VisitActivity visitActivity = createNewVisitActivity(visit, activity);
		Mockito.when(visitActivityRepository.save(visitActivity)).thenReturn(visitActivity);
		visitActivityService.save(visitActivity);
		Assert.assertNotNull(visitActivityService.findById(new VisitActivityPK(visit.getId(), activity.getId())));
	}

	@Test
	public void testUpdateVisitActivity() {
		String newSeat = "10B";
		VisitActivity visitActivity = createNewVisitActivity(visit, activity);
		Mockito.when(visitActivityRepository.save(visitActivity)).thenReturn(visitActivity);
		visitActivityService.save(visitActivity);
		VisitActivity savedvisitActivity = visitActivityService.findById(new VisitActivityPK(visit.getId(), activity.getId()));
		savedvisitActivity.setSeat(newSeat);
		Mockito.when(visitActivityRepository.save(savedvisitActivity)).thenReturn(savedvisitActivity);
		visitActivityService.save(savedvisitActivity);
		VisitActivity modifiedVisitActivity = visitActivityService.findById(new VisitActivityPK(visit.getId(), activity.getId()));
		Assert.assertEquals(modifiedVisitActivity.getSeat(), newSeat);
	}

	@Test
	public void testDeleteVisitActivity() {
		VisitActivity visitActivity = createNewVisitActivity(visit, activity);
		Mockito.when(visitActivityRepository.save(visitActivity)).thenReturn(visitActivity);
		visitActivityService.save(visitActivity);
		Mockito.doNothing().when(visitActivityRepository).delete(new VisitActivityPK(visit.getId(), activity.getId()));
		visitActivityService.deleteById(new VisitActivityPK(visit.getId(), activity.getId()));
		Assert.assertNotNull(visitActivityService.deleteById(new VisitActivityPK(visit.getId(), activity.getId())));
	}
}
