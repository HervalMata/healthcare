package com.healthcare.dto;

import java.util.Date;

import com.healthcare.model.entity.HomeVisit;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HomeVisitDto {

	private HomeVisit homeVisit;
	private Date scheduledDate;

}
