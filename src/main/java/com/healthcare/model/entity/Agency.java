package com.healthcare.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "agency")
@EqualsAndHashCode(callSuper = true)
public
@Data
class Agency extends Audit implements Serializable {

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
    @Column(name = "name")
    private String name;
    @Column(name = "tracking_mode")
    private int trackingMode;
    @Column(name = "contact_person")
    private String contactPerson;
    @Column(name = "email")
    private String email;
    @Column(name = "addressOne")
    private String addressOne;
    @Column(name = "addressTwo")
    private String addressTwo;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "time_zone")
    private String timezone;
    @Column(name = "phone")
    private String phone;
    @Column(name = "holiday")
    private String holiday;
    @Column(name = "fax")
    private String fax;
    @ManyToOne
    @JoinColumn(name = "company_id1")
    private Company company1;
    @ManyToOne
    @JoinColumn(name = "agency_type_id")
    private AgencyType agencyType;

}
