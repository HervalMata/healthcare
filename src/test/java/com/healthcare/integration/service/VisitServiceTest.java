package com.healthcare.integration.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.EntityFactory;
import com.healthcare.api.model.VisitRequest;
import com.healthcare.exception.ApplicationException;
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.User;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.enums.VisitStatusEnum;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
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
	private VisitService visitService;

	private Company company;
	private AgencyType agencyType;
	private Agency agency;
	private User user;

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
	
	@Test
	public void shouldCheckInAVisit() {
		Visit visit = createNewVisit(user, agency);
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
		Visit visit = createNewVisit(user, agency);
		visit.setCheckOutTime(new Timestamp(new Date(0).getTime()));  
		visit.setStatus(VisitStatusEnum.REGISTERED.name()); 
		visit = visitService.save(visit);

		VisitRequest visitRequest = new VisitRequest();
		visitRequest.setId(visit.getId());
		visitService.checkIn(visitRequest);
	}
	
	@Test
	public void shouldCheckOutAVisit() {
		Visit visit = createNewVisit(user, agency);
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
		Visit visit = createNewVisit(user, agency);
		visit.setCheckOutTime(new Timestamp(new Date(0).getTime()));  
		visit.setStatus(VisitStatusEnum.BOOKED.name()); 
		visitService.save(visit);
		
		VisitRequest visitRequest = new VisitRequest();
		visitRequest.setId(visit.getId());
		visitService.checkOut(visitRequest);
	}

}
