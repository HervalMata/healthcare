package com.healthcare.model.entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Created by pazfernando on 4/30/17.
 */
public @Data class TrainingEmployeePK {
	@Id
	@ManyToOne
	@JoinColumn(name = "tranning_id")
	private Training training;
	@Id
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

}
