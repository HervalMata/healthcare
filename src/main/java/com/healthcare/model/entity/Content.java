package com.healthcare.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "content")
@EqualsAndHashCode(callSuper = true)
public @Data class Content extends Audit implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 7211707812835215028L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Content parent;
	private String title;
	@Lob
	private byte[] content;
	@Column(name = "page_title")
	private String pageTitle;
	@Column(name = "page_keyword")
	private String pageKeyword;
	@Column(name = "page_description")
	private String page_description;
	@Column(name = "accessKey")
	private String accessKey;
	private Integer status;
	@Column(name = "base_id")
	private Long baseId;
}
