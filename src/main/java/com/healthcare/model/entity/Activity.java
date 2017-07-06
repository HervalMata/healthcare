package com.healthcare.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "activity")
@EqualsAndHashCode(callSuper = true)
public @Data class Activity extends Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5046778513270931807L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Integer status;
	@ManyToOne
	@JoinColumn(name = "instructor_employee_id")
	private Employee instructorEmployee;
	@Column(name = "time_start")
	private String timeStart;
	@Column(name = "time_end")
	private String timeEnd;
	private String date;
	private String location;
	private String note;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "activity")
	private Set<VisitActivity> visitActivities;
}
