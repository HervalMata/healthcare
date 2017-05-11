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

import com.healthcare.model.entity.AdminPost;
import com.healthcare.service.AdminPostService;

@RunWith(MockitoJUnitRunner.class)
public class AdminPostControllerTest {

	@Mock
	private AdminPostService adminPostService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		AdminPostController controller = new AdminPostController(adminPostService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testCreate() throws Exception {
		// given
		final String postText = "Admin Post Text";
		final Long adminPostId = 1L;
		final AdminPost adminPost = new AdminPost();
		adminPost.setPostText(postText);
		final AdminPost expected = new AdminPost();
		expected.setId(adminPostId);

		given(adminPostService.save(any(AdminPost.class))).willReturn(expected);
		// when
		mockMvc.perform(post("/api/adminpost").param("postText", postText)).andExpect(status().isOk())
				.andExpect(content().string(adminPostId.toString()));
		// then
		verify(adminPostService, only()).save(adminPost);
	}

	@Test
	public void testGet() throws Exception {
		// given
		final Long adminPostId = 1L;
		final String postText = "Admin Post Text";
		final AdminPost adminPost = new AdminPost();
		adminPost.setId(adminPostId);
		final StringBuilder expectedContent = new StringBuilder("");
		expectedContent.append("{").append("\"id\":").append(adminPostId).append(",").append("\"postText\":null,")
				.append("\"postDate\":null,").append("\"status\":0,").append("\"admin\":null").append("}");

		given(adminPostService.get(anyLong())).willReturn(adminPost);

		// when
		mockMvc.perform(get("/api/adminpost/" + adminPostId)).andExpect(status().isOk())
				.andExpect(content().string(expectedContent.toString()));
		// then
		verify(adminPostService, only()).get(adminPostId);
	}

	@Test
	public void testGetWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String adminPostId = "abc";
		// given
		mockMvc.perform(get("/api/adminpost/" + adminPostId)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(adminPostService);
	}

	@Test
	public void testSave() throws Exception {
		// given
		final String postText = "Admin Post Text";
		final Long adminPostId = 1L;
		final AdminPost adminPost = new AdminPost();
		adminPost.setId(adminPostId);
		adminPost.setPostText(postText);

		given(adminPostService.save(any(AdminPost.class))).willReturn(adminPost);
		// when
		mockMvc.perform(
				post("/api/adminpost/" + adminPostId).param("id", adminPostId.toString()).param("postText", postText))
				.andExpect(status().isOk()).andExpect(content().string(isEmptyString()));
		// then
		verify(adminPostService, only()).save(adminPost);
	}

	@Test
	public void testSaveWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String postText = "Admin Post Text";
		final String adminPostId = "abc";
		// when
		mockMvc.perform(post("/api/adminpost/" + adminPostId).param("id", adminPostId).param("postText", postText))
				.andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(adminPostService);
	}

	@Test
	public void testDelete() throws Exception {
		// given
		final Long adminPostId = 1L;
		// when
		mockMvc.perform(delete("/api/adminpost/" + adminPostId)).andExpect(status().isOk())
				.andExpect(content().string(isEmptyString()));
		// then
		verify(adminPostService, only()).delete(adminPostId);
	}

	@Test
	public void testDeleteWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String adminPostId = "abc";
		// when
		mockMvc.perform(delete("/api/adminpost/" + adminPostId)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(adminPostService);
	}
}
