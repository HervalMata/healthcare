package com.healthcare.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.model.entity.Training;
import com.healthcare.service.TrainingService;
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
public class TrainingControllerTest {
	private MockMvc mockMvc;

	@MockBean
	private TrainingService trainingService;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testSaveTraining() throws Exception {
		Training training = new Training();
		Mockito.when(trainingService.save(training)).thenReturn(training);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(training);
		this.mockMvc.perform(post("/api/training").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetTraining() throws Exception {
		Mockito.when(trainingService.findById(1L)).thenReturn(new Training());
		this.mockMvc.perform(get("/api/training/1")).andExpect(status().isOk());
	}

	@Test
	public void testFindAllTraining() throws Exception {
		Mockito.when(trainingService.findAll()).thenReturn(new ArrayList<Training>());
		this.mockMvc.perform(get("/api/training")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateTraining() throws Exception {
		Training training = new Training();
		Mockito.when(trainingService.save(training)).thenReturn(training);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(training);
		this.mockMvc.perform(put("/api/training").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteTraining() throws Exception {
		Mockito.when(trainingService.deleteById(1L)).thenReturn(1L);
		this.mockMvc.perform(get("/api/training/1")).andExpect(status().isOk());
	}
}
