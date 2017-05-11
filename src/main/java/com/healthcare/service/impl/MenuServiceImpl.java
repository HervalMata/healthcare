package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Menu;
import com.healthcare.repository.MenuRepository;
import com.healthcare.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	MenuRepository menuRepository;

	@Autowired
	public MenuServiceImpl(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public Menu save(Menu menu) {
		return menuRepository.save(menu);
	}

	@Override
	public void delete(Long id) {
		menuRepository.delete(id);

	}

	@Override
	public Menu get(Long id) {
		return menuRepository.getOne(id);
	}

}
