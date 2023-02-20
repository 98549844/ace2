package com.controller;

import com.constant.Css;
import com.controller.common.CommonController;
import com.models.entity.Users;
import com.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname: LoginController
 * @Date: 1/7/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class RegistrationController extends CommonController {
    private static final Logger log = LogManager.getLogger(RegistrationController.class.getName());

    private final UsersService usersService;

    @Autowired
    public RegistrationController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/registration.html", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute Users users) {
        ModelAndView modelAndView;

        if (!usersService.validate(users)) {
            log.error("users information incorrect");
            modelAndView = super.redirect("ace/login.html?msg=error");
            String msg = "information error";
            modelAndView.addObject("msg", msg);
            modelAndView.addObject(Css.css, Css.red);
            return modelAndView;
        }

        Integer count = usersService.countByUserAccountOrEmail(users);
        if (count == 0) {
            usersService.accountRegistration(users);
            log.info("新建用户：" + users.getUserAccount());
            modelAndView = super.page("ace/login.html");
            String msg = "Please login your account";
            modelAndView.addObject("msg", msg);
            modelAndView.addObject(Css.css, Css.green);
        } else {
            modelAndView = super.page("ace/login.html");
            String msg = "User exist";
            String msgCss = Css.red;
            modelAndView.addObject("msg", msg);
            modelAndView.addObject(Css.css, msgCss);
        }
        return modelAndView;
    }
}

