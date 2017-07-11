<<<<<<< HEAD
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

    @Test
    public void testFindByCompany()
    {
    	Company company1 = newCompany();
    	company1 = companyService.save(company1);
    	Company company2 = newCompany();
    	company2 = companyService.save(company2);
    	
    	Agency agency1 = newAgency();
    	agency1.setCompany(company1);
    	agency1.setCompany1(company1);
    	agency1 = agencyService.save(agency1);
    	
    	Agency agency2 = newAgency();
    	agency2.setCompany(company1);
    	agency2.setCompany1(company1);
    	agency2 = agencyService.save(agency2);
    	
    	Agency agency3 = newAgency();
    	agency3.setCompany(company2);
    	agency3.setCompany1(company2);
    	agency3 = agencyService.save(agency3);
    	
    	Employee e1 = newEmployee();
    	e1.setAgency(agency1); 
    	employeeService.save(e1);
    	
    	Employee e2 = newEmployee();
    	e2.setAgency(agency1); 
    	employeeService.save(e2);
    	
    	Employee e3 = newEmployee();
    	e3.setAgency(agency1); 
    	employeeService.save(e3);
    	
    	Employee e4 = newEmployee();
    	e4.setAgency(agency2); 
    	employeeService.save(e4);
    	
    	Employee e5 = newEmployee();
    	e5.setAgency(agency3); 
    	employeeService.save(e5);
    	
    	Employee e6 = newEmployee();
    	e6.setAgency(agency3); 
    	employeeService.save(e6);
    	
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company1.getId(), null).size(), 4);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company1.getId(), agency1.getId()).size(), 3);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company1.getId(), agency2.getId()).size(), 1);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company2.getId(), null).size(), 2);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company2.getId(), agency3.getId()).size(), 2);
    }
    private Employee createNewEmployee() {
        Employee employee = newEmployee();
        employee.setAgency(createNewAgency());
        return employeeService.save(employee);
    }
    
    private Company createNewCompany() {
    	Company company = newCompany();
    	return companyService.save(company);
    }
    
    private AgencyType createNewAgencyType() {
    	AgencyType agencyType = new AgencyType();
    	agencyType.setName("Agency Type Name");
    	agencyType.setStatus(1);
    	return agencyTypeService.save(agencyType);
    }

	private Employee newEmployee() {
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
		return employee;
	}

    private Agency createNewAgency() {
        Agency agency = newAgency();
        Company company = createNewCompany();
        agency.setCompany(company);
        agency.setCompany1(company);
        return agencyService.save(agency);
    }

	private Agency newAgency() {
		Agency agency = new Agency();
        agency.setName(agencyName);
        agency.setAddressOne(addressOne);
        agency.setAddressTwo(addressTwo);
        agency.setAgencyType(createNewAgencyType());
        agency.setCity(city);
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
		return agency;
	}

	private Company newCompany() {
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
		return company;
	}
}
=======
package com.healthcare.integration.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

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
import com.healthcare.service.AgencyService;
import com.healthcare.service.AgencyTypeService;
import com.healthcare.service.CompanyService;
import com.healthcare.service.EmployeeService;

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


    private Employee employee;
    private Agency agency;
    private Company company;
    private AgencyType agencyType;

    @Before
    public void setup() {
    	company = companyService.save(TestEntityFactory.createNewCompany());
    	agencyType = agencyTypeService.save(TestEntityFactory.createNewAgencyType());
    	agency = agencyService.save(TestEntityFactory.createNewAgency(company, agencyType));
        employee = null;
    }
    
	@After
	public void rollback() {
		if(employee!=null){
			employeeService.deleteById(employee.getId());
		}
        agencyService.deleteById(agency.getId());
        agencyTypeService.deleteById(agencyType.getId());
        companyService.deleteById(company.getId());
	}

    @Test
    public void testSaveEmployee() {
        employee = createNewEmployee();
        employee = employeeService.save(employee);
        Assert.assertNotNull(employee.getId());
    }
    
    @Test
    public void testFindByCampanyIdAndAgencyId(){
    	List<Employee> employeeList = null;
    	employee = employeeService.save(TestEntityFactory.createNewEmployee(agency));
    	employeeList = employeeService.findByCampanyIdAndAgencyId(company.getId(), agency.getId());
    
    	Assert.assertNotNull(employeeList);
    	Assert.assertTrue(employeeList.size()==1);
    	Assert.assertEquals(employee.getId(),employeeList.get(0).getId());
    	
    	
    	// Create 2nd employee with new agency and new company
    	employeeList = null;
    	Company companyNew = companyService.save(TestEntityFactory.createNewCompany());
    	Agency agencyNew = agencyService.save(TestEntityFactory.createNewAgency(companyNew, agencyType));
        Employee employee2 = employeeService.save(TestEntityFactory.createNewEmployee(agencyNew));
    	
        employeeList = employeeService.findByCampanyIdAndAgencyId(companyNew.getId(), agencyNew.getId());
        	
        Assert.assertNotNull(employeeList);
    	Assert.assertTrue(employeeList.size()==1);
    	Assert.assertEquals(employee2.getId(),employeeList.get(0).getId());
    	
    	
    	// Create employee 3 to 2nd company and agency
    	employeeList = null;
    	Employee employee3 =employeeService.save(TestEntityFactory.createNewEmployee(agencyNew));
    	employeeList = employeeService.findByCampanyIdAndAgencyId(companyNew.getId(), agencyNew.getId());
    	Assert.assertNotNull(employeeList);
    	Assert.assertTrue(employeeList.size()==2);
    	
    	//CleanUp
		cleanup(companyNew, agencyNew, employee2, employee3);
    }

	private void cleanup(Company companyNew, Agency agencyNew, Employee employee2, Employee employee3) {
		employeeService.deleteById(employee3.getId());
		employeeService.deleteById(employee2.getId());
        agencyService.deleteById(agencyNew.getId());
        companyService.deleteById(companyNew.getId());
	}

    @Test
    public void testGetEmployee() {
        employee = createNewEmployee();
        employee = employeeService.save(employee);
        Assert.assertNotNull(employeeService.findById(employee.getId()));
    }

    @Test
    public void testUpdateEmployee() {
        String newManager = "manager2";
        String newPosition = "position2";

        String manager = "manager";
        String position = "position";

        employee = createNewEmployee();
        employee = employeeService.save(employee);
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
        employee = employeeService.save(employee);
        Assert.assertNotNull(employee.getId());
        employeeService.deleteById(employee.getId());
        Assert.assertNull(employeeService.findById(employee.getId()));
    }

    @Test
    public void testFindByCompany()
    {
    	Company company1 = newCompany();
    	company1 = companyService.save(company1);
    	Company company2 = newCompany();
    	company2 = companyService.save(company2);
    	
    	AgencyType agencyType = createNewAgencyType();
    	
    	Agency agency1 = newAgency(agencyType);
    	agency1.setCompany(company1);
    	agency1.setCompany1(company1);
    	agency1 = agencyService.save(agency1);
    	
    	Agency agency2 = newAgency(agencyType);
    	agency2.setCompany(company1);
    	agency2.setCompany1(company1);
    	agency2 = agencyService.save(agency2);
    	
    	Agency agency3 = newAgency(agencyType);
    	agency3.setCompany(company2);
    	agency3.setCompany1(company2);
    	agency3 = agencyService.save(agency3);
    	
    	Employee e1 = newEmployee();
    	e1.setAgency(agency1); 
    	e1 = employeeService.save(e1);
    	
    	Employee e2 = newEmployee();
    	e2.setAgency(agency1); 
    	e2 = employeeService.save(e2);
    	
    	Employee e3 = newEmployee();
    	e3.setAgency(agency1); 
    	e3 = employeeService.save(e3);
    	
    	Employee e4 = newEmployee();
    	e4.setAgency(agency2); 
    	e4 = employeeService.save(e4);
    	
    	Employee e5 = newEmployee();
    	e5.setAgency(agency3); 
    	e5 = employeeService.save(e5);
    	
    	Employee e6 = newEmployee();
    	e6.setAgency(agency3); 
    	e6 = employeeService.save(e6);
    	
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company1.getId(), null).size(), 4);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company1.getId(), agency1.getId()).size(), 3);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company1.getId(), agency2.getId()).size(), 1);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company2.getId(), null).size(), 2);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company2.getId(), agency3.getId()).size(), 2);
    
    	
    	//Cleanup
    	agencyTypeService.deleteById(agencyType.getId());
    	companyService.deleteById(company1.getId());
    	companyService.deleteById(company2.getId());
    	agencyService.deleteById(agency1.getId());
    	agencyService.deleteById(agency2.getId());
    	agencyService.deleteById(agency3.getId());
    	employeeService.deleteById(e1.getId());
    	employeeService.deleteById(e2.getId());
    	employeeService.deleteById(e3.getId());
    	employeeService.deleteById(e4.getId());
    	employeeService.deleteById(e5.getId());
    	employeeService.deleteById(e6.getId());
    	
    }
    private Employee createNewEmployee() {
        Employee employee = newEmployee();
        employee.setAgency(agency);
        return employee;
    }
    
    
    private AgencyType createNewAgencyType() {
    	AgencyType agencyType = new AgencyType();
    	agencyType.setName("Agency Type Name");
    	agencyType.setStatus(1);
    	return agencyTypeService.save(agencyType);
    }

	private Employee newEmployee() {
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
		return employee;
	}

   
	private Agency newAgency(AgencyType agencyType) {
		Agency agency = new Agency();
        agency.setName(agencyName);
        agency.setAddressOne(addressOne);
        agency.setAddressTwo(addressTwo);
        agency.setAgencyType(agencyType);
        agency.setCity(city);
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
		return agency;
	}

	private Company newCompany() {
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
		return company;
	}
}
>>>>>>> dev
