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
import com.healthcare.model.entity.Activity;
import com.healthcare.model.entity.Visit;
import com.healthcare.model.entity.VisitActivity;
import com.healthcare.model.entity.VisitActivityPK;
import com.healthcare.service.VisitActivityService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
public class VisitActivityControllerTest {
	private MockMvc mockMvc;

	@MockBean
	private VisitActivityService visitActivityService;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testSaveVisitActivity() throws Exception {
		VisitActivity visitAgency = new VisitActivity();
		Mockito.when(visitActivityService.save(visitAgency)).thenReturn(visitAgency);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(visitAgency);
		this.mockMvc.perform(post("/api/visitactivity").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetVisitActivity() throws Exception {
		Mockito.when(visitActivityService.findById(new VisitActivityPK(1L, 1L))).thenReturn(new VisitActivity());
		this.mockMvc.perform(get("/api/visitactivity/1/1")).andExpect(status().isOk());
	}

	@Test
	public void testFindAllVisitActivity() throws Exception {
		Mockito.when(visitActivityService.findAll()).thenReturn(new ArrayList<VisitActivity>());
		this.mockMvc.perform(get("/api/visitactivity")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateVisitActivity() throws Exception {
		VisitActivity visitActivity = new VisitActivity();
		Mockito.when(visitActivityService.save(visitActivity)).thenReturn(visitActivity);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(visitActivity);
		this.mockMvc.perform(put("/api/visitactivity").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteAgency() throws Exception {
		Mockito.when(visitActivityService.deleteById(new VisitActivityPK(1L, 1L))).thenReturn(1L);
		this.mockMvc.perform(get("/api/visitactivity/1/1")).andExpect(status().isOk());
	}

	@Test
	public void testGetVisitActivityByVisit() throws Exception {
		Visit visit = new Visit();
		visit.setId(1L);
		Mockito.when(visitActivityService.findByVisit(visit)).thenReturn(new ArrayList<VisitActivity>());
		this.mockMvc.perform(get("/api/visitactivity/visit/1")).andExpect(status().isOk());
	}

	@Test
	public void testGetVisitActivityByActivity() throws Exception {
		Activity activity = new Activity();
		activity.setId(1L);
		Mockito.when(visitActivityService.findByActivity(activity)).thenReturn(new ArrayList<VisitActivity>());
		this.mockMvc.perform(get("/api/visitactivity/activity/1")).andExpect(status().isOk());
	}
}
