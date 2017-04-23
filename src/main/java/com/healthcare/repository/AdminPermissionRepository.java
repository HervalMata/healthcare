package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.healthcare.model.entity.AdminPermission;

public interface AdminPermissionRepository extends Repository<AdminPermission, Long> {

	AdminPermission findByLevel(long level);

	List<AdminPermission> findByStatus(long status);

	@Query(value = "select * from admin_permission a_p where a_p.id <= ?1 and a_p.status = ?2", nativeQuery = true)
	List<AdminPermission> findByIdAndStatus(@Param("id") long id, @Param("status") long status);

}
