package com.controller;

import com.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname: CssController
 * @Date: 13/7/2021 2:40 下午
 * @Author: garlam
 * @Description:
 */


public class CssController extends CommonController {
    private static final Logger log = LogManager.getLogger(CssController.class.getName());

    @RequestMapping(value = "/active.html", method = RequestMethod.GET)
    public ModelAndView active() {
        ModelAndView modelAndView = super.page("ace/active");
        modelAndView.addObject("active", "");
        return modelAndView;
    }

    @RequestMapping(value = "/open.html", method = RequestMethod.GET)
    public ModelAndView open() {
        ModelAndView modelAndView = super.page("ace/open");
        modelAndView.addObject("open", "");
        return modelAndView;
    }
}

