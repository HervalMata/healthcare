package com.healthcare.integration.service;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.repository.AgencyRepository;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AgencyServiceRedisTest extends TestEntityFactory{
	@Autowired
	private AgencyService agencyService;
	
	@MockBean
	private AgencyRepository agencyRepository;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AgencyTypeService agencyTypeService;

	private Company company;
	private AgencyType agencyType;
	private Agency agency =null;
	private Long id = 7L;

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
		agency.setId(id);
		Mockito.when(agencyRepository.save(agency)).thenReturn(agency);
		agency = agencyService.save(agency);
		Agency savedAgency = agencyService.findById(agency.getId());
		Assert.assertNotNull(savedAgency);
	}

	@Test
	public void testUpdateAgency() {
		String newAddressOne = "25, Green St";
		agency = createNewAgency(company,agencyType);
		agency.setId(id);
		Mockito.when(agencyRepository.save(agency)).thenReturn(agency);
		agency = agencyService.save(agency);
		Agency savedAgency = agencyService.findById(agency.getId());
		savedAgency.setAddressOne(newAddressOne);
		Mockito.when(agencyRepository.save(savedAgency)).thenReturn(savedAgency);
		agencyService.save(savedAgency);
		Agency modifiedAgency = agencyService.findById(agency.getId());
		Assert.assertEquals(modifiedAgency.getAddressOne(), newAddressOne);
	}

	@Test
	public void testDeleteAgency() {
		Agency agency = createNewAgency(company,agencyType);
		agency.setId(id);
		Mockito.when(agencyRepository.save(agency)).thenReturn(agency);
		agency = agencyService.save(agency);
		Mockito.doNothing().when(agencyRepository).delete(agency.getId());
		Assert.assertNotNull(agencyService.deleteById(agency.getId()));
	}


}
