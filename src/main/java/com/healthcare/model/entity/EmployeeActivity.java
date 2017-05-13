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
@Table(name = "employee_has_activity", schema = "health_care_v1_dev", catalog = "")
@IdClass(EmployeeActivityPK.class)
public @Data class EmployeeActivity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1976459796513221823L;
	@Id
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@Id
	@ManyToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;

}
