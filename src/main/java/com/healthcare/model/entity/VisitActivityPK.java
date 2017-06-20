package com.healthcare.model.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by pazfernando on 4/30/17.
 */
public @Data class VisitActivityPK implements Serializable {

	private static final long serialVersionUID = -4912913092398276257L;
	private Visit visit;
	private Activity activity;
}
