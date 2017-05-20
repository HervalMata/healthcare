package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Menu;
import com.healthcare.repository.MenuRepository;
import com.healthcare.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuRepository menuRepository;

	@Autowired
	private RedisTemplate<String, Menu> menuRedisTemplate;
	
	private static String MENU_KEY = "Menu";

	@Override
	public Menu save(Menu menu) {
		menu = menuRepository.save(menu);
		menuRedisTemplate.opsForHash().put(MENU_KEY, menu.getId(), menu);
		return menu;
	}

	@Override
	public void deleteById(Long id) {
		menuRepository.delete(id);
		menuRedisTemplate.opsForHash().delete(MENU_KEY, id);
	}

	@Override
	public Menu findById(Long id) {
		Menu menu = (Menu) menuRedisTemplate.opsForHash().get(MENU_KEY, id);
		if (menu == null)
			menu = menuRepository.findOne(id);
		return menu;
	}
}
