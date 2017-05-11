package com.healthcare.integration.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.healthcare.api.UserController;
import com.healthcare.model.entity.User;
import com.healthcare.service.UserService;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void shouldAcceptAnUserForSave() throws Exception {
		User user = new User();
		given(userService.save(user)).willReturn(user);
		this.mvc.perform(get("/api/user/1").accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
	}

}
