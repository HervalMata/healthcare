package com.healthcare.service;

import com.healthcare.model.entity.Menu;

public interface MenuService {
	Menu save(Menu adminPost);

	void delete(Long id);

	Menu get(Long id);
}
