package com.healthcare.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "meal")
@EqualsAndHashCode(callSuper = true)
public @Data class Meal extends Audit implements Serializable {

    private static final long serialVersionUID = -6955987587452175363L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "meal_class")
	private String mealClass;
	private String name;
	private String ingredients;
	private String notes;

	@Column(name = "verified_by_nutritionist")
	private Integer verifiedByNutritionist;

	private Integer status;

    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
}
