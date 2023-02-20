package com.restController;

import com.controller.common.CommonController;
import com.models.common.AjaxResponse;
import com.models.entity.UserRoles;
import com.models.entity.Users;
import com.generator.InsertUsers;
import com.service.UserRolesService;
import com.service.UsersService;
import com.util.RandomUtil;
import com.util.TypeUtil;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: UserRestController
 * @Date: 5/7/2021 10:49 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/users")
@Api(tags = "users")
public class UsersRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(UsersRestController.class.getName());

    private final UsersService usersService;
    private final UserRolesService userRolesService;
    private final PasswordEncoder passwordEncoder;
    private final UserRolePermissionRestController userRolePermissionRestController;

    @Autowired
    public UsersRestController(UserRolesService userRolesService, UserRolePermissionRestController userRolePermissionRestController, UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.userRolesService = userRolesService;
        this.passwordEncoder = passwordEncoder;
        this.userRolePermissionRestController = userRolePermissionRestController;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getUsers")
    public AjaxResponse getUsers() {
        List<Users> ls = usersService.findAll();
        List<String> result = new ArrayList<>();
        for (Users user : ls) {
            String u = user.getUsername() + "   [" + user.getPassword() + "]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsersByMybatis")
    public AjaxResponse getUsersByMybatis() {
        List<Users> ls = usersService.findAllByMybatis();
        List<String> result = new ArrayList<>();
        for (Users user : ls) {
            String u = user.getUsername() + "   [" + user.getPassword() + "]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsersLikeNameByMybatis/{username}")
    public AjaxResponse getUsersLikeNameByMybatis(@PathVariable String username) {
        List<Users> ls = usersService.findUsersLikeNameByMybatis(username);
        List<String> result = new ArrayList<>();
        for (Users user : ls) {
            String u = user.getUsername() + "   [" + user.getPassword() + "]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }


    /**
     * select statement 需要用jpa夹住mybatis update, 共用col才会update
     *
     * @param acc
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/updateUserByMybatis/{acc}")
    public AjaxResponse updateUserByMybatis(@PathVariable String acc) {

        Users users = usersService.findUserByMybatis(acc);
        log.info("before version: " + users.getVersion());

        users.setIp(getRequest().getRemoteAddr());
        users.setHostName(getRequest().getRemoteHost());
        users.setMobile(TypeUtil.integerToString(RandomUtil.getRangeInt(0, 99999999)));
        usersService.updateByMybatis(users);

        //users = usersService.findByUserAccount(acc);
        users = usersService.findUserByMybatis(acc);

        log.info("after version: " + users.getVersion());
        log.info("COMPLETE !!!");

        return AjaxResponse.success(users);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/deleteAllUser")
    public AjaxResponse deleteAllUser() {
        usersService.deleteAll();
        userRolesService.deleteAll();
        return AjaxResponse.success("All users and roles relation deleted");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/deleteUsersWithoutDefaultUsers")
    public AjaxResponse deleteUsersWithoutDefaultUsers() {
        List<String> defaultUserAccounts = new ArrayList<>();
        defaultUserAccounts.add("admin");
        defaultUserAccounts.add("garlam");

        List<Users> users = usersService.findByUserAccountNotIn(defaultUserAccounts);

        List<Long> deleteUsers = new ArrayList<>();
        for (Users user : users) {
            deleteUsers.add(user.getUserId());
        }
        List<UserRoles> userRoles = userRolesService.findAllByUserIdIn(deleteUsers);

        usersService.deleteAll(users);
        userRolesService.deleteAll(userRoles);


        return AjaxResponse.success("All users and roles relation deleted, but not include default user account");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/insertSampleUser")
    public AjaxResponse insertSampleUser(boolean remap) {
        //generate users data
        //default password = 909394
        String password = passwordEncoder.encode("909394");
        log.info("passwordEncoder matches=>" + passwordEncoder.matches("909394", password));
        List<Users> usersList = InsertUsers.insertUsers();
        usersService.saveAll(usersList);


        if (remap) {
            userRolePermissionRestController.remapUsersRolesPermissionRelation();
        }
        List<String> result = new ArrayList<>();
        for (Users user : usersList) {
            String u = user.getUsername() + "   [ 909394 ]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/reEncodePassword")
    public AjaxResponse reEncodePassword(@RequestParam("password") String password) {
        //get all user
        log.info("password: {}", password);
        List<Users> ls = usersService.findAll();

        List<Users> usersWithReEncodePassword = new ArrayList<>();
        for (Users users : ls) {
            String encode = passwordEncoder.encode(password);
            users.setPassword(encode);
            usersWithReEncodePassword.add(users);
        }
        usersService.saveAll(usersWithReEncodePassword);
        return AjaxResponse.success("All password re-encoded");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/validatePassword/{password}")
    public AjaxResponse validatePassword(@PathVariable String password) {
        log.info("password: {}", password);
        //get all user
        List<Users> ls = usersService.findAll();
        List<String> result = new ArrayList<>();
        for (Users users : ls) {
            boolean match = passwordEncoder.matches(password, users.getPassword());
            log.info("encode: {}", match);
            result.add(users.getUsername() + "[ " + match + " ]");
        }
        return AjaxResponse.success(result);
    }


}

