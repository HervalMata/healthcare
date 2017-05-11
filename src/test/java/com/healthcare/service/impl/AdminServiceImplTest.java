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

import com.healthcare.model.entity.Admin;
import com.healthcare.repository.AdminRepository;
import com.healthcare.service.AdminService;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {

	private AdminService sut;

	@Mock
	private AdminRepository adminRepository;

	@Before
	public void setUp() {
		sut = new AdminServiceImpl(adminRepository);
	}

	@Test
	public void testSave() {
		// given
		final Admin admin = new Admin();
		final Admin expected = new Admin();

		given(adminRepository.save(any(Admin.class))).willReturn(expected);
		// when
		Admin result = sut.save(admin);
		// then
		verify(adminRepository, only()).save(admin);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testGet() {
		// given
		final Long adminId = 1L;
		final Admin expected = new Admin();

		given(adminRepository.getOne(anyLong())).willReturn(expected);
		// when
		Admin result = sut.get(adminId);
		// then
		verify(adminRepository, only()).getOne(adminId);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testDelete() {
		// given
		final Long adminId = 1L;
		// when
		sut.delete(adminId);
		// then
		verify(adminRepository, only()).delete(adminId);
	}
}
