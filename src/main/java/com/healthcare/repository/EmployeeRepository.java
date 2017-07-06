package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthcare.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findById(long l);

	@Query(value = " SELECT emp from Employee emp left join emp.agency agnc left join agnc.company cmp where cmp.id = :companyId and (:agencyId is null or agnc.id = :agencyId) ")
	List<Employee> findByCompany(@Param("companyId") long companyId, @Param("agencyId") long agencyId);

}