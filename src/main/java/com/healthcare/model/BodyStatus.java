package com.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Model class that contains part data of review
 */
@EqualsAndHashCode
public @Data class BodyStatus implements Serializable {

    private static final long serialVersionUID = -5959217049363071540L;

    @JsonProperty("systolic")
    private Integer bloodPressureSystolic;
    @JsonProperty("diastolic")
    private Integer bloodPressureDiastolic;
    private Integer pulse;
    private Integer weight;
    private Integer height;

}
