package com.healthcare.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "training")
@EqualsAndHashCode(callSuper = true)
public @Data class Training extends Audit implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -797138070537430162L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "start_time")
	private Timestamp startTime;
	@Column(name = "end_time")
	private Timestamp endTime;
	@Column(name = "type")
	private int type;
	@Column(name = "trainer")
	private String trainer;
	@Column(name = "location")
	private String location;
	@Column(name = "note")
	private String note;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public Long getId() {
		return id;
	}
}
