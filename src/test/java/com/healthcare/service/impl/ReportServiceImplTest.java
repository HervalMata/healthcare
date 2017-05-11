package com.healthcare.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.healthcare.model.entity.Report;
import com.healthcare.repository.ReportRepository;
import com.healthcare.service.ReportService;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTest {

	private ReportService sut;

	@Mock
	private ReportRepository reportRepository;

	@Before
	public void setUp() {
		sut = new ReportServiceImpl(reportRepository);
	}

	@Test
	public void testSave() {
		// given
		final Report report = new Report();
		final Report expected = new Report();

		given(reportRepository.save(any(Report.class))).willReturn(expected);
		// when
		Report result = sut.save(report);
		// then
		verify(reportRepository, only()).save(report);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testGet() {
		// given
		final Long reportId = 1L;
		final Report expected = new Report();

		given(reportRepository.getOne(anyLong())).willReturn(expected);
		// when
		Report result = sut.get(reportId);
		// then
		verify(reportRepository, only()).getOne(reportId);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testDelete() {
		// given
		final Long reportId = 1L;
		// when
		sut.delete(reportId);
		// then
		verify(reportRepository, only()).delete(reportId);
	}
}
