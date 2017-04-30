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
@Table(name = "trainnig")
public @Data class Training extends Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -797138070537430162L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	@Column(name = "start_time")
	private Timestamp startTime;
	@Column(name = "end_time")
	private Timestamp endTime;
	private int type;
	private String trainer;
	private String location;
	private String note;

}
