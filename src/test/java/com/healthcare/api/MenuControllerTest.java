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

import com.healthcare.model.entity.Menu;
import com.healthcare.service.MenuService;

@RunWith(MockitoJUnitRunner.class)
public class MenuControllerTest {

	@Mock
	private MenuService menuService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MenuController controller = new MenuController(menuService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testCreate() throws Exception {
		// given
		final String name = "Menu name";
		final Long menuId = 1L;
		final Menu menu = new Menu();
		menu.setName(name);
		final Menu expected = new Menu();
		expected.setId(menuId);

		given(menuService.save(any(Menu.class))).willReturn(expected);
		// when
		mockMvc.perform(post("/api/menu").param("name", name)).andExpect(status().isOk())
				.andExpect(content().string(menuId.toString()));
		// then
		verify(menuService, only()).save(menu);
	}

	@Test
	public void testGet() throws Exception {
		// given
		final Long menuId = 1L;
		final String name = "Menu name";
		final Menu menu = new Menu();
		menu.setId(menuId);
		final StringBuilder expectedContent = new StringBuilder("");
		expectedContent.append("{").append("\"id\":").append(menuId).append(",").append("\"name\":null,")
				.append("\"url\":null,").append("\"angularUrl\":null,").append("\"page\":null,")
				.append("\"clazz\":null,").append("\"imgUrl\":null,").append("\"createdAt\":null,")
				.append("\"displayOrder\":null,").append("\"status\":null,").append("\"role\":null").append("}");

		given(menuService.get(anyLong())).willReturn(menu);
		// when
		mockMvc.perform(get("/api/menu/" + menuId)).andExpect(status().isOk())
				.andExpect(content().string(expectedContent.toString()));
		// then
		verify(menuService, only()).get(menuId);
	}

	@Test
	public void testGetWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String menuId = "abc";
		// given
		mockMvc.perform(get("/api/menu/" + menuId)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(menuService);
	}

	@Test
	public void testSave() throws Exception {
		// given
		final String name = "Menu name";
		final Long menuId = 1L;
		final Menu menu = new Menu();
		menu.setId(menuId);
		menu.setName(name);

		given(menuService.save(any(Menu.class))).willReturn(menu);
		// when
		mockMvc.perform(post("/api/menu/" + menuId).param("id", menuId.toString()).param("name", name))
				.andExpect(status().isOk()).andExpect(content().string(isEmptyString()));
		// then
		verify(menuService, only()).save(menu);
	}

	@Test
	public void testSaveWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String name = "Menu name";
		final String menuId = "abc";
		// when
		mockMvc.perform(post("/api/menu/" + menuId).param("id", menuId).param("name", name))
				.andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(menuService);
	}

	@Test
	public void testDelete() throws Exception {
		// given
		final Long menuId = 1L;
		// when
		mockMvc.perform(delete("/api/menu/" + menuId)).andExpect(status().isOk())
				.andExpect(content().string(isEmptyString()));
		// then
		verify(menuService, only()).delete(menuId);
	}

	@Test
	public void testDeleteWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String menuId = "abc";
		// when
		mockMvc.perform(delete("/api/menu/" + menuId)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(menuService);
	}
}
