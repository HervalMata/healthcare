package com.healthcare.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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
	@Column(name = "name")
	private String name;
	@Column(name = "status")
	private int status;

}
