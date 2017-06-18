package com.healthcare.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "signature_type", schema = "health_care_v1_dev", catalog = "")
public @Data class SignatureType implements Serializable {

	private static final long serialVersionUID = -93362933217847648L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type;
	@Column(name = "updated_at")
	protected Timestamp updatedAt;

}
