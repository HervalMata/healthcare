package com.healthcare.model.entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Created by pazfernando on 4/30/17.
 */
public @Data class EmployeeActivityPK {
	@Id
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@Id
	@ManyToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;

}
