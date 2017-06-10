package com.healthcare.integration.service;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.EntityFactory;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Employee;
import com.healthcare.model.entity.ServicePlan;
import com.healthcare.model.entity.User;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.EmployeeService;
import com.healthcare.service.ServicePlanService;
import com.healthcare.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServicePlanServiceTest extends EntityFactory {
	@Autowired
	private ServicePlanService servicePlanService;

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AgencyTypeService agencyTypeService;

	User user;
	Employee employee;
	Company company;
	AgencyType agencyType;
	Agency agency;

	@Before
	public void setup() {
		init();
		user = createNewUser();
		userService.save(user);
		agencyType = createNewAgencyType();
		agencyTypeService.save(agencyType);
		company = createNewCompany();
		companyService.save(company);
		agency = createNewAgency(agencyType, company);
		agencyService.save(agency);
		employee = createNewEmployee(agency);
		employeeService.save(employee);
	}

	@Test
	public void testSaveServicePlan() {
		ServicePlan servicePlan = createNewServicePlan(user);
		servicePlanService.save(servicePlan);
		Assert.assertNotNull(servicePlan.getId());
	}

	@Test
	public void testGetServicePlan() {
		ServicePlan servicePlan = createNewServicePlan(user);
		servicePlanService.save(servicePlan);
		Assert.assertNotNull(servicePlanService.findById(servicePlan.getId()));
	}
	
	@Test
	public void testGetServiceCalendarPlan() {
		ServicePlan servicePlan = createNewServicePlan(user);
		servicePlanService.save(servicePlan);
		Assert.assertNotEquals(0, servicePlanService.getServiceCalendar(servicePlan.getId()).size());
	}

	@Test
	public void testUpdateServicePlan() {
		String newDocUrl = "/doc/new/a";
		ServicePlan servicePlan = createNewServicePlan(user);
		servicePlanService.save(servicePlan);
		Assert.assertEquals(servicePlan.getDocUrl(), docUrl);
		ServicePlan savedServicePlan = servicePlanService.findById(servicePlan.getId());
		savedServicePlan.setDocUrl(newDocUrl);
		servicePlanService.save(savedServicePlan);
		ServicePlan modifiedServicePlan = servicePlanService.findById(servicePlan.getId());
		Assert.assertEquals(modifiedServicePlan.getDocUrl(), newDocUrl);
	}

	@Test
	public void testDeleteServicePlan() {
		ServicePlan servicePlan = createNewServicePlan(user);
		servicePlanService.save(servicePlan);
		Assert.assertNotNull(servicePlan.getId());
		servicePlanService.deleteById(servicePlan.getId());
		Assert.assertNull(servicePlanService.findById(servicePlan.getId()));
	}
}
