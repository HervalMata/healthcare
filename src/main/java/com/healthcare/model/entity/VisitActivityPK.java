package com.healthcare.model.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Data;

/**
 * Created by pazfernando on 4/30/17.
 */
//@Embeddable
public @Data class VisitActivityPK implements Serializable {

	private static final long serialVersionUID = -4912913092398276257L;
	private Long visit;
	private Long activity;

	public VisitActivityPK() {
		super();
	}

	public VisitActivityPK(Long visit, Long activity) {
		super();
		this.visit = visit;
		this.activity = activity;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(visit, activity);
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
	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		VisitActivityPK other = (VisitActivityPK) obj;
//		if (visit == null) {
//			if (other.visit != null)
//				return false;
//		} else if (!visit.equals(other.visit))
//			return false;
//		if (activity == null) {
//			if (other.activity != null)
//				return false;
//		} else if (!activity.equals(other.activity))
//			return false;
//		return true;
//	}
	
	@Override
    public boolean equals(Object o) {
		if(o == null)
	        return false;

	    if(!(o instanceof VisitActivityPK))
	        return false;

	    VisitActivityPK other = (VisitActivityPK) o;
	    if(!(getVisit().equals(other.getVisit())))
	        return false;

	    if(!(getActivity().equals(other.getActivity())))
	        return false;

	    return true;
	    
//        if (o == this) return true;
//        if (!(o instanceof VisitActivityPK)) {
//            return false;
//        }
//        VisitActivityPK other = (VisitActivityPK) o;
//        return Objects.equals(visit, other.visit) && Objects.equals(activity, other.activity);
    }

}
