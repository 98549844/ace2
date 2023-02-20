package com.models.info;

import com.models.entity.Roles;
import com.models.entity.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @Classname: UsersInfo
 * @Date: 2022/11/12 下午 07:32
 * @Author: kalam_au
 * @Description:
 */


public class UsersInfo extends Users {
    private static final Logger log = LogManager.getLogger(UsersInfo.class.getName());

    private List<Roles> roles;


    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}

