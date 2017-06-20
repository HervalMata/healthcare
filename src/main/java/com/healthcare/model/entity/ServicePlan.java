package com.healthcare.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "serviceplan")
@EqualsAndHashCode(callSuper = true)
public @Data class ServicePlan extends Audit implements Serializable {

	private static final long serialVersionUID = -8777670249499595658L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "approvedby")
	private String approvedBy;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@Column(name = "plan_start")
	private Timestamp planStart;
	@Column(name = "plan_end")
	private Timestamp planEnd;
	private String days;
	@Column(name = "docurl")
	private String docUrl;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
