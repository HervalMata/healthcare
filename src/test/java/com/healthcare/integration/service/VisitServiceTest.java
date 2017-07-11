package com.healthcare.integration.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.EntityFactory;
import com.healthcare.api.model.VisitRequest;
import com.healthcare.exception.ApplicationException;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.ServicePlan;
import com.healthcare.model.entity.User;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.enums.VisitStatusEnum;
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
	
	@Autowired
	private RedisTemplate<String, Visit> redisTemplate;

	private Company company;
	private AgencyType agencyType;
	private Agency agency;
	private User user;
	private ServicePlan servicePlan;
	private Visit visit;

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
		visit = null;
		redisTemplate.delete(Visit.class.getSimpleName());
	}
	

	@After
	public void rollback() {
		if(visit!=null){
			visitService.deleteById(visit.getId());
		}
		userService.deleteById(user.getId());
		agencyService.deleteById(agency.getId());
		agencyTypeService.deleteById(agencyType.getId());
		companyService.deleteById(company.getId());
	}

	@Test
	public void shouldSaveAVisit() {
		visit = createNewVisit(user, agency);
		visit = visitService.save(visit);
		Assert.assertNotNull(visit.getId());
	}

	@Test
	public void shouldGetAVisit() {
		visit = createNewVisit(user, agency);
		visit = visitService.save(visit);
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

		visit = createNewVisit(user, agency);
		visit = visitService.save(visit);
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
		visit = visitService.save(visit);
		Assert.assertNotNull(visit.getId());
		visitService.deleteById(visit.getId());
		Assert.assertNull(visitService.findById(visit.getId()));
	}
	
	@Test
	public void shouldCheckInAVisit() {
		visit = createNewVisit(user, agency);
		visit.setId(12L); 
		visit.setCheckInTime(new Timestamp(new Date(0).getTime()));  
		visit.setStatus(VisitStatusEnum.BOOKED.name());
		visit.setUserBarcodeId("12345678901234567890");
		visit = visitService.save(visit);
		
		// Values before check in
		Date oldCheckInTime = visit.getCheckInTime();
		String oldStatus = visit.getStatus();
		
		// search by id
		VisitRequest visitRequest = new VisitRequest();
		visitRequest.setUserBarcodeId("12345678901234567890"); 
		visitService.checkIn(visitRequest);
		Visit visitCHeckIn = visitService.findById(visit.getId());
		
		Assert.assertNotEquals(visitCHeckIn.getCheckInTime(), oldCheckInTime);
		Assert.assertNotEquals(visitCHeckIn.getStatus(), oldStatus);
		Assert.assertEquals(visitCHeckIn.getStatus(), VisitStatusEnum.REGISTERED.name());
		
		// search by userBarecodeId
		visit.setStatus(VisitStatusEnum.BOOKED.name());
		visit = visitService.save(visit);
		
		visitRequest = new VisitRequest();
		visitRequest.setUserBarcodeId("12345678901234567890"); 
		visitService.checkIn(visitRequest);
		visitCHeckIn = visitService.findById(visit.getId());
		
		Assert.assertNotEquals(visitCHeckIn.getCheckInTime(), oldCheckInTime);
		Assert.assertNotEquals(visitCHeckIn.getStatus(), oldStatus);
		Assert.assertEquals(visitCHeckIn.getStatus(), VisitStatusEnum.REGISTERED.name());

	}

	@Test(expected = ApplicationException.class)
	public void shouldNotCheckInAVisit() {
		// Nominal case
		visit = createNewVisit(user, agency);
		visit.setCheckOutTime(new Timestamp(new Date(0).getTime()));  
		visit.setStatus(VisitStatusEnum.REGISTERED.name()); 
		visit = visitService.save(visit);

		VisitRequest visitRequest = new VisitRequest();
		visitRequest.setId(visit.getId());
		visitService.checkIn(visitRequest);
	}
	
	@Test(expected = ApplicationException.class)
	public void shouldNotCheckInAVisit2() {
		VisitRequest visitRequest = new VisitRequest();
		visitService.checkIn(visitRequest);
	}
	
	@Test
	public void shouldCheckOutAVisit() {
		visit = createNewVisit(user, agency);
		visit.setId(12L); 
		visit.setCheckOutTime(new Timestamp(new Date(0).getTime()));  
		visit.setStatus(VisitStatusEnum.REGISTERED.name());
		visit.setUserBarcodeId("12345678901234567890");
		visit = visitService.save(visit);
		
		// Values before check in
		Date oldCheckOutTime = visit.getCheckOutTime();
		String oldStatus = visit.getStatus();
		
		// search by id
		VisitRequest visitRequest = new VisitRequest();
		visitRequest.setUserBarcodeId("12345678901234567890"); 
		visitService.checkOut(visitRequest);
		Visit visitCHeckOut = visitService.findById(visit.getId());
		
		Assert.assertNotEquals(visitCHeckOut.getCheckOutTime(), oldCheckOutTime);
		Assert.assertNotEquals(visitCHeckOut.getStatus(), oldStatus);
		Assert.assertEquals(visitCHeckOut.getStatus(), VisitStatusEnum.FINISHED.name());
		
		// search by userBarecodeId
		visit.setStatus(VisitStatusEnum.REGISTERED.name());
		visit = visitService.save(visit);
		
		visitRequest = new VisitRequest();
		visitRequest.setUserBarcodeId("12345678901234567890"); 
		visitService.checkOut(visitRequest);
		visitCHeckOut = visitService.findById(visit.getId());
		
		Assert.assertNotEquals(visitCHeckOut.getCheckOutTime(), oldCheckOutTime);
		Assert.assertNotEquals(visitCHeckOut.getStatus(), oldStatus);
		Assert.assertEquals(visitCHeckOut.getStatus(), VisitStatusEnum.FINISHED.name());
		
	}
	
	@Test(expected = ApplicationException.class)
	public void shouldNotCheckOutAVisit() {
		// Nominal case
		visit = createNewVisit(user, agency);
		visit.setCheckOutTime(new Timestamp(new Date(0).getTime()));  
		visit.setStatus(VisitStatusEnum.BOOKED.name()); 
		visit = visitService.save(visit);
		
		VisitRequest visitRequest = new VisitRequest();
		visitRequest.setId(visit.getId());
		visitService.checkOut(visitRequest);
	
	}
	
	@Test(expected = ApplicationException.class)
	public void shouldNotCheckOutAVisit2() {
		VisitRequest visitRequest = new VisitRequest();
		visitService.checkOut(visitRequest);
	}
	
	@Test
	public void souldFindAll() {
		Visit visit = createNewVisit(user, agency);
		visit = visitService.save(visit);
		Visit visit2 = createNewVisit(user, agency);
		visit2 = visitService.save(visit2);
		Visit visit3 = createNewVisit(user, agency);
		visit3 = visitService.save(visit3);
		
		List<Visit> list= visitService.findAll();
		assertNotNull(list);
		assertEquals(3, list.size());
	
		//Cleanups
		visitService.deleteById(visit.getId());
		visitService.deleteById(visit2.getId());
		visitService.deleteById(visit3.getId());
	}
}
