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
import com.healthcare.model.entity.ServicePlan;
import com.healthcare.model.entity.User;
import com.healthcare.model.entity.Visit;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.ServicePlanService;
import com.healthcare.service.UserService;
import com.healthcare.service.VisitService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VisitServiceTest extends EntityFactory {

	@Autowired
	private UserService userService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	public CompanyService companyService;

	@Autowired
	public AgencyTypeService agencyTypeService;
	
	@Autowired
	public ServicePlanService servicePlanService;

	@Autowired
	private VisitService visitService;

	private Company company;
	private AgencyType agencyType;
	private Agency agency;
	private User user;
	private ServicePlan servicePlan;

	@Before
	public void setup() {
		init();
		company = createNewCompany();
		companyService.save(company);
		agencyType = createNewAgencyType();
		agencyTypeService.save(agencyType);
		agency = createNewAgency(agencyType, company);
		agencyService.save(agency);
		user = createNewUser();		
		userService.save(user);
		servicePlan = createNewServicePlan(user);
		servicePlanService.save(servicePlan);
	}

	@Test
	public void shouldSaveAVisit() {
		Visit visit = createNewVisit(user, agency);
		visitService.save(visit);
		Assert.assertNotNull(visit.getId());
	}

	@Test
	public void shouldGetAVisit() {
		Visit visit = createNewVisit(user, agency);
		visitService.save(visit);
		Assert.assertNotNull(visitService.findById(visit.getId()));
	}
	
	@Test
	public void shouldFindAllVisitByServiceId() {
		Visit visit = createNewVisit(user, agency);
		visitService.save(visit);
		Assert.assertNotNull(visitService.findAllByServicePlanId(servicePlan.getId()));
	}

	@Test
	public void shouldUpdateAVisit() {
		String newUserSignature = "Dr. Abc Junior";
		String newNotes = "seems so quiet";

		Visit visit = createNewVisit(user, agency);
		visitService.save(visit);
		Assert.assertEquals(visit.getUserSignature(), userSignature);
		Assert.assertEquals(visit.getNotes(), notes);
		Visit visitSaved = visitService.findById(visit.getId());
		visitSaved.setUserSignature(newUserSignature);
		visitSaved.setNotes(newNotes);
		visitService.save(visitSaved);
		Visit visitMofified = visitService.findById(visit.getId());
		Assert.assertEquals(visitMofified.getUserSignature(), newUserSignature);
		Assert.assertEquals(visitMofified.getNotes(), newNotes);
	}

	@Test
	public void shouldDeleteAVisit() {
		Visit visit = createNewVisit(user, agency);
		visitService.save(visit);
		Assert.assertNotNull(visit.getId());
		visitService.deleteById(visit.getId());
		Assert.assertNull(visitService.findById(visit.getId()));
	}

}
