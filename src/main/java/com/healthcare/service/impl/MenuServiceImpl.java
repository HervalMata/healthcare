package com.healthcare.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Menu;
import com.healthcare.repository.MenuRepository;
import com.healthcare.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuRepository menuRepository;

	@Override
	public Menu save(Menu menu) {
		return menuRepository.save(menu);
	}

	@Override
	public void deleteById(Long id) {
		menuRepository.delete(id);
	}

	@Override
	public Menu findById(Long id) {
		return menuRepository.findOne(id);
	}
}
