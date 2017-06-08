package com.healthcare.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.healthcare.model.entity.Visit;
import com.healthcare.service.VisitService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
public class VisitControllerTest {
	private MockMvc mockMvc;
	private Visit visit;

	@Autowired
	private WebApplicationContext wac;
	@MockBean
	private VisitService visitService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		visit = new Visit();
	}

	@Test
	public void shouldAcceptSaveVisitRequest() throws Exception {
		Mockito.when(visitService.save(visit)).thenReturn(visit);

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(visit);

		this.mockMvc.perform(post("/api/visit").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldAcceptGetVisitRequest() throws Exception {
		Mockito.when(visitService.findById(1L)).thenReturn(visit);
		this.mockMvc.perform(get("/api/visit/1")).andExpect(status().isOk());
	}

	@Test
	public void shouldAcceptUpdateVisitRequest() throws Exception {
		Mockito.when(visitService.save(visit)).thenReturn(visit);

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(visit);

		this.mockMvc.perform(put("/api/visit").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldAcceptDeleteVisitRequest() throws Exception {
		Mockito.when(visitService.deleteById(1L)).thenReturn(1L);
		this.mockMvc.perform(get("/api/visit/1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldCheckinVisitRequest() throws Exception {
		Mockito.when(visitService.save(visit)).thenReturn(visit);

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(visit);

		this.mockMvc.perform(put("/api/visit/checkin").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldCheckoutVisitRequest() throws Exception {
		Mockito.when(visitService.save(visit)).thenReturn(visit);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(visit);
		
		this.mockMvc.perform(put("/api/visit/checkout").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
		.andExpect(status().isOk());
	}
	
}
