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

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.healthcare.model.entity.Role;
import com.healthcare.service.RoleService;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

	@Mock
	private RoleService roleService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		RoleController controller = new RoleController(roleService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testCreate() throws Exception {
		// given
		final String levelName = "Level Name";
		final Long level = 1L;
		final Long status = 1L;
		final Long roleId = 1L;
		final Role role = new Role();
		role.setLevelName(levelName);
		role.setLevel(level);
		role.setStatus(status);
		final Role expected = new Role();
		expected.setId(roleId);

		given(roleService.save(any(Role.class))).willReturn(expected);
		// when
		mockMvc.perform(post("/api/role").param("levelName", levelName)).andExpect(status().isOk())
				.andExpect(content().string(roleId.toString()));
		// then
		verify(roleService, only()).save(role);
	}

	@Test
	public void testGet() throws Exception {
		// given
		final Long roleId = 1L;
		final Role role = new Role();
		role.setId(roleId);
		final StringBuilder expectedContent = new StringBuilder("");
		expectedContent.append("{").append("\"id\":").append(roleId).append(",").append("\"level\":0,")
				.append("\"levelName\":null,").append("\"status\":0,").append("\"agency\":null").append("}");

		given(roleService.get(anyLong())).willReturn(role);
		// when
		mockMvc.perform(get("/api/role/" + roleId)).andExpect(status().isOk())
				.andExpect(content().string(expectedContent.toString()));
		// then
		verify(roleService, only()).get(roleId);
	}

	@Test
	public void testGetWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String roleId = "abc";
		// given
		mockMvc.perform(get("/api/role/" + roleId)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(roleService);
	}

	@Test
	public void testSave() throws Exception {
		// given
		final String levelName = "Level Name";
		final Long status = 1L;
		final Long level = 1L;
		final Long roleId = 1L;
		final Role role = new Role();
		role.setId(roleId);
		role.setLevel(level);
		role.setStatus(status);
		role.setLevelName(levelName);

		given(roleService.save(any(Role.class))).willReturn(role);
		// when
		mockMvc.perform(post("/api/role/" + roleId).param("id", roleId.toString()).param("levelName", levelName))
				.andExpect(status().isOk()).andExpect(content().string(isEmptyString()));
		// then
		verify(roleService, only()).save(role);
	}

	@Test
	public void testSaveWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String levelName = "Level Name";
		final String roleId = "abc";
		// when
		mockMvc.perform(post("/api/role/" + roleId).param("id", roleId).param("levelName", levelName))
				.andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(roleService);
	}

	@Test
	public void testDelete() throws Exception {
		// given
		final Long roleId = 1L;
		// when
		mockMvc.perform(delete("/api/role/" + roleId)).andExpect(status().isOk())
				.andExpect(content().string(isEmptyString()));
		// then
		verify(roleService, only()).delete(roleId);
	}

	@Test
	public void testDeleteWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String roleId = "abc";
		// when
		mockMvc.perform(delete("/api/role/" + roleId)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(roleService);
	}

	@Test
	public void testFindByLevel() throws Exception {
		// given
		final Long level = 1L;
		final Role role = new Role();
		role.setLevel(level);
		final StringBuilder expectedContent = new StringBuilder("");
		expectedContent.append("{").append("\"id\":0,").append("\"level\":").append(level).append(",").append("\"levelName\":null,")
				.append("\"status\":0,").append("\"agency\":null").append("}");

		given(roleService.findByLevel(anyLong())).willReturn(role);
		// when
		mockMvc.perform(get("/api/role/level/" + level)).andExpect(status().isOk())
				.andExpect(content().string(expectedContent.toString()));
		// then
		verify(roleService, only()).get(role.getId());
	}

	@Test
	public void testFindByLevelWithIncorrectLevelReturnBadRequestStatus() throws Exception {
		// given
		final String level = "abc";
		// given
		mockMvc.perform(get("/api/role/level/" + level)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(roleService);
	}

	@Test
	public void testFindByStatus() throws Exception {
		// given
		final Long status = 1L;
		final List<Role> role = null;

		given(roleService.findByStatus(status)).willReturn(role);
		// when
		mockMvc.perform(get("/api/role/status/" + status)).andExpect(status().isOk());
		// then
		verify(roleService, only()).findByStatus(status);
	}

	@Test
	public void testFindByStatusWithIncorrectStatusReturnBadRequestStatus() throws Exception {
		// given
		final String status = "abc";
		// given
		mockMvc.perform(get("/api/role/status/" + status)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(roleService);
	}

	@Test
	public void testFindByIdAndStatus() throws Exception {
		// given
		final Long id = 1L;
		final Long status = 1L;
		final List<Role> role = null;

		given(roleService.findByIdAndStatus(id, status)).willReturn(role);
		// when
		mockMvc.perform(get("/api/role/" + id + "/status/" + status)).andExpect(status().isOk());
		// then
		verify(roleService, only()).findByIdAndStatus(id, status);
	}

	@Test
	public void testFindByIdAndStatusWithIncorrectIdAndStatusReturnBadRequestStatus() throws Exception {
		// given
		final String roleId = "abc";
		final String status = "abc";
		// given
		mockMvc.perform(get("/api/role/" + roleId + "/status/" + status)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(roleService);
	}
}
