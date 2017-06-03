package com.healthcare.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.model.entity.Employee;
import com.healthcare.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
public class EmployeeControllerTest {
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testSaveEmployee() throws Exception {
		Employee employee = new Employee();
		Mockito.when(employeeService.save(employee)).thenReturn(employee);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(employee);
		this.mockMvc.perform(post("/api/employee").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetEmployee() throws Exception {
		Mockito.when(employeeService.findById(1L)).thenReturn(new Employee());
		this.mockMvc.perform(get("/api/employee/")).andExpect(status().isOk());
	}

	@Test
	public void testFindAllEmployee() throws Exception {
		Mockito.when(employeeService.findAll()).thenReturn(new ArrayList<Employee>());
		this.mockMvc.perform(get("/api/employee")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		Employee employee = new Employee();
		Mockito.when(employeeService.save(employee)).thenReturn(employee);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(employee);
		this.mockMvc.perform(put("/api/employee").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		Mockito.when(employeeService.deleteById(1L)).thenReturn(1L);
		this.mockMvc.perform(delete("/api/employee/1")).andExpect(status().isOk());
	}
}
