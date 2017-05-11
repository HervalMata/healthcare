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

import com.healthcare.model.entity.Menu;
import com.healthcare.repository.MenuRepository;
import com.healthcare.service.MenuService;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {

	private MenuService sut;

	@Mock
	private MenuRepository menuRepository;

	@Before
	public void setUp() {
		sut = new MenuServiceImpl(menuRepository);
	}

	@Test
	public void testSave() {
		// given
		final Menu menu = new Menu();
		final Menu expected = new Menu();

		given(menuRepository.save(any(Menu.class))).willReturn(expected);
		// when
		Menu result = sut.save(menu);
		// then
		verify(menuRepository, only()).save(menu);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testGet() {
		// given
		final Long menuId = 1L;
		final Menu expected = new Menu();

		given(menuRepository.getOne(anyLong())).willReturn(expected);
		// when
		Menu result = sut.get(menuId);
		// then
		verify(menuRepository, only()).getOne(menuId);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testDelete() {
		// given
		final Long menuId = 1L;
		// when
		sut.delete(menuId);
		// then
		verify(menuRepository, only()).delete(menuId);
	}
}
