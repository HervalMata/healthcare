package com.healthcare.integration.service;

import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.AgencyType;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Employee;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AgencyTypeService agencyTypeService;

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
    String status = "status";
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


    @Before
    public void setup() {
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = createNewEmployee();
        employeeService.save(employee);
        Assert.assertNotNull(employee.getId());
    }

    @Test
    public void testGetEmployee() {
        Employee employee = createNewEmployee();
        employeeService.save(employee);
        Assert.assertNotNull(employeeService.findById(employee.getId()));
    }

    @Test
    public void testUpdateEmployee() {
        String newManager = "manager2";
        String newPosition = "position2";

        String manager = "manager";
        String position = "position";

        Employee employee = createNewEmployee();
        employeeService.save(employee);
        Assert.assertEquals(employee.getManager(), manager);
        Assert.assertEquals(employee.getPosition(), position);
        Employee employeeSaved = employeeService.findById(employee.getId());
        employeeSaved.setManager(newManager);
        employeeSaved.setPosition(newPosition);
        employeeService.save(employeeSaved);
        Employee employeeMofified = employeeService.findById(employee.getId());
        Assert.assertEquals(employeeMofified.getManager(), newManager);
        Assert.assertEquals(employeeMofified.getPosition(), newPosition);
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = createNewEmployee();
        employeeService.save(employee);
        Assert.assertNotNull(employee.getId());
        employeeService.deleteById(employee.getId());
        Assert.assertNull(employeeService.findById(employee.getId()));
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
        employee.setStatus(status);
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

    private Company createNewCompany() {
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
        return companyService.save(company);
    }

    private AgencyType createNewAgencyType() {
        AgencyType agencyType = new AgencyType();
        agencyType.setName("Agency Type Name");
        agencyType.setStatus(1);
        return agencyTypeService.save(agencyType);
    }
}
