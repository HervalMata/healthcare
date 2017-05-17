package com.healthcare.service;

public interface IService<T> {
	T save(T entity);

	T findById(Long id);

	void deleteById(Long id);
}
