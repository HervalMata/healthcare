package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Content;

/**
 * Created by Hitesh on 07/06/17.
 */
public interface ContentService extends IService<Content> {

	Content save(Content content);

	List<Content> findAll();
}
