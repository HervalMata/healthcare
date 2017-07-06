package com.healthcare.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public @Data class User extends Audit implements Serializable {

	private static final long serialVersionUID = 8716797253090002699L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	@Column(name = "user_type")
	private int userType;
	private String username;
	@JsonIgnore
	private String password;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	private String lastName;
	private String gender;
	private String language;
	@Column(name = "social_security_number")
	private String socialSecurityNumber;
	@Column(name = "dob")
	private Date dateOfBirth;
	private String email;
	private String phone;
	@Column(name = "secondary_phone")
	private String secondaryPhone;
	@Column(name = "verification_code")
	private String verificationCode;
	@Column(name = "address_type")
	private String addressType;
	@Column(name = "address_one")
	private String addressOne;
	@Column(name = "address_two")
	private String addressTwo;
	private String city;
	private String state;
	private String zipcode;
	@Column(name = "emergency_contact_first_name")
	private String emergencyContactFirstName;
	@Column(name = "emergency_contact_middle_name")
	private String emergencyContactMiddleName;
	@Column(name = "emergency_contact_last_name")
	private String emergencyContactLastName;
	@Column(name = "relationship_to_paticipant")
	private String relationshipToParticipant;
	@Column(name = "emergency_contact_phone")
	private String emergencyContactPhone;
	@Column(name = "emergency_contact_address_one")
	private String emergencyContactAddressOne;
	@Column(name = "emergency_contact_address_two")
	private String emergencyContactAddressTwo;
	@Column(name = "emergency_contact_city")
	private String emergencyContactCity;
	@Column(name = "emergency_contact_state")
	private String emergencyContactState;
	@Column(name = "emergency_contact_zipcode")
	private String emergencyContactZipcode;
	@Column(name = "comment")
	private String comment;
	@ManyToOne
	@JoinColumn(name = "preferred_meal_id")
	private Meal preferredMeal;
	@ManyToOne
	@JoinColumn(name = "preferred_activity_id")
	private Activity preferredActivity;
	@Column(name = "preferred_seat")
	private String preferredSeat;
	@Column(name = "profile_photo")
	private String profilePhoto;
	@Column(name = "approvable_mail")
	private Integer approvableMail;
	@Column(name = "medicaid_no")
	private String medicaIdNumber;
	@Column(name = "medicare_no")
	private String medicareNumber;
	private String insurance;
	@Column(name = "insurance_start")
	private Timestamp insuranceStart;
	@Column(name = "insurance_end")
	private Timestamp insuranceEnd;
	@Column(name = "insurance_eligiable")
	private String insuranceEligiable;
	@Column(name = "eligiable_start")
	private Timestamp eligiableStart;
	@Column(name = "eligiable_end")
	private Timestamp eligiableEnd;
	@Column(name = "family_doctor")
	private String familyDoctor;
	@Column(name = "family_doctor_address")
	private String familyDoctorAddress;
	@Column(name = "family_doctor_tel")
	private String familyDoctorTel;
	@Column(name = "expert_doctor")
	private String expertDoctor;
	@Column(name = "expert_doctor_address")
	private String expertDoctorAddress;
	@Column(name = "expert_doctor_tel")
	private String expertDoctorTel;
	@Column(name = "medical_condition")
	private String medicalCondition;
	private Integer status;
	@Column(name = "vacation_note")
	private String vacationNote;
	@Column(name = "vacation_start")
	private Timestamp vacationStart;
	@Column(name = "vacation_end")
	private Timestamp vacationEnd;
	@ManyToOne
	@JoinColumn(name = "agency_id")
	private Agency agency;
	@Column(name = "status_second")
	private Integer statusSecond;

}
