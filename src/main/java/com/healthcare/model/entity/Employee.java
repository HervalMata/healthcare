package com.healthcare.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "employee")
@EqualsAndHashCode(callSuper = true)
public @Data class Employee extends Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 512962093355769597L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String gender;
	@Column(name = "social_security_number")
	private String socialSecurityNumber;
	@Column(name = "date_of_birth")
	private Timestamp dateOfBirth;
	@Column(name = "physical_exam")
	private String physicalExam;
	@Column(name = "certificate_name")
	private String certificateName;
	@Column(name = "certificate_start")
	private Timestamp certificateStart;
	@Column(name = "certificate_end")
	private Timestamp certificateEnd;
	@Column(name = "work_start")
	private Timestamp workStart;
	@Column(name = "work_end")
	private Timestamp workEnd;
	private String position;
	private String manager;
	private String type;
	private String status;
	@Column(name = "background_check")
	private String backgroundCheck;
	@ManyToOne
	@JoinColumn(name = "agency_id")
	private Agency agency;
	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhysicalExam() {
		return physicalExam;
	}

	public void setPhysicalExam(String physicalExam) {
		this.physicalExam = physicalExam;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public Timestamp getCertificateStart() {
		return certificateStart;
	}

	public void setCertificateStart(Timestamp certificateStart) {
		this.certificateStart = certificateStart;
	}

	public Timestamp getCertificateEnd() {
		return certificateEnd;
	}

	public void setCertificateEnd(Timestamp certificateEnd) {
		this.certificateEnd = certificateEnd;
	}

	public Timestamp getWorkStart() {
		return workStart;
	}

	public void setWorkStart(Timestamp workStart) {
		this.workStart = workStart;
	}

	public Timestamp getWorkEnd() {
		return workEnd;
	}

	public void setWorkEnd(Timestamp workEnd) {
		this.workEnd = workEnd;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBackgroundCheck() {
		return backgroundCheck;
	}

	public void setBackgroundCheck(String backgroundCheck) {
		this.backgroundCheck = backgroundCheck;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}
}
