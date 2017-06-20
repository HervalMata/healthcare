package com.healthcare.api.model;

public class VisitRequest {

	private Long id;
	private String userBarcodeId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserBarcodeId() {
		return userBarcodeId;
	}
	public void setUserBarcodeId(String userBarcodeId) {
		this.userBarcodeId = userBarcodeId;
	}
	
	
}
