package com.healthcare.api;

import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.internal.verification.VerificationModeFactory.only;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.healthcare.model.entity.Admin;
import com.healthcare.service.AdminService;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

	@Mock
	private AdminService adminService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		AdminController controller = new AdminController(adminService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testCreate() throws Exception {
		// given
		final String username = "Username";
		final Long adminId = 1L;
		final Admin admin = new Admin();
		admin.setUsername(username);
		final Admin expected = new Admin();
		expected.setId(adminId);

		given(adminService.save(any(Admin.class))).willReturn(expected);
		// when
		mockMvc.perform(post("/api/admin").param("username", username)).andExpect(status().isOk())
				.andExpect(content().string(adminId.toString()));
		// then
		verify(adminService, only()).save(admin);
	}

	@Test
	public void testGet() throws Exception {
		// given
		final Long adminId = 1L;
		final Admin admin = new Admin();
		admin.setId(adminId);
		final StringBuilder expectedContent = new StringBuilder("");
		expectedContent.append("{").append("\"id\":").append(adminId).append(",").append("\"username\":null,")
				.append("\"password\":null,").append("\"gender\":null,").append("\"email\":null,")
				.append("\"phone\":null,").append("\"ip\":null,").append("\"status\":0,").append("\"role\":null,")
				.append("\"firstName\":null,").append("\"middleName\":null,").append("\"lastName\":null,")
				.append("\"secondaryPhone\":null,").append("\"profilePhoto\":null,").append("\"deviceAddress\":null,")
				.append("\"rememberToken\":null").append("}");

		given(adminService.get(anyLong())).willReturn(admin);
		// when
		mockMvc.perform(get("/api/admin/" + adminId)).andExpect(status().isOk())
				.andExpect(content().string(expectedContent.toString()));
		// then
		verify(adminService, only()).get(adminId);
	}

	@Test
	public void testGetWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String adminId = "abc";
		// given
		mockMvc.perform(get("/api/admin/" + adminId)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(adminService);
	}

	@Test
	public void testSave() throws Exception {
		// given
		final String username = "Username";
		final Long adminId = 1L;
		final Admin admin = new Admin();
		admin.setId(adminId);
		admin.setUsername(username);

		given(adminService.save(any(Admin.class))).willReturn(admin);
		// when
		mockMvc.perform(post("/api/admin/" + adminId).param("id", adminId.toString()).param("username", username))
				.andExpect(status().isOk()).andExpect(content().string(isEmptyString()));
		// then
		verify(adminService, only()).save(admin);
	}

	@Test
	public void testSaveWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String username = "Username";
		final String adminId = "abc";
		// when
		mockMvc.perform(post("/api/admin/" + adminId).param("id", adminId).param("username", username))
				.andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(adminService);
	}

	@Test
	public void testDelete() throws Exception {
		// given
		final Long adminId = 1L;
		// when
		mockMvc.perform(delete("/api/admin/" + adminId)).andExpect(status().isOk())
				.andExpect(content().string(isEmptyString()));
		// then
		verify(adminService, only()).delete(adminId);
	}

	@Test
	public void testDeleteWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String admin = "abc";
		// when
		mockMvc.perform(delete("/api/admin/" + admin)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(adminService);
	}
}
