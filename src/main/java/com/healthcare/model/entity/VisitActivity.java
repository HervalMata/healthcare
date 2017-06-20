package com.healthcare.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

/**
 * Created by pazfernando on 4/30/17.
 */
@Entity
@Table(name = "visit_has_activity", schema = "health_care_v1_dev", catalog = "")
@IdClass(VisitActivityPK.class)
public @Data class VisitActivity implements Serializable {

	private static final long serialVersionUID = -4384670103126314525L;
//	@EmbeddedId
	VisitActivityPK id;
	@Id
//	@MapsId
	@ManyToOne
	@JoinColumn(name = "visit_id", referencedColumnName = "id")
	private Visit visit;
	@Id
//	@MapsId
	@ManyToOne
	@JoinColumn(name = "activity_id", referencedColumnName = "id")
	private Activity activity;
	private String table;
	private String seat;
	@Column(name = "start_time")
	private Timestamp startTime;
	@Column(name = "end_time")
	private Timestamp endTime;
	
	@Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof VisitActivity)) {
            return false;
        }
        VisitActivity other = (VisitActivity) o;
        return Objects.equals(visit.getId(), other.visit.getId()) && Objects.equals(activity.getId(), other.activity.getId());
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(visit, activity);
    }
}
