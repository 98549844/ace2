package com.config;

import cn.dev33.satoken.stp.StpInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.controller.common.CommonController;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义权限验证接口扩展
 *
 * @Classname: SaRightConfig
 * @Date: 16/12/2021 8:55 AM
 * @Author: garlam
 * @Description:
 */


@Component
public class StpInterfaceConfig extends CommonController implements StpInterface {
    private static final Logger log = LogManager.getLogger(StpInterfaceConfig.class.getName());

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.info("getPermissionList: {}", loginId);

        //项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<>();
        list.add((String) loginId);
        list.add("user-add");
        list.add("user-delete");
        list.add("user-update");
        list.add("user-get");
        return list;
    }


    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        //项目中要根据具体业务逻辑来查询角色
        return null;
    }
}

