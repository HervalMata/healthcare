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
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Employee;
import com.healthcare.model.entity.Training;
import com.healthcare.model.entity.TrainingEmployee;
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.EmployeeService;
import com.healthcare.service.TrainingEmployeeService;
import com.healthcare.service.TrainingService;

/**
 * Created by jean on 03/07/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TrainingEmployeeServiceTest{

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
    private TrainingEmployee trainingEmployee ;
    
	@Before
	public void setup() {
		company = companyService.save(TestEntityFactory.createNewCompany());
		agencyType = agencyTypeService.save(TestEntityFactory.createNewAgencyType());
		agency = agencyService.save(TestEntityFactory.createNewAgency(company, agencyType));
		employee = employeeService.save(TestEntityFactory.createNewEmployee(agency));
	    training = createNewTraining();
	    trainingService.save(training);
	    trainingEmployee = null;
	}

	@After
	public void rollback() {
		if(trainingEmployee!=null)
			trainingEmployeeService.deleteById(trainingEmployee.getId());
		
		trainingService.deleteById(training.getId());
        employeeService.deleteById(employee.getId());
		agencyService.deleteById(agency.getId());
        agencyTypeService.deleteById(agencyType.getId());
        companyService.deleteById(company.getId());
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
    	trainingEmployee = createNewTrainingEmployee(employee, training);
    	trainingEmployee = trainingEmployeeService.save(trainingEmployee);
        Assert.assertNotNull(trainingEmployee);
    }

    @Test
    public void shouldGetATrainingEmployee() {
        trainingEmployee = createNewTrainingEmployee(employee, training);
        trainingEmployee = trainingEmployeeService.save(trainingEmployee);
        Assert.assertNotNull(trainingEmployeeService.findByTrainingEmployee(trainingEmployee));
    }

   @Test
    public void shouldUpdateATrainingEmployee() {
        Employee newEmployee = createNewEmployee();
        Training newTraining = createNewTraining();

        trainingEmployee = createNewTrainingEmployee(employee, training);
        trainingEmployee = trainingEmployeeService.save(trainingEmployee);
        Assert.assertEquals(trainingEmployee.getEmployee(), employee);
        Assert.assertEquals(trainingEmployee.getTraining(), training);

        trainingEmployee.setEmployee(newEmployee);
        trainingEmployee.setTraining(newTraining);
        trainingEmployee = trainingEmployeeService.save(trainingEmployee);

        TrainingEmployee trainingEmployeeMofified = trainingEmployeeService.findById(trainingEmployee.getId());
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


    protected Training createNewTraining() {
        Training training = new Training();
        training.setTitle(titile);
        training.setStartTime(new Timestamp(createdAt.getTimeInMillis()));
        training.setEndTime(new Timestamp(createdAt.getTimeInMillis()));
        training.setType(typeTraining);
        training.setTrainer(trainer);
        training.setLocation(location);
        training.setNote(note);

        return training;
    }

    protected TrainingEmployee createNewTrainingEmployee(Employee employee, Training training) {
        TrainingEmployee trainingEmployee = new TrainingEmployee();
        trainingEmployee.setId(id);
        trainingEmployee.setEmployee(employee);
        trainingEmployee.setTraining(training);
        return trainingEmployee;
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
        employee.setAgency(agency);
        return employee;
    }
   
}

