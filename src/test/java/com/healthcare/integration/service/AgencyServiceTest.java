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

import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AgencyServiceTest extends TestEntityFactory {
	@Autowired
	private AgencyService agencyService;

	@Autowired
	public CompanyService companyService;

	@Autowired
	public AgencyTypeService agencyTypeService;

	private Company company;
	private AgencyType agencyType;
	private Agency agency =null;
	
	String username = "username";
	String password = "password";
	String firstName = "John";
	String middleName = "B";
	String lastName = "Watson";
	String ip = "127.0.0.1";
	String secondaryPhone = "1234560001";
	String profilePhoto = "XXXXXXXXXX";
	String deviceAddress = "City ABC";
	String rememberToken = "00000";
	String levelName = "Level Name";
	long level = 1;
	long status = 1;

	@Before
	public void setup() {
		company = companyService.save(TestEntityFactory.createNewCompany());
		agencyType = agencyTypeService.save(TestEntityFactory.createNewAgencyType());
		agency =null;
	}

	@After
	public void rollback() {
		if (agency != null)
			agencyService.deleteById(agency.getId());
		
		agencyTypeService.deleteById(agencyType.getId());
		companyService.deleteById(company.getId());
	}

	@Test
	public void testSaveAgency() {
		agency = createNewAgency(company,agencyType);
		agency = agencyService.save(agency);
		Assert.assertNotNull(agency.getId());
	}

	@Test
	public void testGetAgency() {
		agency = createNewAgency( company,agencyType);
		agency = agencyService.save(agency);
		Assert.assertNotNull(agencyService.findById(agency.getId()));
	}

	@Test
	public void testUpdateAgency() {
		String newAddressOne = "25, Green St";
		agency = createNewAgency(company,agencyType );
		agency = agencyService.save(agency);
		Assert.assertEquals(agency.getAddressOne(), addressOne);
		Agency savedAgency = agencyService.findById(agency.getId());
		savedAgency.setAddressOne(newAddressOne);
		agencyService.save(savedAgency);
		Agency modifiedAgency = agencyService.findById(agency.getId());
		Assert.assertEquals(modifiedAgency.getAddressOne(), newAddressOne);
	}

	@Test
	public void testDeleteAgency() {
		Agency agency = createNewAgency( company,agencyType);
		agency = agencyService.save(agency);
		Assert.assertNotNull(agency.getId());
		agencyService.deleteById(agency.getId());
		Assert.assertNull(agencyService.findById(agency.getId()));
	}
}
