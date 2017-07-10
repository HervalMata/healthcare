package com.healthcare.integration.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Before
	public void setup() {
		init();
		id=0L;
		redisTemplate.delete(User.class.getSimpleName());
	}

	private Long id = 0L;

	@After
	public void rollback() {
		if(id!=0L)
			userService.deleteById(id);
	}

	@Test
	public void shouldSaveAUser() {
		User user = createNewUser();
		userService.save(user);
		Assert.assertNotNull(user.getId());
		id = user.getId();
	}

	@Test
	public void shouldGetAUser() {
		User user = createNewUser();
		userService.save(user);
		Assert.assertNotNull(userService.findById(user.getId()));
		id = user.getId();

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
		id = userMofified.getId();

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
		user = userService.save(user);

		User user1= createNewUser();
		user1 = userService.save(user1);

		User user2= createNewUser();
		user2 = userService.save(user2);

		List<User> list= userService.findAll();
		assertNotNull(list);
		assertEquals(3, list.size());


		id=user.getId();
		rollback();
		id=user1.getId();
		rollback();
		id=user2.getId();
	}
}
