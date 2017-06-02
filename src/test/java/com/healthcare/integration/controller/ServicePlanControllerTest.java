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
import com.healthcare.model.entity.ServicePlan;
import com.healthcare.service.ServicePlanService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
public class ServicePlanControllerTest {
	private MockMvc mockMvc;

	@MockBean
	private ServicePlanService servicePlanService;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testSaveServicePlan() throws Exception {
		ServicePlan servicePlan = new ServicePlan();
		Mockito.when(servicePlanService.save(servicePlan)).thenReturn(servicePlan);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(servicePlan);
		this.mockMvc.perform(post("/api/serviceplan").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetServicePlan() throws Exception {
		Mockito.when(servicePlanService.findById(1L)).thenReturn(new ServicePlan());
		this.mockMvc.perform(get("/api/serviceplan/1")).andExpect(status().isOk());
	}

	@Test
	public void testFindAllServicePlan() throws Exception {
		Mockito.when(servicePlanService.findAll()).thenReturn(new ArrayList<ServicePlan>());
		this.mockMvc.perform(get("/api/serviceplan")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateServicePlan() throws Exception {
		ServicePlan servicePlan = new ServicePlan();
		Mockito.when(servicePlanService.save(servicePlan)).thenReturn(servicePlan);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(servicePlan);
		this.mockMvc.perform(put("/api/serviceplan").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteServicePlan() throws Exception {
		Mockito.doNothing().when(servicePlanService).deleteById(1L);
		this.mockMvc.perform(get("/api/serviceplan/1")).andExpect(status().isOk());
	}
}
