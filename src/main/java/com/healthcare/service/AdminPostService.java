package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.AdminPost;

public interface AdminPostService extends IService<AdminPost> {
	String KEY = AdminPost.class.getSimpleName();
	
	List<AdminPost> findAll();
}
