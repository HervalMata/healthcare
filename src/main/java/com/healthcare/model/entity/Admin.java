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

@Entity
@Table(name = "admin")
public @Data class Admin extends Audit implements Serializable {

	private static final long serialVersionUID = 1425662189663784653L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private String username;
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

}
