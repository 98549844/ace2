package com.service;

import com.dao.PermissionsDao;
import com.models.entity.Permissions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PermissionsService {
    private static final Logger log = LogManager.getLogger(PermissionsService.class.getName());

    private final PermissionsDao permissionsDao;

    @Autowired
    public PermissionsService(PermissionsDao permissionsDao) {
        this.permissionsDao = permissionsDao;
    }

    public Permissions findPermissionsByPermissionCode(String permissionCode) {
        return permissionsDao.findPermissionsByPermissionCode(permissionCode);
    }

    public Permissions findPermissionsByAction(String action) {
        return permissionsDao.findPermissionsByAction(action);
    }

    public void deleteAll() {
        permissionsDao.deleteAll();
    }


    public List<Permissions> findAll() {
        return permissionsDao.findAll();
    }

    public void saveAll(List<Permissions> permissions) {
        permissionsDao.saveAll(permissions);
    }
}
