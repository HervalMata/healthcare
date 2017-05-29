package com.healthcare.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "visit")
public @Data class Visit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5449963759010972006L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "agency_id")
	private Agency agency;
	@Column(name = "check_in_time")
	private Timestamp checkInTime;
	@ManyToOne
	@JoinColumn(name = "selected_meal_id")
	private Meal selectedMeal;
	@ManyToOne
	@JoinColumn(name = "selected_activity_id")
	private Activity selectedActivity;
	@Column(name = "selected_seat")
	private String selectedSeat;
	@Column(name = "user_signature")
	private String userSignature;
	@Column(name = "user_barcode_id")
	private String userBarcodeId;
	@Column(name = "check_out_time")
	private Timestamp checkOutTime;
	@Column(name = "user_comments")
	private String userComments;
	private String notes;
	private Integer status;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
