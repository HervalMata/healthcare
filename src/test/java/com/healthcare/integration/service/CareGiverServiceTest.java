package com.healthcare.integration.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.transaction.Transactional;

import com.healthcare.model.entity.*;
import com.healthcare.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.EntityFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CareGiverServiceTest extends EntityFactory {

    @Autowired
    private CareGiverService careGiverService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AgencyTypeService agencyTypeService;

    private CareGiver careGiver;
    private Employee employee;
    private Agency agency;
    private Company company;
    private AgencyType agencyType;

    @Before
    public void setup() {
        careGiver = createNewCareGiver();
        careGiverService.save(careGiver);
        employee = createNewEmployee();
        employeeService.save(employee);
        agency = createNewAgency();
        agencyService.save(agency);
        company = createNewCompany();
        companyService.save(company);
        agencyType = createNewAgencyType();
        agencyTypeService.save(agencyType);
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


    /**
     * Employee
     */
    Long id = 1L;
    String firstName = "firstName";
    String lastName = "lastName";
    String gender = "gender";
    String socialSecurityNumber = "socialSecurityNumber";
    Calendar dateOfBirth = Calendar.getInstance();
    String physicalExam = "physicalExam";
    String certificateName = "certificateName";
    Calendar certificateStart = Calendar.getInstance();
    Calendar certificateEnd = Calendar.getInstance();
    Calendar workStart = Calendar.getInstance();
    Calendar workEnd = Calendar.getInstance();
    String position = "position";
    String manager = "manager";
    String type = "type";
    String statusEmp = "status";
    String backgroundCheck = "backgroundCheck";

    /**
     * Agency
     */
    String addressOne = "addressOne";
    String addressTwo = "addressTwo";
    String city = "city";
    String contactPerson = "contactPerson";
    String email = "email";
    String fax = "fax";
    String holiday = "holiday";
    String licenseNo = "licenseNo";
    String phone = "phone";
    String state = "state";
    String timezone = "timezone";
    int trackingMode = 1;
    String zipcode = "01234567";
    String agencyName = "Agency Name";

    /**
     * Company
     */
    String federalTax = "federalTax";
    Calendar federalTaxExpire = Calendar.getInstance();
    Calendar federalTaxStart = Calendar.getInstance();
    String stateTax = "stateTax";
    Calendar stateTaxExpire = Calendar.getInstance();
    Calendar stateTaxStart = Calendar.getInstance();
    Calendar worktimeEnd = Calendar.getInstance();
    Calendar worktimeStart = Calendar.getInstance();
    String daysWork = "5";

    @Test
    public void shouldSaveACareGiver() {
        CareGiver careGiver = createNewCareGiver();
        Assert.assertNotNull(careGiver);
    }

    @Test
    public void shouldGetACareGiver() {
        CareGiver careGiver = createNewCareGiver();
        Assert.assertNotNull(careGiverService.findById(careGiver.getId()));
    }

    @Test
    public void shouldUpdateACareGiver() {
        String newLanguage = "en-US";
        String newSecondaryPhone = "888 88888";

        CareGiver careGiver = createNewCareGiver();
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
        CareGiver careGiver = createNewCareGiver();
        Assert.assertNotNull(careGiver);
        careGiverService.deleteById(careGiver.getId());
        Assert.assertNull(careGiverService.findById(careGiver.getId()));
    }

    private Employee createNewEmployee() {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setGender(gender);
        employee.setSocialSecurityNumber(socialSecurityNumber);
        employee.setDateOfBirth(new Timestamp(dateOfBirth.getTimeInMillis()));
        employee.setPhysicalExam(physicalExam);
        employee.setCertificateName(certificateName);
        employee.setCertificateStart(new Timestamp(certificateStart.getTimeInMillis()));
        employee.setCertificateEnd(new Timestamp(certificateEnd.getTimeInMillis()));
        employee.setWorkStart(new Timestamp(workStart.getTimeInMillis()));
        employee.setWorkEnd(new Timestamp(workEnd.getTimeInMillis()));
        employee.setPosition(position);
        employee.setManager(manager);
        employee.setType(type);
        employee.setStatus(statusEmp);
        employee.setBackgroundCheck(backgroundCheck);
        employee.setAgency(createNewAgency());
        return employeeService.save(employee);
    }

    private Agency createNewAgency() {
        Agency agency = new Agency();
        agency.setName(agencyName);
        Company company = createNewCompany();
        agency.setAddressOne(addressOne);
        agency.setAddressTwo(addressTwo);
        agency.setAgencyType(createNewAgencyType());
        agency.setCity(city);
        agency.setCompany(company);
        agency.setCompany1(company);
        agency.setContactPerson(contactPerson);
        agency.setEmail(email);
        agency.setFax(fax);
        agency.setHoliday(holiday);
        agency.setLicenseNo(licenseNo);
        agency.setPhone(phone);
        agency.setState(state);
        agency.setTimezone(timezone);
        agency.setTrackingMode(trackingMode);
        agency.setZipcode(zipcode);
        return agencyService.save(agency);
    }

    protected Company createNewCompany() {
        Company company = new Company();
        company.setAddressOne(addressOne);
        company.setAddressTwo(addressTwo);
        company.setCity(city);
        company.setEmail(email);
        company.setFax(fax);
        company.setFederalTax(federalTax);
        company.setFederalTaxExpire(new Timestamp(federalTaxExpire.getTimeInMillis()));
        company.setFederalTaxStart(new Timestamp(federalTaxStart.getTimeInMillis()));
        company.setFederalTaxStatus(1);
        company.setLicenseNo(licenseNo);
        company.setName("Company Name");
        company.setPhone(phone);
        company.setState(state);
        company.setStateTax(stateTax);
        company.setStateTaxExpire(new Timestamp(stateTaxExpire.getTimeInMillis()));
        company.setStateTaxStart(new Timestamp(stateTaxStart.getTimeInMillis()));
        company.setStateTaxStatus(1);
        company.setStatus(1);
        company.setWorktimeEnd(new Time(worktimeEnd.getTimeInMillis()));
        company.setWorktimeStart(new Time(worktimeStart.getTimeInMillis()));
        company.setZipcode(zipcode);
        company.setDaysWork(daysWork);
        return companyService.save(company);
    }

    protected AgencyType createNewAgencyType() {
        AgencyType agencyType = new AgencyType();
        agencyType.setName("Agency Type Name");
        agencyType.setStatus(1);
        return agencyTypeService.save(agencyType);
    }

    private CareGiver createNewCareGiver() {
        CareGiver caregiver = new CareGiver();
        caregiver.setCompany(createNewCompany());
        caregiver.setAgency(createNewAgency());
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

        return careGiverService.save(caregiver);
    }

}
