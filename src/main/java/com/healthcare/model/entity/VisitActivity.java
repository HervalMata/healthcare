package com.healthcare.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import lombok.Data;

/**
 * Created by pazfernando on 4/30/17.
 */
@Entity
@Table(name = "visit_has_activity", schema = "health_care_v1_dev", catalog = "")
//@IdClass(VisitActivityPK.class)
//@AssociationOverrides({
//	@AssociationOverride(name = "id.visit",
//		joinColumns = @JoinColumn(name = "VISIT_ID")),
//	@AssociationOverride(name = "id.activity",
//		joinColumns = @JoinColumn(name = "ACTIVITY_ID")) })
public @Data class VisitActivity implements Serializable {

	private static final long serialVersionUID = -4384670103126314525L;
	@EmbeddedId
	VisitActivityPK id;
//	 @Id
//	 @Column(name = "visit_id")
//	private Long visitId;
	 @MapsId("visitId")
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "visit_id", referencedColumnName = "id", insertable=false, updatable=false)
//	@Transient
	private Visit visit;
//	 @Id
//	 @Column(name = "activity_id")
//		private Long activityId;
	 @MapsId("activityId")
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "activity_id", referencedColumnName = "id", insertable=false, updatable=false)
//	@Transient
	private Activity activity;
	private String table;
	private String seat;
	@Column(name = "start_time")
	private Timestamp startTime;
	@Column(name = "end_time")
	private Timestamp endTime;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		VisitActivity that = (VisitActivity) o;
		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return (getId() != null ? getId().hashCode() : 0);
	}
}
