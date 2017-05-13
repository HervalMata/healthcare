package com.healthcare.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.healthcare.model.entity.Role;
import com.healthcare.repository.RoleRepository;
import com.healthcare.service.RoleService;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

	private RoleService sut;

	@Mock
	private RoleRepository roleService;

	@Before
	public void setUp() {
		sut = new RoleServiceImpl(roleService);
	}

	@Test
	public void testSave() {
		// given
		final Role role = new Role();
		final Role expected = new Role();

		given(roleService.save(any(Role.class))).willReturn(expected);
		// when
		Role result = sut.save(role);
		// then
		verify(roleService, only()).save(role);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testGet() {
		// given
		final Long roleId = 1L;
		final Role expected = new Role();

		given(roleService.getOne(anyLong())).willReturn(expected);
		// when
		Role result = sut.get(roleId);
		// then
		verify(roleService, only()).getOne(roleId);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testDelete() {
		// given
		final Long roleId = 1L;
		// when
		sut.delete(roleId);
		// then
		verify(roleService, only()).delete(roleId);
	}

	@Test
	public void testFindByLevel() {
		// given
		final Long level = 1L;
		final Role expected = new Role();

		given(roleService.findByLevel(anyLong())).willReturn(expected);
		// when
		Role result = sut.findByLevel(level);
		// then
		verify(roleService, only()).findByLevel(level);

		assertThat(result, notNullValue());
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testFindByStatus() {
		// given
		final Long status = 1L;
		final List<Role> expected = null;

		given(roleService.findByStatus(anyLong())).willReturn(expected);
//		given(roleService.findAll(anyListOf(Long.class))).willReturn(expected);
		// when
		List<Role> result = sut.findByStatus(status);
//		List<Long> idList = new ArrayList<Long>();
//		for (Role role : result)
//			idList.add(role.getId());
		// then
		verify(roleService, only()).findByStatus(status);

		assertNull(result);
		assertThat(result, sameInstance(expected));
	}

	@Test
	public void testFindByIdAndStatus() {
		// given
		final Long roleId = 1L;
		final Long status = 1L;
		final List<Role> expected = null;

		given(roleService.findByIdAndStatus(anyLong(), anyLong())).willReturn(expected);
		// when
		List<Role> result = sut.findByIdAndStatus(roleId, status);
//		List<Long> idList = new ArrayList<Long>();
//		for (Role role : result)
//			idList.add(role.getId());
		// then
		verify(roleService, only()).findByIdAndStatus(roleId, status);

		assertNull(result);
		assertThat(result, sameInstance(expected));
	}
}
