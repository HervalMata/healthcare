package com.healthcare.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "role")
public @Data class Role implements Serializable {

	private static final long serialVersionUID = -6360665934926249915L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private long level;
	@Column(name = "level_name")
	private String levelName;
	private long status;
	@ManyToOne
	@JoinColumn(name = "agency_id")
	private Agency agency;
}