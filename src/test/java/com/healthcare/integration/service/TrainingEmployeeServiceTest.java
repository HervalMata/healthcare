package com.healthcare.integration.service;

import com.healthcare.EntityFactory;
import com.healthcare.model.entity.*;
import com.healthcare.service.*;
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

/**
 * Created by jean on 03/07/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TrainingEmployeeServiceTest extends EntityFactory {

    @Autowired
    private TrainingEmployeeService trainingEmployeeService;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AgencyTypeService agencyTypeService;
    private Employee employee;
    private Training training;
    private Agency agency;
    private Company company;
    private AgencyType agencyType;

    @Before
    public void setup() {
        employee = createNewEmployee();
        employeeService.save(employee);
        training = createNewTraining();
        trainingService.save(training);
        agency = createNewAgency();
        agencyService.save(agency);
        company = createNewCompany();
        companyService.save(company);
        agencyType = createNewAgencyType();
        agencyTypeService.save(agencyType);
    }

    /**
     * Training
     */

    String name = "name";
    Integer status = 1;
    Calendar createdAt = Calendar.getInstance();
    String titile = "title";
    int typeTraining = 1;
    String trainer = "trainer";
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
    public void shouldSaveATrainingEmployee() {
        TrainingEmployee employeeActivity = createNewTrainingEmployee(employee, training);
        Assert.assertNotNull(employeeActivity);
    }

    @Test
    public void shouldGetATrainingEmployee() {
        TrainingEmployee trainingEmployee = createNewTrainingEmployee(employee, training);
        Assert.assertNotNull(trainingEmployeeService.findByTrainingEmployee(trainingEmployee));
    }

    @Test
    public void shouldUpdateATrainingEmployee() {
        Employee newEmployee = createNewEmployee();
        Training newTraining = createNewTraining();

        TrainingEmployee trainingEmployee = createNewTrainingEmployee(employee, training);
        trainingEmployeeService.save(trainingEmployee);
        Assert.assertEquals(trainingEmployee.getEmployee(), employee);
        Assert.assertEquals(trainingEmployee.getTraining(), training);

        TrainingEmployee trainingEmployeeSaved = trainingEmployeeService.save(trainingEmployee);
        trainingEmployeeSaved.setEmployee(newEmployee);
        trainingEmployeeSaved.setTraining(newTraining);
        trainingEmployeeService.save(trainingEmployeeSaved);

        TrainingEmployee trainingEmployeeMofified = trainingEmployeeService.findById(trainingEmployeeSaved.getId());
        Assert.assertEquals(trainingEmployeeMofified.getEmployee(), newEmployee);
        Assert.assertEquals(trainingEmployeeMofified.getTraining(), newTraining);
    }

    @Test
    public void shouldDeleteATrainingEmployee() {
        TrainingEmployee trainingEmployee = createNewTrainingEmployee(employee, training);
        Assert.assertNotNull(trainingEmployee);
        trainingEmployeeService.deleteByTrainingEmployee(trainingEmployee);
        Assert.assertNull(trainingEmployeeService.findById(trainingEmployee.getId()));
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

    protected Training createNewTraining() {
        Training training = new Training();
        training.setTitle(titile);
        training.setStartTime(new Timestamp(createdAt.getTimeInMillis()));
        training.setEndTime(new Timestamp(createdAt.getTimeInMillis()));
        training.setType(typeTraining);
        training.setTrainer(trainer);
        training.setLocation(location);
        training.setNote(note);

        return trainingService.save(training);
    }

    protected TrainingEmployee createNewTrainingEmployee(Employee employee, Training training) {
        TrainingEmployee trainingEmployee = new TrainingEmployee();
        trainingEmployee.setId(id);
        trainingEmployee.setEmployee(employee);
        trainingEmployee.setTraining(training);
        return trainingEmployee;
    }
}

