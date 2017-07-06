package com.healthcare.service;

import java.util.List;

import com.healthcare.model.entity.Caregiver;

/**
 * Created by Hitesh on 06/24/17.
 */
public interface CareGiverService extends IService<Caregiver> {

	Caregiver save(Caregiver careGiver);

	List<Caregiver> findAll();
}
