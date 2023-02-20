package com.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.controller.common.CommonController;
import com.exception.PasswordNotMatchException;
import com.exception.UserNotFoundException;
import com.models.entity.Users;
import com.service.LoginService;
import com.service.UsersService;
import com.util.DateTimeUtil;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

/**
 * @Classname: LoginController
 * @Date: 1/7/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
//@RequestMapping("/ace")
public class LoginController extends CommonController {
    private static final Logger log = LogManager.getLogger(LoginController.class.getName());

    private final UsersService usersService;
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService, UsersService usersService) {
        this.loginService = loginService;
        this.usersService = usersService;
    }


    @RequestMapping(value = {"/ace/login.html", "/"}, method = RequestMethod.GET)
    public ModelAndView login() {
        if (isLogin()) {
            return super.page("ace/index.html");
        } else {
            return super.page("ace/login.html");
        }
    }

    @RequestMapping(value = "/ace/logging.html", method = RequestMethod.POST)
    public ModelAndView logging(String userAccount, String password, String rememberMe, String deviceType) {
        log.info("userAccount: " + userAccount);
        log.info("password: " + password);
        log.info("rememberMe: {}", rememberMe);

        ModelAndView modelAndView;
        String msg;
        Users user = new Users();
        if (userAccount.isEmpty() || password.isEmpty()) {
            //check input param
            msg = "Account/Password empty";
            modelAndView = loginService.loginError(msg);
            return modelAndView;
        } else if (NullUtil.isNotNull(userAccount) && NullUtil.isNotNull(password)) {
            if (isLogin()) {
                log.info("Logged into Ace");
                return super.page("ace/index.html");
            }

            user.setUserAccount(userAccount);
            user.setPassword(password);
            try {
                //get user information
                user = usersService.findByUserAccount(user);
                long userId = user.getUserId();
                long expired = DateTimeUtil.differenceMinutesByLocalDateTime(LocalDateTime.now(), user.getExpireDate());
                if (!user.isEnabled()) {
                    msg = "Account disabled";
                    modelAndView = loginService.loginError(msg);
                    return modelAndView;
                } else if (expired < 0L) {
                    msg = "Account expired";
                    modelAndView = loginService.loginError(msg);
                    return modelAndView;
                }
                user.setLoginDateTime(LocalDateTime.now());
                user.setIp(getRequest().getRemoteAddr());
                user.setHostName(getRequest().getRemoteHost());

                //rememberMe = on 记住我
                login(userId, deviceType, NullUtil.isNotNull(rememberMe));

                log.info("UserId: {}", userId);
                setUsersSession(user);
                usersService.save(user);
                log.info("login device: {}", StpUtil.getLoginDevice());
            } catch (UserNotFoundException | PasswordNotMatchException e) {
                e.printStackTrace();
                msg = "Account/Password incorrect";
                modelAndView = loginService.loginError(msg);
                modelAndView.addObject("userAccount", userAccount);
                return modelAndView;
            }
        }
        //  modelAndView = super.page("ace/index.html");
        modelAndView = super.redirect("ace/index.html");
        return modelAndView;
    }


}

