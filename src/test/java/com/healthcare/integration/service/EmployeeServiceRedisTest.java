package com.healthcare.integration.service;

import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.Company;
import com.healthcare.model.entity.Employee;
import com.healthcare.repository.EmployeeRepository;
import com.healthcare.service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeServiceRedisTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Before
    public void setup() {
    }

    @Test
    public void shouldSaveAEmployeeToRedisAndRetrievedItFromRedis() {
        Employee employee = createNewEmployee();
        employee.setId(1L);
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.save(employee);
        Employee employeeSaved = employeeService.findById(1L);
        Assert.assertNotNull(employeeSaved);
    }

    @Test
    public void shouldUpdateATrainingToRedis() {
        String newManager = "manager2";
        String newPosition = "position2";

        Employee employee = createNewEmployee();
        employee.setId(1L);
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.save(employee);
        Employee employeeSaved = employeeService.findById(employee.getId());
        employeeSaved.setManager(newManager);
        employeeSaved.setPosition(newPosition);
        Mockito.when(employeeRepository.save(employeeSaved)).thenReturn(employeeSaved);
        employeeService.save(employeeSaved);

        Employee employeeMofified = employeeService.findById(employee.getId());
        Assert.assertEquals(employeeMofified.getManager(), newManager);
        Assert.assertEquals(employeeMofified.getPosition(), newPosition);
    }

    @Test
    public void shouldDeleteAEmployee() {
        Employee employee = createNewEmployee();
        employee.setId(1L);
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.save(employee);
        Mockito.doNothing().when(employeeRepository).delete(1L);
        Assert.assertNotNull(employeeService.deleteById(employee.getId()));
    }

    @Test
    public void testFindByCompany()
    {
    	Company company1 = new Company();
    	company1.setId(88L);
    	Company company2 = new Company();
    	company2.setId(99L);
    	Agency agency1 = new Agency();
    	agency1.setId(44L);
    	agency1.setCompany(company1);
    	Agency agency2 = new Agency();
    	agency2.setId(55L);
    	agency2.setCompany(company1);
    	
    	Agency agency3 = new Agency();
    	agency3.setId(55L);
    	agency3.setCompany(company2);
    	
    	Employee e1 = createNewEmployee();
    	e1.setId(77L);
    	e1.setAgency(agency1);
    	Mockito.when(employeeRepository.save(e1)).thenReturn(e1);
    	employeeService.save(e1);
    	
    	Employee e2 = createNewEmployee();
    	e2.setId(88L);
    	e2.setAgency(agency2);
    	Mockito.when(employeeRepository.save(e2)).thenReturn(e2);
    	employeeService.save(e2);
    	
    	Employee e3 = createNewEmployee();
    	e3.setId(99L);
    	e3.setAgency(agency3);
    	Mockito.when(employeeRepository.save(e3)).thenReturn(e3);
    	employeeService.save(e3);
    	
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company1.getId(), agency1.getId()).size(), 1);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company1.getId(), null).size(), 2);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company2.getId(), agency3.getId()).size(), 1);
    	Assert.assertEquals(employeeService.findByCampanyIdAndAgencyId(company2.getId(), null).size(), 1);
    }
    
    private Employee createNewEmployee() {
        Employee employee = new Employee();
        Long id = 1L;
        employee.setId(id);
        String firstName = "firstName";
        employee.setFirstName(firstName);
        String lastName = "lastName";
        employee.setLastName(lastName);
        String gender = "gender";
        employee.setGender(gender);
        String socialSecurityNumber = "socialSecurityNumber";
        employee.setSocialSecurityNumber(socialSecurityNumber);
        Calendar dateOfBirth = Calendar.getInstance();
        employee.setDateOfBirth(new Timestamp(dateOfBirth.getTimeInMillis()));
        String physicalExam = "physicalExam";
        employee.setPhysicalExam(physicalExam);
        String certificateName = "certificateName";
        employee.setCertificateName(certificateName);
        Calendar certificateStart = Calendar.getInstance();
        employee.setCertificateStart(new Timestamp(certificateStart.getTimeInMillis()));
        Calendar certificateEnd = Calendar.getInstance();
        employee.setCertificateEnd(new Timestamp(certificateEnd.getTimeInMillis()));
        Calendar workStart = Calendar.getInstance();
        employee.setWorkStart(new Timestamp(workStart.getTimeInMillis()));
        Calendar workEnd = Calendar.getInstance();
        employee.setWorkEnd(new Timestamp(workEnd.getTimeInMillis()));
        String position = "position";
        employee.setPosition(position);
        String manager = "manager";
        employee.setManager(manager);
        String type = "type";
        employee.setType(type);
        String status = "status";
        employee.setStatus(status);
        String backgroundCheck = "backgroundCheck";
        employee.setBackgroundCheck(backgroundCheck);
        employee.setAgency(new Agency());

        return employee;
    }
}
