package com.healthcare.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "meal")
@EqualsAndHashCode(callSuper = true)
public @Data class Meal extends Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3274532181839756840L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "meal_class")
	private String mealClass;
	private String name;
	private String ingredients;
	private String notes;
	@Column(name = "verify_by_nutritionist")
	private Integer verifiedByNutritionist;
	private Integer status;

}
