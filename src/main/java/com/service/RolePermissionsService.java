package com.service;

import com.dao.RolePermissionsDao;
import com.models.entity.RolePermissions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionsService {
    private static final Logger log = LogManager.getLogger(RolePermissionsService.class.getName());

    private final RolePermissionsDao rolePermissionsDao;

    @Autowired
    public RolePermissionsService(RolePermissionsDao rolePermissionsDao) {
        this.rolePermissionsDao = rolePermissionsDao;
    }

    public RolePermissions save(RolePermissions rolePermissions) {
        return rolePermissionsDao.saveAndFlush(rolePermissions);
    }

    public List<RolePermissions> findRolePermissionsByRoleId(Long roleId) {
        return rolePermissionsDao.findRolePermissionsByRoleId(roleId);
    }

    public List<RolePermissions> saveAll(List<RolePermissions> permissionsArrayList) {
        return rolePermissionsDao.saveAll(permissionsArrayList);
    }

    public void deleteAll() {
        rolePermissionsDao.deleteAll();
    }

    public void delete(RolePermissions rolePermissions) {
        rolePermissionsDao.delete(rolePermissions);
    }

}
