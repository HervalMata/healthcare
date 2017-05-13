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
import com.healthcare.model.entity.User;
import com.healthcare.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
// @WebMvcTest(controllers = {UserController.class}, secure = false)
@Transactional
public class UserControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@MockBean
	private UserService userService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void shouldAcceptSaveUserRequest() throws Exception {
		User user = new User();
		Mockito.when(userService.save(user)).thenReturn(user);
		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		String jsonInString = mapper.writeValueAsString(user);

		this.mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldAcceptGetUserRequest() throws Exception {
		Mockito.when(userService.findById(1L)).thenReturn(new User());
		this.mockMvc.perform(get("/api/user/1")).andExpect(status().isOk());
	}

	@Test
	public void shouldAcceptUpdateUserRequest() throws Exception {
		User user = new User();
		Mockito.when(userService.save(user)).thenReturn(user);
		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		String jsonInString = mapper.writeValueAsString(user);

		this.mockMvc.perform(put("/api/user").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldAcceptDeleteUserRequest() throws Exception {
		Mockito.doNothing().when(userService).deleteById(1L);
		this.mockMvc.perform(get("/api/user/1")).andExpect(status().isOk());
	}
}
