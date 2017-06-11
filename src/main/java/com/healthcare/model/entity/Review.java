package com.healthcare.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

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
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

}
