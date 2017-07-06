package com.healthcare.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.io.Serializable;

@Entity
@Table(name = "admin")
@EqualsAndHashCode(callSuper = true)
public @Data class Admin extends Audit implements Serializable {

	private static final long serialVersionUID = 1425662189663784653L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String gender;
	private String email;
	private String phone;
	private String ip;
	private long status;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "secondary_phone")
	private String secondaryPhone;
	@Column(name = "profile_photo")
	private String profilePhoto;
	@Column(name = "device_address")
	private String deviceAddress;
	@Column(name = "remember_token")
	private String rememberToken;

	public String getPassword() {
		return password;
	}

	public Long getId() {
		return id;
	}
}
