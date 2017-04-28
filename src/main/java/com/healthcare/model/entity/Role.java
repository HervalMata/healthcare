package com.healthcare.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public @Data class Role implements java.io.Serializable {
	
	private static final long serialVersionUID = -6360665934926249915L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long level;
	private String level_name;
	private long status;

	// @OneToMany(cascade = { CascadeType.PERSIST,
	// CascadeType.REMOVE }, fetch = FetchType.EAGER, mappedBy =
	// "role")
	// private List<Admin> admins;

}