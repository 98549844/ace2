package com.restController;

import com.models.common.AjaxResponse;
import com.models.entity.*;
import com.service.*;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.controller.common.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.util.MapUtil;
import com.util.NullUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @Classname: UserRolePermissionRestController
 * @Date: 12/12/2021 6:32 AM
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/userRolePermission")
@Api(tags = "userRolePermission")
public class UserRolePermissionRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(UserRolePermissionRestController.class.getName());

    private final UsersService usersService;
    private final RolesService rolesService;
    private final PermissionsService permissionsService;
    private final UserRolesService userRolesService;
    private final RolePermissionsService rolePermissionsService;
    private final RolesRestController rolesRestController;
    private final PermissionRestController permissionRestController;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserRolePermissionRestController(PasswordEncoder passwordEncoder, RolePermissionsService rolePermissionsService, UserRolesService userRolesService, UsersService usersService, RolesService rolesService, PermissionsService permissionsService, RolesRestController rolesRestController, PermissionRestController permissionRestController) {
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.permissionsService = permissionsService;
        this.userRolesService = userRolesService;
        this.rolePermissionsService = rolePermissionsService;
        this.rolesRestController = rolesRestController;
        this.permissionRestController = permissionRestController;
        this.passwordEncoder = passwordEncoder;
    }

    public void defaultUser() {
        Users admin = usersService.findByUserAccount("admin");
        Users garlam = usersService.findByUserAccount("garlam");
        if (NullUtil.isNull(admin) || NullUtil.isNull(garlam)) {
            log.warn("administrator/garlam was DESTROYED");
            addDefaultAdminUsers();
            log.info("administrator/garlam rebuild success !!!");
            return;
        }
        log.info("default ADMIN account health check: PASS");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addDefaultUsers")
    public AjaxResponse addDefaultAdminUsers() {
        if (0 == rolesService.findAll().size() || 0 == permissionsService.findAll().size()) {
            log.warn("roles or permission is empty, rebuild default roles and permission ...");
            mapRolesAndPermissions();
            log.info("rebuild roles and permission complete !");
        }

        //related default user into roles
        Roles adminRoles = rolesService.findByRoleCode(Roles.ADMIN);

        //create default user
        Users admin = usersService.findByUserAccount("admin");
        Users garlam = usersService.findByUserAccount("garlam");

        if (NullUtil.isNull(admin)) {
            admin = new Users();
            admin.setPassword(passwordEncoder.encode("909394"));//password 909394
            admin.setUserAccount("admin");
            admin.setUsername("administrator");
            admin.setDescription(Users.ADMINISTRATOR);
            admin.setEmail("admin@ace.com");
            admin.setMobile("0000 0000");
            admin.setGender(null);
            admin.setDateOfBirth(LocalDateTime.now());
            admin.setLoginDateTime(LocalDateTime.now());
            admin.setStatus(Users.ACTIVE);
            admin.setDomain("ace.com");
            admin.setIp("127.0.0.1");
            admin.setDomain("ace.com");
            admin.setRemark("ACE APPLICATION");
            admin.setEnabled(true);
            admin.setExpireDate(LocalDateTime.now().plusYears(100L));
            admin = usersService.saveAndFlush(admin);

            UserRoles adminUsersRoles = new UserRoles();
            adminUsersRoles.setRoleId(adminRoles.getRoleId());
            adminUsersRoles.setUserId(admin.getUserId());
            userRolesService.save(adminUsersRoles);
        }

        if (NullUtil.isNull(garlam)) {
            garlam = new Users();
            //garlam.setPassword("$2a$11$gNnG0zfbKr8c7M3YX9Frn.HaKPS1hFsmgKPt4F6LXnEmmE0FAhV8C");
            garlam.setPassword(passwordEncoder.encode("909394"));//password 909394
            garlam.setUserAccount("garlam");
            garlam.setUsername("garlam");
            garlam.setDescription(Users.ADMINISTRATOR);
            garlam.setEmail("garlam@ace.com");
            garlam.setMobile("9518 6540");
            garlam.setGender("M");
            garlam.setDateOfBirth(LocalDateTime.now());
            garlam.setLoginDateTime(LocalDateTime.now());
            garlam.setStatus(Users.ACTIVE);
            garlam.setDomain("ace.com");
            garlam.setIp("127.0.0.1");
            garlam.setDomain("ace.com");
            garlam.setRemark("ACE APPLICATION");
            garlam.setEnabled(true);
            garlam.setExpireDate(LocalDateTime.now().plusYears(100L));
            garlam = usersService.saveAndFlush(garlam);

            UserRoles garlamUsersRoles = new UserRoles();
            garlamUsersRoles.setRoleId(adminRoles.getRoleId());
            garlamUsersRoles.setUserId(garlam.getUserId());
            userRolesService.save(garlamUsersRoles);

        }
        return AjaxResponse.success("User: administrator and garlam has been generated into roles");
    }


    /**
     * 关联用户组别和权限级别
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/relateRolesAndPermissions")
    public AjaxResponse mapRolesAndPermissions() {

        int rSize = rolesService.findAll().size();
        int pSize = permissionsService.findAll().size();
        if (0 == rSize && 0 == pSize) {
            log.info("Clean ROLES and PERMISSION DATA ...");
            rolesService.deleteAll();
            permissionsService.deleteAll();
            log.info("Clean ROLES and PERMISSION COMPLETED !");

            log.info("Build ROLES and PERMISSION data ...");
            rolesRestController.insertRoles();
            permissionRestController.insertPermission();
            log.info("Build ROLES and PERMISSION COMPLETE !");
        } else {
            log.info("Roles size: {}", rolesService.findAll().size());
            log.info("Permission size: {}", permissionsService.findAll().size());
            StringBuilder c = new StringBuilder();
            c.append("Rebuild roles permission fail !!! ");
            c.append("Roles size: " + rSize);
            c.append("Permission size: " + pSize);
            return AjaxResponse.success(c.toString());
        }

        //insert default roles
        Roles roleAdmin = rolesService.findByRoleCode(Roles.ADMIN);
        Roles roleUser = rolesService.findByRoleCode(Roles.USER);
        Roles RoleDisable = rolesService.findByRoleCode(Roles.DISABLE);
        Roles roleViewer = rolesService.findByRoleCode(Roles.VIEWER);

        RolePermissions all;
        RolePermissions insert;
        RolePermissions update;
        RolePermissions select;
//        RolePermissions delete ;
        RolePermissions deny;

        Permissions permission;
        for (Roles roles : rolesService.findAll()) {
            if (Users.ACTIVE.equals(roles.getStatus())) {
                switch (roles.getRoleCode()) {
                    case Roles.ADMIN:
                        all = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.ALL);
                        all.setPermissionsId(permission.getPermissionsId());
                        all.setRoleId(roleAdmin.getRoleId());

                        rolePermissionsService.save(all);
                        break;
                    case Roles.DISABLE:
                        deny = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.DENY);
                        deny.setPermissionsId(permission.getPermissionsId());
                        deny.setRoleId(RoleDisable.getRoleId());
                        rolePermissionsService.save(deny);
                        break;
                    case Roles.USER:
                        select = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.SELECT);
                        select.setPermissionsId(permission.getPermissionsId());
                        select.setRoleId(roleUser.getRoleId());

                        update = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.UPDATE);
                        update.setPermissionsId(permission.getPermissionsId());
                        update.setRoleId(roleUser.getRoleId());

                        insert = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.INSERT);
                        insert.setPermissionsId(permission.getPermissionsId());
                        insert.setRoleId(roleUser.getRoleId());

                        rolePermissionsService.save(select);
                        rolePermissionsService.save(update);
                        rolePermissionsService.save(insert);
                        break;
                    case Roles.VIEWER:
                        select = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.SELECT);
                        select.setPermissionsId(permission.getPermissionsId());
                        select.setRoleId(roleViewer.getRoleId());

                        rolePermissionsService.save(select);
                        break;
                }
            }

        }
        return AjaxResponse.success("Roles Permission merged");
    }


    /**
     * 整理没有用户组别的现有用户
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/remapUsersRolesPermissionRelation")
    public AjaxResponse remapUsersRolesPermissionRelation() {
        //find default users
        List<String> account = new ArrayList<>();
        account.add("admin");
        account.add("garlam");
        List<Users> users = usersService.findByUserAccountNotIn(account);

        //find default user roles
        Users admin = usersService.findByUserAccount("admin");
        Users garlam = usersService.findByUserAccount("garlam");
        List<UserRoles> adminRoles = userRolesService.findAllByUserId(admin.getUserId());
        List<UserRoles> garlamRoles = userRolesService.findAllByUserId(garlam.getUserId());

        List<Long> defaultUserRolesIds = new ArrayList<>();
        defaultUserRolesIds.add(adminRoles.get(0).getUserRolesId());
        defaultUserRolesIds.add(garlamRoles.get(0).getUserRolesId());

        //find user roles list without default users
        List<UserRoles> userRolesWithoutDefaultUsers = userRolesService.findAllByUserRolesIdNotIn(defaultUserRolesIds);

        // delete user roles relation without default users
        userRolesService.deletes(userRolesWithoutDefaultUsers);

        Roles roleAdmin = rolesService.findByRoleCode(Roles.ADMIN);
        Roles roleUser = rolesService.findByRoleCode(Roles.USER);
        Roles RoleDisable = rolesService.findByRoleCode(Roles.DISABLE);
        Roles roleViewer = rolesService.findByRoleCode(Roles.VIEWER);

        List<UserRoles> userRolesList = new ArrayList<>();
        int userSize = users.size();
        //用户加入角色
        for (int i = 0; i < userSize; i++) {
            UserRoles userRoles = new UserRoles();
            switch (users.get(i).getDescription()) {
                case Users.ADMINISTRATOR:
                    userRoles.setUserId(users.get(i).getUserId());
                    userRoles.setRoleId(roleAdmin.getRoleId());
                    break;
                case Users.DISABLE:
                    userRoles.setUserId(users.get(i).getUserId());
                    userRoles.setRoleId(RoleDisable.getRoleId());
                    break;
                case Users.USER:
                    userRoles.setUserId(users.get(i).getUserId());
                    userRoles.setRoleId(roleUser.getRoleId());
                    break;
                case Users.VIEWER:
                    userRoles.setUserId(users.get(i).getUserId());
                    userRoles.setRoleId(roleViewer.getRoleId());
                    break;
            }
            userRolesList.add(userRoles);
        }
        userRolesService.saveAll(userRolesList);

        rolePermissionsService.deleteAll(); // delete all roles and permission relation

        List<Roles> rolesList = rolesService.findAll();
        int rolesSize = rolesList.size();
        Permissions p0 = permissionsService.findPermissionsByPermissionCode(Permissions.ALL);
        Permissions p1 = permissionsService.findPermissionsByPermissionCode(Permissions.INSERT);
        Permissions p2 = permissionsService.findPermissionsByPermissionCode(Permissions.UPDATE);
        Permissions p3 = permissionsService.findPermissionsByPermissionCode(Permissions.DELETE);
        Permissions p4 = permissionsService.findPermissionsByPermissionCode(Permissions.SELECT);
        Permissions p10 = permissionsService.findPermissionsByPermissionCode(Permissions.DENY);

        for (int i = 0; i < rolesSize; i++) {
            RolePermissions all;
            RolePermissions insert;
            RolePermissions update;
            RolePermissions delete;
            RolePermissions select;
            RolePermissions deny;

            switch (rolesList.get(i).getRoleCode()) {
                case Roles.ADMIN:
                    all = new RolePermissions();
                    all.setRoleId(rolesList.get(i).getRoleId());
                    all.setPermissionsId(p0.getPermissionsId());

                    rolePermissionsService.save(all);
                    break;
                case Roles.DISABLE:
                    deny = new RolePermissions();
                    deny.setRoleId(rolesList.get(i).getRoleId());
                    deny.setPermissionsId(p10.getPermissionsId());

                    rolePermissionsService.save(deny);
                    break;
                case Roles.USER:
                    insert = new RolePermissions();
                    insert.setRoleId(rolesList.get(i).getRoleId());
                    insert.setPermissionsId(p1.getPermissionsId());

                    update = new RolePermissions();
                    update.setRoleId(rolesList.get(i).getRoleId());
                    update.setPermissionsId(p2.getPermissionsId());

                    select = new RolePermissions();
                    select.setRoleId(rolesList.get(i).getRoleId());
                    select.setPermissionsId(p4.getPermissionsId());

                    rolePermissionsService.save(insert);
                    rolePermissionsService.save(update);
                    rolePermissionsService.save(select);
                    break;
                case Roles.VIEWER:
                    select = new RolePermissions();
                    select.setRoleId(rolesList.get(i).getRoleId());
                    select.setPermissionsId(p4.getPermissionsId());

                    rolePermissionsService.save(select);
                    break;
            }
        }
        return AjaxResponse.success("User Roles Permission merged");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/findUserRolePermission")
    public AjaxResponse findUserRolePermission() {
        log.info("List detail by USERS");
        List<Map> list = usersService.findUserRolePermission();
        MapUtil mapUtil = new MapUtil();
        for (Map map : list) {
            System.out.println("--------------");
            mapUtil.iterateMapKeySet(map);
        }
        return AjaxResponse.success(list);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findUserRolePermissionDetail")
    public AjaxResponse findUserRolePermissionDetail() {
        log.info("List detail by UserRolePermission");
        List<Map> list = usersService.findUserRolePermissionDetail();
        MapUtil mapUtil = new MapUtil();
        for (Map map : list) {
            System.out.println("--------------");
            mapUtil.iterateMapKeySet(map);
        }
        return AjaxResponse.success(list);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserRolePermissionById")
    public AjaxResponse getUserRolePermissionById() {
        log.info("List detail by UserRolePermission Mybatis");
        List<Map> m0 = usersService.findAllUserRolePermissionByMybatis();
        List<Map> getUsersByMybatis = usersService.getUserRolePermissionById(533l);
        List<Map> getUsersByHibernate = usersService.findUserRolePermissionDetailById(533l);

        Long userId = (Long) getUsersByMybatis.get(0).get("userId");
        String userAccount = (String) getUsersByMybatis.get(0).get("userAccount");
        String roleName = (String) getUsersByMybatis.get(0).get("roleName");
        String permissionCode = (String) getUsersByMybatis.get(0).get("permissionCode");
        Long permissionsId = (Long) getUsersByMybatis.get(0).get("permissionsId");
        log.info("userId: {}", userId);
        log.info("userAccount: {}", userAccount);
        log.info("roleName: {}", roleName);
        log.info("permissionCode: {}", permissionCode);
        log.info("permissionsId: {}", permissionsId);

        List<List<Map>> result = new ArrayList<>();
        result.add(getUsersByMybatis);
        result.add(getUsersByHibernate);

        return AjaxResponse.success(result);
    }

}

