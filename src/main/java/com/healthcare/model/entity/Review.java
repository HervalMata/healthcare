package com.healthcare.model.entity;

import com.healthcare.model.ReviewForm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "review")
@EqualsAndHashCode(callSuper = true)
public @Data class Review extends Audit implements Serializable {

	private static final long serialVersionUID = 8451585526769991568L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
    @ManyToOne
    @JoinColumn(name = "user_id1")
    private User user1;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

	@JoinColumn(name = "affect_start")
	private Timestamp affectStart;
	@JoinColumn(name = "affect_end")
	private Timestamp affectEnd;

    @Column(name = "form_data")
    @Type(type = "com.healthcare.model.entity.usertype.ReviewFormDataUserType")
    private ReviewForm formData;

	private Integer content;

}
