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
		Visit visit = createNewVisit();
		visitService.save(visit);
		Assert.assertNotNull(visit.getId());
	}

	private Visit createNewVisit() {
		Timestamp checkInTime = new Timestamp(new Date().getTime());
		Timestamp checkOutTime = new Timestamp(new Date().getTime());
		String userComments = "all ok";
		String notes = ".";
		String selectedTable = "TABLE 1";
		String selectedSeat = "AB";
		String userSignature = "userSignature";
		Visit visit = new Visit();
		visit.setUser(user);
		visit.setAgency(agency);
		visit.setCheckInTime(checkInTime);
		visit.setCheckOutTime(checkOutTime);
		visit.setSelectedSeat(selectedTable);
		visit.setSelectedSeat(selectedSeat);
		visit.setUserSignature(userSignature);
		// visit.setSelectedMeal(selectedMeal);// TODO not yet finished Meal CRUD
		// visit.setUserBarcodeId(userBarcodeId);// TODO not yet finished Meal CRUD
		visit.setUserComments(userComments);
		visit.setNotes(notes);
		visit.setStatus(VisitStatusEnum.BOOKED.name());
		return visit;
	}

}
