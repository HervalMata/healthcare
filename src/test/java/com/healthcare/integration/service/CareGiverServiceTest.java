package com.healthcare.integration.service;

import java.sql.Timestamp;
import java.util.Calendar;

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
import com.healthcare.model.entity.CareGiver;
import com.healthcare.model.entity.Company;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CareGiverService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.EmployeeService;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CareGiverServiceTest extends TestEntityFactory {

    @Autowired
    private CareGiverService careGiverService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AgencyTypeService agencyTypeService;

    private CareGiver careGiver;
    private Agency agency;
    private Company company;
    private AgencyType agencyType;
    
    
    @Before
    public void setup() {
    	company = companyService.save(TestEntityFactory.createNewCompany());
    	agencyType = agencyTypeService.save(TestEntityFactory.createNewAgencyType());
    	agency = agencyService.save(TestEntityFactory.createNewAgency(company, agencyType));
    	careGiver = null;
    }

	@After
	public void rollback() {
		if(careGiver!=null)
			careGiverService.deleteById(careGiver.getId());
		
        agencyService.deleteById(agency.getId());
        agencyTypeService.deleteById(agencyType.getId());
        companyService.deleteById(company.getId());
	}

    /**
     * CareGiver
     */
    Long careGiverType = 1L;
    String language = "pt-BR";
    String secondaryPhone = "99999999";
    String verificationCode = "123";
    String profilePhoto = "profile photo";
    String certificate = "certificate";
    Integer statusCareGiver = 1;
    Calendar vacationStart = Calendar.getInstance();
    Calendar vacationEnd = Calendar.getInstance();


    @Test
    public void shouldSaveACareGiver() {
        careGiver = createNewCareGiver(company,agency);
        careGiver = careGiverService.save(careGiver);
        Assert.assertNotNull(careGiver);
    }

    @Test
    public void shouldGetACareGiver() {
        careGiver = createNewCareGiver(company,agency);
        careGiver = careGiverService.save(careGiver);
        Assert.assertNotNull(careGiverService.findById(careGiver.getId()));
    }

    @Test
    public void shouldUpdateACareGiver() {
        String newLanguage = "en-US";
        String newSecondaryPhone = "888 88888";

        careGiver = createNewCareGiver(company,agency);
        careGiverService.save(careGiver);
        Assert.assertEquals(careGiver.getLanguage(), language);
        Assert.assertEquals(careGiver.getSecondaryPhone(), secondaryPhone);

        CareGiver careGiverSaved = careGiverService.save(careGiver);
        careGiverSaved.setLanguage(newLanguage);
        careGiverSaved.setSecondaryPhone(newSecondaryPhone);
        careGiverService.save(careGiverSaved);

        CareGiver careGiverMofified = careGiverService.findById(careGiverSaved.getId());
        Assert.assertEquals(careGiverMofified.getLanguage(), newLanguage);
        Assert.assertEquals(careGiverMofified.getSecondaryPhone(), newSecondaryPhone);
    }

    @Test
    public void shouldDeleteACareGiver() {
        CareGiver careGiver = createNewCareGiver(company,agency);
        careGiver = careGiverService.save(careGiver);
        Assert.assertNotNull(careGiver);
        careGiverService.deleteById(careGiver.getId());
        Assert.assertNull(careGiverService.findById(careGiver.getId()));
    }

  
    private CareGiver createNewCareGiver(Company company,Agency agency) {
        CareGiver caregiver = new CareGiver();
        caregiver.setCompany(company);
        caregiver.setAgency(agency);
        caregiver.setCareGiverType(careGiverType);
        caregiver.setUsername(username);
        caregiver.setPassword(password);
        caregiver.setFirstName(firstName);
        caregiver.setMiddleName(middleName);
        caregiver.setLastName(lastName);
        caregiver.setGender(gender);
        caregiver.setLanguage(language);
        caregiver.setSocialSecurityNumber(socialSecurityNumber);
        caregiver.setDateOfBirth(new Timestamp(dateOfBirth.getTimeInMillis()));
        caregiver.setEmail(email);
        caregiver.setPhone(phone);
        caregiver.setSecondaryPhone(secondaryPhone);
        caregiver.setVerificationCode(verificationCode);
        caregiver.setAddressType(addressType);
        caregiver.setAddressOne(addressOne);
        caregiver.setAddressTwo(addressTwo);
        caregiver.setCity(city);
        caregiver.setState(state);
        caregiver.setZipcode(zipcode);
        caregiver.setProfilePhoto(profilePhoto);
        caregiver.setCertificate(certificate);
        caregiver.setCertificateStart(new Timestamp(certificateStart.getTimeInMillis()));
        caregiver.setCertificateEnd(new Timestamp(certificateEnd.getTimeInMillis()));
        caregiver.setStatus(statusCareGiver);
        caregiver.setVacationNote(vacationNote);
        caregiver.setVacationStart(new Timestamp(vacationStart.getTimeInMillis()));
        caregiver.setVacationEnd(new Timestamp(vacationEnd.getTimeInMillis()));

        return caregiver;
    }

}
