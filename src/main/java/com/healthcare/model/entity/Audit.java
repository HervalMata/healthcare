package com.healthcare.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Audit {
	@Column(name = "created_at")
	protected Timestamp createdAt;
	@Column(name = "updated_at")
	protected Timestamp updatedAt;

}
