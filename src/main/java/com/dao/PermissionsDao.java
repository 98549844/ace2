package com.dao;

import com.models.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionsDao extends JpaRepository<Permissions, Long>, JpaSpecificationExecutor<Permissions> {


	Permissions findPermissionsByPermissionCode(String permissionCode);

	Permissions findPermissionsByAction(String action);
}
