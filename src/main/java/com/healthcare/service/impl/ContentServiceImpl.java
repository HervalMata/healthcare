package com.healthcare.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.healthcare.model.entity.Content;
import com.healthcare.repository.ContentRepository;
import com.healthcare.service.ContentService;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {
	private static final String KEY = Content.class.getSimpleName();

	@Autowired
	ContentRepository contentRepository;

	@Autowired
	private RedisTemplate<String, Content> contentRedisTemplate;

	@Override
	public Content save(Content content) {
		content = contentRepository.save(content);
		contentRedisTemplate.opsForHash().put(KEY, content.getId(), content);
		return content;
	}

	@Override
	public Content findById(Long id) {
		Content content = (Content) contentRedisTemplate.opsForHash().get(KEY, id);
		if (content == null)
			content = contentRepository.findOne(id);
		return content;
	}

	@Override
	public Long deleteById(Long id) {
		contentRepository.delete(id);
		return contentRedisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public List<Content> findAll() {
		Map<Object, Object> homeVisitMap = contentRedisTemplate.opsForHash().entries(KEY);
		List<Content> homeVisitList = Collections.arrayToList(homeVisitMap.values().toArray());
		if (homeVisitMap.isEmpty())
			homeVisitList = contentRepository.findAll();
		return homeVisitList;
	}
}
