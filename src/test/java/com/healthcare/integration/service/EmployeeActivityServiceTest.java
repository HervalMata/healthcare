package com.healthcare.integration.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import com.healthcare.model.entity.*;
import com.healthcare.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.EntityFactory;
import com.healthcare.api.model.VisitRequest;
import com.healthcare.exception.ApplicationException;
import com.healthcare.model.enums.VisitStatusEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeActivityServiceTest extends EntityFactory {

    @Autowired
    private EmployeeActivityService employeeActivityService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AgencyTypeService agencyTypeService;

    private Employee employee;
    private Activity activity;
    private Agency agency;
    private Company company;
    private AgencyType agencyType;

    @Before
    public void setup() {
        employee = createNewEmployee();
        employeeService.save(employee);
        activity = createNewActivity();
        activityService.save(activity);
        agency = createNewAgency();
        agencyService.save(agency);
        company = createNewCompany();
        companyService.save(company);
        agencyType = createNewAgencyType();
        agencyTypeService.save(agencyType);
    }

    /**
     * Activity
     */

    String name = "name";
    Integer status = 1;
    Calendar createdAt = Calendar.getInstance();
    Calendar updatedAt = Calendar.getInstance();
    Calendar timeStart = Calendar.getInstance();
    Calendar timeEnd = Calendar.getInstance();
    String date = "date";
    String location = "location";
    String note = "note";

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
    public void shouldSaveAEmployeeActivity() {
        EmployeeActivity employeeActivity = createNewEmployeeActivity(employee, activity);
        Assert.assertNotNull(employeeActivity);
    }

    @Test
    public void shouldGetAEmployeeActivity() {
        EmployeeActivity employeeActivity = createNewEmployeeActivity(employee, activity);
        Assert.assertNotNull(employeeActivityService.findByEmployeeActivity(employeeActivity));
    }

    @Test
    public void shouldUpdateAEmployeeActivity() {
        Employee newEmployee = createNewEmployee();
        Activity newActivity = createNewActivity();

        EmployeeActivity employeeActivity = createNewEmployeeActivity(employee, activity);
        employeeActivityService.save(employeeActivity);
        Assert.assertEquals(employeeActivity.getEmployee(), employee);
        Assert.assertEquals(employeeActivity.getActivity(), activity);

        EmployeeActivity employeeActivitySaved = employeeActivityService.save(employeeActivity);
        employeeActivitySaved.setEmployee(newEmployee);
        employeeActivitySaved.setActivity(newActivity);
        employeeActivityService.save(employeeActivitySaved);

        EmployeeActivity employeeActiityMofified = employeeActivityService.findById(employeeActivitySaved.getId());
        Assert.assertEquals(employeeActiityMofified.getEmployee(), newEmployee);
        Assert.assertEquals(employeeActiityMofified.getActivity(), newActivity);
    }

    @Test
    public void shouldDeleteAEmployeeActivity() {
        EmployeeActivity employeeActivity = createNewEmployeeActivity(employee, activity);
        Assert.assertNotNull(employeeActivity);
        employeeActivityService.deleteByEmployeeActivity(employeeActivity);
        Assert.assertNull(employeeActivityService.findById(employeeActivity.getId()));
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

    private Activity createNewActivity() {
        Activity activity = new Activity();
        activity.setName(name);
        activity.setStatus(status);
        //activity.setCreatedAt(new Timestamp(createdAt.getTimeInMillis()));
        //activity.setUpdatedAt(new Timestamp(updatedAt.getTimeInMillis()));
        activity.setInstructorEmployee(createNewEmployee());
        activity.setTimeStart(String.valueOf(new Time(timeStart.getTimeInMillis())));
        activity.setTimeEnd(String.valueOf(new Time(timeEnd.getTimeInMillis())));
        activity.setDate(date);
        activity.setLocation(location);
        activity.setNote(note);

        return activityService.save(activity);
    }

    protected EmployeeActivity createNewEmployeeActivity(Employee employee, Activity activity) {
        EmployeeActivity employeeActivity = new EmployeeActivity();
        employeeActivity.setId(id);
        employeeActivity.setEmployee(employee);
        employeeActivity.setActivity(activity);
        return employeeActivity;
    }
}
