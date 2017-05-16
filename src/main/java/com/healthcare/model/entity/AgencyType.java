package com.healthcare.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "agency_type", schema = "health_care_v1_dev", catalog = "")
public @Data class AgencyType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5513342374278345081L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int status;

}
