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

import com.healthcare.model.entity.AdminPost;
import com.healthcare.repository.AdminPostRepository;
import com.healthcare.service.AdminPostService;

@RunWith(MockitoJUnitRunner.class)
public class AdminPostServiceImplTest {

	private AdminPostService sut;

	@Mock
	private AdminPostRepository adminPostRepository;

	@Before
	public void setUp() {
		sut = new AdminPostServiceImpl(adminPostRepository);
	}

	@Test
	public void testSave() {
		// given
		final AdminPost adminPost = new AdminPost();
		final AdminPost expected = new AdminPost();

		given(adminPostRepository.save(any(AdminPost.class))).willReturn(expected);
		// when
		AdminPost result = sut.save(adminPost);
		// then
		verify(adminPostRepository, only()).save(adminPost);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testGet() {
		// given
		final Long adminPostId = 1L;
		final AdminPost expected = new AdminPost();

		given(adminPostRepository.getOne(anyLong())).willReturn(expected);
		// when
		AdminPost result = sut.get(adminPostId);
		// then
		verify(adminPostRepository, only()).getOne(adminPostId);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testDelete() {
		// given
		final Long adminPostId = 1L;
		// when
		sut.delete(adminPostId);
		// then
		verify(adminPostRepository, only()).delete(adminPostId);
	}
}
