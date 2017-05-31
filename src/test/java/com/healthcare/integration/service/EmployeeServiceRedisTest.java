package com.healthcare.integration.service;

import com.healthcare.model.entity.Agency;
import com.healthcare.model.entity.Employee;
import com.healthcare.model.entity.Review;
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
        employeeRepository.save(employee);
        Employee employeeSaved = employeeRepository.findById(1L);
        Assert.assertNull(employeeSaved);
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
        employeeService.deleteById(employee.getId());

        Employee employeeDeleted = employeeService.findById(1L);
        Assert.assertNull(employeeDeleted);
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
        employee.setReview(new Review());

        return employee;
    }
}
