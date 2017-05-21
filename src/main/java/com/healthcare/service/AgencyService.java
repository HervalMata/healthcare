package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Agency;

public interface AgencyService extends IService<Agency> {
	List<Agency> findAll();
}
