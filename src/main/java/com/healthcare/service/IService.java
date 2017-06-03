package com.healthcare.service;

public interface IService<T> {
	T save(T user);

	T findById(Long id);

	/**
	 * Delete the entity
	 * @return TODO
	 * 
	 * @return the Redis id deleted
	 * @param id identificator
	 */
	Long deleteById(Long id);
}
