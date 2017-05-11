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

import com.healthcare.model.entity.Report;
import com.healthcare.service.ReportService;

@RunWith(MockitoJUnitRunner.class)
public class ReportControllerTest {

	@Mock
	private ReportService reportService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		ReportController controller = new ReportController(reportService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testCreate() throws Exception {
		// given
		final String reportTitle = "Report Title";
		final Long reportId = 1L;
		final Report report = new Report();
		report.setReportTitle(reportTitle);
		final Report expected = new Report();
		expected.setId(reportId);

		given(reportService.save(any(Report.class))).willReturn(expected);
		// when
		mockMvc.perform(post("/api/report").param("reportTitle", reportTitle)).andExpect(status().isOk())
				.andExpect(content().string(reportId.toString()));
		// then
		verify(reportService, only()).save(report);
	}

	@Test
	public void testGet() throws Exception {
		// given
		final Long reportId = 1L;
		final Report report = new Report();
		report.setId(reportId);
		final StringBuilder expectedContent = new StringBuilder("");
		expectedContent.append("{").append("\"id\":").append(reportId).append(",").append("\"baseId\":0,")
				.append("\"company\":null,").append("\"admin\":null,").append("\"reportTitle\":null,")
				.append("\"startDate\":null,").append("\"endDate\":null,").append("\"dataColumns\":null,")
				.append("\"format\":null,").append("\"downloadAt\":null,").append("\"createdAt\":null,")
				.append("\"role\":null").append("}");

		given(reportService.get(anyLong())).willReturn(report);
		// when
		mockMvc.perform(get("/api/report/" + reportId)).andExpect(status().isOk())
				.andExpect(content().string(expectedContent.toString()));
		// then
		verify(reportService, only()).get(reportId);
	}

	@Test
	public void testGetWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String reportId = "abc";
		// given
		mockMvc.perform(get("/api/report/" + reportId)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(reportService);
	}

	@Test
	public void testSave() throws Exception {
		// given
		final String reportTitle = "Report Title";
		final Long reportId = 1L;
		final Report report = new Report();
		report.setId(reportId);
		report.setReportTitle(reportTitle);

		given(reportService.save(any(Report.class))).willReturn(report);
		// when
		mockMvc.perform(
				post("/api/report/" + reportId).param("id", reportId.toString()).param("reportTitle", reportTitle))
				.andExpect(status().isOk()).andExpect(content().string(isEmptyString()));
		// then
		verify(reportService, only()).save(report);
	}

	@Test
	public void testSaveWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String reportTitle = "Report Title";
		final String reportId = "abc";
		// when
		mockMvc.perform(post("/api/report/" + reportId).param("id", reportId).param("reportTitle", reportTitle))
				.andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(reportService);
	}

	@Test
	public void testDelete() throws Exception {
		// given
		final Long reportId = 1L;
		// when
		mockMvc.perform(delete("/api/report/" + reportId)).andExpect(status().isOk())
				.andExpect(content().string(isEmptyString()));
		// then
		verify(reportService, only()).delete(reportId);
	}

	@Test
	public void testDeleteWithIncorrectIdReturnBadRequestStatus() throws Exception {
		// given
		final String reportId = "abc";
		// when
		mockMvc.perform(delete("/api/report/" + reportId)).andExpect(status().isBadRequest());
		// then
		verifyZeroInteractions(reportService);
	}
}
