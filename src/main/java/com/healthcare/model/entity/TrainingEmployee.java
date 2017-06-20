package com.healthcare.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Created by pazfernando on 4/30/17.
 */
@Entity
@Table(name = "training_has_employee", schema = "health_care_v1_dev", catalog = "")
@IdClass(TrainingEmployeePK.class)
public @Data class TrainingEmployee implements Serializable {

	private static final long serialVersionUID = 5555550953790346395L;
	@Id
	@ManyToOne
	@JoinColumn(name = "training_id")
	private Training training;
	@Id
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	private String notes;

}
