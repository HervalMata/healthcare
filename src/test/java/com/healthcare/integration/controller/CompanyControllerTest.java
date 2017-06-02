package com.healthcare.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.transaction.Transactional;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.model.entity.Company;
import com.healthcare.service.CompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
public class CompanyControllerTest {
	private MockMvc mockMvc;

	@MockBean
	private CompanyService companyService;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testSaveCompany() throws Exception {
		Company company = new Company();
		Mockito.when(companyService.save(company)).thenReturn(company);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(company);
		this.mockMvc.perform(post("/api/company").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetCompany() throws Exception {
		Mockito.when(companyService.findById(1L)).thenReturn(new Company());
		this.mockMvc.perform(get("/api/company/1")).andExpect(status().isOk());
	}

	@Test
	public void testFindAllCompany() throws Exception {
		Mockito.when(companyService.findAll()).thenReturn(new ArrayList<Company>());
		this.mockMvc.perform(get("/api/company")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateCompany() throws Exception {
		Company company = new Company();
		Mockito.when(companyService.save(company)).thenReturn(company);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(company);
		this.mockMvc.perform(put("/api/company").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteCompany() throws Exception {
		Mockito.when(companyService.deleteById(1L)).thenReturn(1L);
		this.mockMvc.perform(get("/api/company/1")).andExpect(status().isOk());
	}
}
