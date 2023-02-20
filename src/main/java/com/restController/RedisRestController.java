/*
package com.restController;

import com.google.gson.Gson;
import com.models.common.AjaxResponse;
import com.models.entity.Users;
import com.service.RedisService;
import com.service.UsersService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @Classname: RedisController
 * @Date: 6/5/2021 12:17 上午
 * @Author: garlam
 * @Description:
 *//*


@RestController
@RequestMapping("/rest/redis")
@Api(tags = "redis")
public class RedisRestController {
    private final static Logger log = LogManager.getLogger(RedisRestController.class.getName());


    private final RedisService redisService;
    private final UsersService usersService;

    @Autowired
    public RedisRestController(RedisService redisService, UsersService usersService) {
        this.redisService = redisService;
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public String test() {
        redisService.set("ace", "<<< ace >>>");
        redisService.set("version", "版本 2.5", 10);
        System.out.println(redisService.get("ace"));
        System.out.println(redisService.get("version"));

        return redisService.get("ace") + ":" + redisService.get("version");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsers")
    public AjaxResponse getUsers() {
        List<Users> users = usersService.findAll();
        Users user = usersService.findByUserAccount("garlam");

        redisService.set("all", new Gson().toJson(users));
        redisService.set(user.getUsername(), new Gson().toJson(user));

        List<Object> ls = new ArrayList<>();
        ls.add(redisService.get("all"));
        ls.add(redisService.get(user.getUsername()));

        return AjaxResponse.success(ls);
    }

}

*/
