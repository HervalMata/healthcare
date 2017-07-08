package com.healthcare.integration.service;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.EntityFactory;
import com.healthcare.model.entity.User;
import com.healthcare.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest extends EntityFactory {
	@Autowired
	private UserService userService;

	@Before
	public void setup() {
		init();
	}

	@Test
	public void shouldSaveAUser() {
		User user = createNewUser();
		userService.save(user);
		Assert.assertNotNull(user.getId());
	}

	@Test
	public void shouldGetAUser() {
		User user = createNewUser();
		userService.save(user);
		Assert.assertNotNull(userService.findById(user.getId()));
	}

	@Test
	public void shouldUpdateAUser() {
		String newPhone = "5967897788";
		String newAddress = "Av. 57 y 23 St.";

		User user = createNewUser();
		userService.save(user);
		Assert.assertEquals(user.getPhone(), phone);
		Assert.assertEquals(user.getAddressOne(), addressOne);
		User userSaved = userService.findById(user.getId());
		userSaved.setPhone(newPhone);
		userSaved.setAddressOne(newAddress);
		userService.save(userSaved);
		User userMofified = userService.findById(user.getId());
		Assert.assertEquals(userMofified.getPhone(), newPhone);
		Assert.assertEquals(userMofified.getAddressOne(), newAddress);
	}

	@Test
	public void shouldDeleteAUser() {
		User user = createNewUser();
		userService.save(user);
		Assert.assertNotNull(user.getId());
		userService.deleteById(user.getId());
		Assert.assertNull(userService.findById(user.getId()));
	}
	
	@Test
	public void souldFindAll() {
		User user = createNewUser();
		userService.save(user);

		User user1= createNewUser();
		userService.save(user1);

		User user2= createNewUser();
		userService.save(user2);
		
		Assert.assertNotNull(userService.findAll());
		Assert.assertTrue(userService.findAll().size()>=3);
	}
}
