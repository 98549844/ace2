package com.dao;

import com.models.entity.RolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RolePermissionsDao extends JpaRepository<RolePermissions, Long>, JpaSpecificationExecutor<RolePermissions> {

    List<RolePermissions> findRolePermissionsByRoleId(Long roleId);

}
