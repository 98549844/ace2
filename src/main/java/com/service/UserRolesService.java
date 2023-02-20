package com.service;


import com.dao.UserRolesDao;
import com.models.entity.UserRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesService {
    private static final Logger log = LogManager.getLogger(UserRolesService.class.getName());

    private final UserRolesDao userRolesDao;

    @Autowired
    public UserRolesService(UserRolesDao userRolesDao) {
        this.userRolesDao = userRolesDao;
    }


    public UserRoles saveAndFlush(UserRoles userRoles) {
        return userRolesDao.saveAndFlush(userRoles);
    }

    public UserRoles save(UserRoles userRoles) {
        return userRolesDao.save(userRoles);
    }

    public List<UserRoles> saveAll(List<UserRoles> userRolesList) {
        return userRolesDao.saveAll(userRolesList);
    }

    public void deleteAll() {
        userRolesDao.deleteAll();
    }

    public void deleteAll(List<UserRoles> userRoles) {
        userRolesDao.deleteAll(userRoles);
    }

    public void deletes(List userRoles) {
        userRolesDao.deleteAll(userRoles);
    }

    public void delete(UserRoles userRoles) {
        userRolesDao.delete(userRoles);
    }

    public List<UserRoles> findAllByUserId(Long userId) {
        return userRolesDao.findAllByUserId(userId);
    }

    public List<UserRoles> findAllByUserIdIn(List<Long> userId) {
        return userRolesDao.findAllByUserIdIn(userId);
    }

    public List<UserRoles> findAllByUserRolesIdNotIn(List<Long> userRoles) {
        return userRolesDao.findAllByUserRolesIdNotIn(userRoles);
    }

    public void deleteUserRolesByUserId(Long userId) {
        userRolesDao.deleteUserRolesByUserId(userId);
    }

}
