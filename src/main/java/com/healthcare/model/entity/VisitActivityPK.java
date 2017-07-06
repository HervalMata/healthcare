package com.healthcare.model.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Created by pazfernando on 4/30/17.
 */
@Embeddable
public @Data class VisitActivityPK implements Serializable {

	private static final long serialVersionUID = -4912913092398276257L;
//	@ManyToOne
	private Long visitId;
//	@ManyToOne
	private Long activityId;

	public VisitActivityPK() {
		super();
	}

//	public VisitActivityPK(Long visit, Long activity) {
//		super();
//		this.visit = new Visit();
//		this.visit.setId(visit);
//		this.activity = new Activity();
//		this.activity.setId(activity);
//	}
//	
//	public VisitActivityPK(Visit visit, Activity activity) {
//		super();
//		this.visit = visit;
//		this.activity = activity;
//	}
	
	public VisitActivityPK(Long visit, Long activity) {
		super();
		this.visitId = visit;
		this.activityId = activity;
	}
	
//	@Override
//    public int hashCode() {
//        return Objects.hash(visit.getId(), activity.getId());
//    }
	
	@Override
    public int hashCode() {
        return Objects.hash(visitId, activityId);
    }

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result
//				+ ((visit == null) ? 0 : visit.hashCode());
//		result = prime * result + ((activity == null) ? 0 : activity.hashCode());;
//		return result;
//	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VisitActivityPK other = (VisitActivityPK) obj;
		if (visitId == null) {
			if (other.visitId != null)
				return false;
		} else if (!visitId.equals(other.visitId))
			return false;
		if (activityId == null) {
			if (other.activityId != null)
				return false;
		} else if (!activityId.equals(other.activityId))
			return false;
		return true;
	}
	
//	@Override
//    public boolean equals(Object o) {
//		if(o == null)
//	        return false;
//
//	    if(!(o instanceof VisitActivityPK))
//	        return false;
//
//	    VisitActivityPK other = (VisitActivityPK) o;
//	    if(!(getVisit().getId().equals(other.getVisit().getId())))
//	        return false;
//
//	    if(!(getActivity().getId().equals(other.getActivity().getId())))
//	        return false;
//
//	    return true;
//}
//        if (o == this) return true;
//        if (!(o instanceof VisitActivityPK)) {
//            return false;
//        }
//        VisitActivityPK other = (VisitActivityPK) o;
//        return Objects.equals(visit, other.visit) && Objects.equals(activity, other.activity);
//    }

}
