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
import com.healthcare.model.entity.Admin;
import com.healthcare.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Transactional
public class AdminControllerTest {
	private MockMvc mockMvc;

	@MockBean
	private AdminService adminService;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testSaveAdmin() throws Exception {
		Admin admin = new Admin();
		Mockito.when(adminService.save(admin)).thenReturn(admin);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(admin);
		this.mockMvc.perform(post("/api/admin").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetAdmin() throws Exception {
		Mockito.when(adminService.findById(1L)).thenReturn(new Admin());
		this.mockMvc.perform(get("/api/admin/1")).andExpect(status().isOk());
	}

	@Test
	public void testFindAllAdmin() throws Exception {
		Mockito.when(adminService.findAll()).thenReturn(new ArrayList<Admin>());
		this.mockMvc.perform(get("/api/admin")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateAdmin() throws Exception {
		Admin admin = new Admin();
		Mockito.when(adminService.save(admin)).thenReturn(admin);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(admin);
		this.mockMvc.perform(put("/api/admin").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteAdmin() throws Exception {
		Mockito.when(adminService.deleteById(1L)).thenReturn(1L);
		this.mockMvc.perform(get("/api/admin/1")).andExpect(status().isOk());
	}
}
