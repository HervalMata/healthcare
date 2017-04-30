package com.healthcare.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
public @Data class Audit {
	@Column(name = "created_at")
	protected Timestamp createdAt;
	@Column(name = "updated_at")
	protected Timestamp updatedAt;

}
