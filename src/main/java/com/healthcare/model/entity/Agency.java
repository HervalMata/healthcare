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
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "agency")
@EqualsAndHashCode(callSuper = true)
public @Data class Agency extends Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6572833124019691517L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "license_no")
	private String licenseNo;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	private String name;
	@Column(name = "tracking_mode")
	private int trackingMode;
	@Column(name = "contact_person")
	private String contactPerson;
	private String email;
	@Column(name = "address_one")
	private String addressOne;
	@Column(name = "address_two")
	private String addressTwo;
	private String city;
	private String state;
	private String zipcode;
	private String timezone;
	private String phone;
	private String holiday;
	private String fax;
	@JoinColumn(name = "company_id1")
	private Company company1;
	@JoinColumn(name = "agency_type_id")
	private AgencyType agencyType;

}
