package com.healthcare.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.healthcare.model.entity.ServicePlan;

public class ServicePlanRepositoryImpl implements ServicePlanRepositoryCustom{
	@PersistenceContext
    private EntityManager em;

	@Override
	public List<Date> getServiceCalendar(ServicePlan servicePlan) {
		StringBuilder sql = new StringBuilder("");
		StringBuilder daySql = new StringBuilder("");
		String[] days = servicePlan.getDays().split(",");
		
		for(int i = 0; i < days.length; i++){
 			if (i == 0)
 				daySql.append(" and (");
			daySql.append("dayname(selected_date) = '").append(days[i].toUpperCase()).append("'");
			if (i < days.length - 1)
				daySql.append(" or ");
			if (i == days.length - 1)
				daySql.append(") ");
		}
		
		sql.append("select * from ");
		sql.append("(select adddate('2016-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from ");
		sql.append("(select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, ");
		sql.append("(select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, ");
		sql.append("(select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, ");
		sql.append("(select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, ");
		sql.append("(select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v ");
		sql.append("where selected_date between '").append(servicePlan.getPlanStart()).append("' and '").append(servicePlan.getPlanEnd()).append("' "); 		
		sql.append(daySql.toString());
		sql.append("order by selected_date");
		return em.createNativeQuery(sql.toString()).getResultList();
	}

}
