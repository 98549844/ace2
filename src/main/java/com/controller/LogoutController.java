package com.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.constant.Css;
import com.models.entity.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.controller.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @Classname: LogoutController
 * @Date: 11/12/2021 6:52 AM
 * @Author: garlam
 * @Description:
 */

@Controller
public class LogoutController extends CommonController {
    private static final Logger log = LogManager.getLogger(LogoutController.class.getName());


    @RequestMapping(value = {"/logout.html", "/ace/logout.html", "/ace/**/logout.html"}, method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView modelAndView = super.page("ace/login");
        String msg = "Logout success";
        modelAndView.addObject("msg", msg);
        modelAndView.addObject(Css.css, Css.green);
        Users user = getCurrentUser();

        // logout();
        logout(user.getUserId(), getDevice());
        clearSession();
        return modelAndView;
    }

}

