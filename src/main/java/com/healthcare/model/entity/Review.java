package com.healthcare.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "review")
@EqualsAndHashCode(callSuper = true)
public @Data class Review extends Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8451585526769991568L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@JoinColumn(name = "affect_start")
	private Timestamp affectStart;
	@JoinColumn(name = "affect_end")
	private Timestamp affectEnd;
	private Integer content;
	@ManyToOne
	@JoinColumn(name = "user_id1")
	private User user1;

}
