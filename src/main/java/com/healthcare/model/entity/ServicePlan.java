package com.healthcare.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "serviceplan")
@EqualsAndHashCode(callSuper = false)
public @Data class ServicePlan extends Audit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3982072469879184835L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

}
