package com.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Model class that contains data from review
 */
@EqualsAndHashCode
public @Data class ReviewForm implements Serializable {

    private static final long serialVersionUID = -3984784699148833095L;

    @JsonProperty("body_status")
    private BodyStatus bodyStatus;

}
