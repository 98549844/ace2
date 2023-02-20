package com.dao;

import com.models.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RolesDao extends JpaRepository<Roles, Long>, JpaSpecificationExecutor<Roles> {


    Roles findByRoleCode(String roleCode);

    Roles findRolesByRoleId(Long roleId);

    List<Roles> findRolesByRoleIdIn(List<Long> rolesList);


}
