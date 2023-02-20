package com.service;


import com.dao.RolesDao;
import com.models.entity.Roles;
import com.models.entity.UserRoles;
import com.models.entity.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolesService {
    private static final Logger log = LogManager.getLogger(RolesService.class.getName());

    private final RolesDao rolesDao;
    private final UserRolesService userRolesService;

    @Autowired
    public RolesService(RolesDao rolesDao, UserRolesService userRolesService) {
        this.rolesDao = rolesDao;
        this.userRolesService = userRolesService;
    }

    public List<Roles> findAll() {
        return rolesDao.findAll();
    }

    public Roles findByRoleCode(String roleCode) {
        return rolesDao.findByRoleCode(roleCode);
    }

    public void deleteAll() {
        rolesDao.deleteAll();
    }

    public void saveAll(List<Roles> roles) {
        rolesDao.saveAll(roles);
    }

    public Roles findRolesByRoleId(Long rolesId) {
        return rolesDao.findRolesByRoleId(rolesId);
    }

    public List<Roles> findRolesByRoleIdIn(List<Long> rolesId) {
        return rolesDao.findRolesByRoleIdIn(rolesId);
    }

    public void saveAllAndFlush(List<Roles> roles) {
        rolesDao.saveAllAndFlush(roles);
    }

    public List<Roles> getRolesByUserId(Long userId) {
        List<UserRoles> userRolesList = userRolesService.findAllByUserId(userId);
        List<Long> roleIdList = new ArrayList<>();
        for (UserRoles userRoles : userRolesList) {
            roleIdList.add(userRoles.getRoleId());
        }
        List<Roles> rolesList = findRolesByRoleIdIn(roleIdList);
        return rolesList;
    }


    public List<Roles> getRolesByUsers(List<Users> users) {
        List<Long> usersId = new ArrayList<>();
        for (Users user : users) {
            usersId.add(user.getUserId());
        }

        List<UserRoles> userRolesList = userRolesService.findAllByUserIdIn(usersId);
        List<Long> roleIdList = new ArrayList<>();
        for (UserRoles userRoles : userRolesList) {
            roleIdList.add(userRoles.getRoleId());
        }
        List<Roles> rolesList = findRolesByRoleIdIn(roleIdList);
        return rolesList;
    }

}
