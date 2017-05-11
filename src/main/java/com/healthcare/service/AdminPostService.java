package com.healthcare.service;

import com.healthcare.model.entity.AdminPost;

public interface AdminPostService {
	AdminPost save(AdminPost adminPost);

	void delete(Long id);

	AdminPost get(Long id);
}
