package com.healthcare.integration.service;

import java.sql.Timestamp;
import java.util.List;

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
import com.healthcare.model.enums.DayEnum;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.EmployeeService;
import com.healthcare.service.ServicePlanService;
import com.healthcare.service.UserService;
import com.healthcare.util.DateUtils;

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
		// days = MONDAY,THURSDAY
		// plan_start = 2017-06-01
		// plan_end = 2017-12-01
		ServicePlan servicePlan = createNewServicePlan(user);
		servicePlanService.save(servicePlan);
		List<String> serviceCalendar = servicePlanService.getServiceCalendar(servicePlan.getId());

		// expect valid service plan (not returning null)
		Assert.assertNotNull(servicePlan);

		// expect there are 53 days
		Assert.assertEquals(53, serviceCalendar.size());

		// expect the first day is on 2017-06-01
		Assert.assertTrue("2017-06-01".equals(serviceCalendar.get(0)));

		// expect the last day is on 2017-11-30
		Assert.assertTrue("2017-11-30".equals(serviceCalendar.get(serviceCalendar.size() - 1)));

		// update the period, plan_end before plan_start
		Timestamp temp = servicePlan.getPlanEnd();
		servicePlan.setPlanEnd(servicePlan.getPlanStart());
		servicePlan.setPlanStart(temp);
		servicePlan = servicePlanService.save(servicePlan);

		// expect return null cause of invalid period
		Assert.assertNull(servicePlan);

		// update the days, input any string that not in DayEnum
		servicePlan = createNewServicePlan(user);
		servicePlan.setDays("MON,TUE");
		servicePlan = servicePlanService.save(servicePlan);

		// expect return null cause of invalid days
		Assert.assertNull(servicePlan);

		// update the days, input any day but not in period;
		servicePlan = createNewServicePlan(user);
		servicePlan.setDays(DayEnum.MONDAY.name() + "," + DayEnum.WEDNESDAY.name());
		servicePlan.setPlanEnd(new Timestamp(DateUtils.stringToDate("yyyy-MM-dd", "2017-06-04").getTime()));
		servicePlanService.save(servicePlan);

		// expect return empty array list
		Assert.assertTrue(servicePlanService.getServiceCalendar(servicePlan.getId()).isEmpty());
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
