package com.healthcare.integration.service;

import java.sql.Timestamp;
import java.util.Date;

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
import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.User;
import com.healthcare.model.entity.Visit;
import com.healthcare.repository.VisitRepository;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.UserService;
import com.healthcare.service.VisitService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VisitServiceRedisTest extends EntityFactory {
	@MockBean
	private VisitRepository visitRepository;

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
	public void shouldSaveAVisitToRedisAndRetrievedItFromRedis() {
		Visit visit = createNewVisit(user, agency);
		visit.setId(10L);
		Mockito.when(visitRepository.save(visit)).thenReturn(visit);
		visitService.save(visit);
		Visit visitSaved = visitService.findById(10L);
		Assert.assertNotNull(visitSaved);
	}

	@Test
	public void shouldUpdateAVisitToRedis() {
		String newUserSignature = "Dr. Abc Junior";
		String newNotes = "seems so quiet";

		Visit visit = createNewVisit(user, agency);
		visit.setId(10L);
		Mockito.when(visitRepository.save(visit)).thenReturn(visit);
		visitService.save(visit);
		Visit visitSaved = visitService.findById(visit.getId());
		visitSaved.setUserSignature(newUserSignature);
		visitSaved.setNotes(newNotes);
		Mockito.when(visitRepository.save(visitSaved)).thenReturn(visitSaved);
		visitService.save(visitSaved);
		Visit visitMofified = visitService.findById(visit.getId());
		Assert.assertEquals(visitMofified.getUserSignature(), newUserSignature);
		Assert.assertEquals(visitMofified.getNotes(), newNotes);
	}

	@Test
	public void shouldDeleteAVisit() {
		Visit visit = createNewVisit(user, agency);
		visit.setId(10L);
		Mockito.when(visitRepository.save(visit)).thenReturn(visit);
		visitService.save(visit);
		Mockito.doNothing().when(visitRepository).delete(10L);
		Assert.assertNotNull(visitService.deleteById(visit.getId()));
	}
	
	public void shouldCheckInAVisitToRedis() {
		Visit visit = createNewVisit(user, agency);
		visit.setId(10L);
		visit.setCheckOutTime(new Timestamp(new Date(0).getTime()));  
		Mockito.when(visitRepository.save(visit)).thenReturn(visit);
		visitService.save(visit);
		// Values before check in
		Date oldCheckInTime = visit.getCheckInTime();
		String oldStatus = visit.getStatus();
	
		Visit visitCHeckIn = visitService.checkIn(visit);
		
		Assert.assertNotEquals(visitCHeckIn.getCheckInTime(), oldCheckInTime);
		Assert.assertNotEquals(visitCHeckIn.getStatus(), oldStatus);
	}
	
	
	public void shouldCheckOutAVisitToRedis() {
		Visit visit = createNewVisit(user, agency);
		visit.setId(10L);
		visit.setCheckOutTime(new Timestamp(new Date(0).getTime())); 
		Mockito.when(visitRepository.save(visit)).thenReturn(visit);
		visitService.save(visit);
		
		// Values before check out
		Date oldCheckOutTime = visit.getCheckOutTime();
		
		Visit visitCHeckOut = visitService.checkOut(visit);
		
		Assert.assertNotEquals(visitCHeckOut.getCheckOutTime(), oldCheckOutTime);
	}
}
