package com.healthcare.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "employee")
@EqualsAndHashCode(callSuper = true)
public
@Data
class Employee extends Audit implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 512962093355769597L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
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
    @Column(name = "position")
    private String position;
    @Column(name = "manager")
    private String manager;
    @Column(name = "type")
    private String type;
    @Column(name = "status")
    private String status;
    @Column(name = "background_check")
    private String backgroundCheck;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

}
