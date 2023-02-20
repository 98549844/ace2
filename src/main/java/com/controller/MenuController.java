package com.controller;

import com.controller.common.CommonController;
import com.models.entity.Menu;
import com.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Classname: MenuController
 * @Date: 12/11/2021 11:45 下午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class MenuController extends CommonController {
    private static final Logger log = LogManager.getLogger(MenuController.class.getName());

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "/menu.html", method = RequestMethod.GET)
    public ModelAndView getMenuList() {
        List<Menu> menuList = menuService.getMenuList();
        ModelAndView modelAndView = super.page("ace/modules/menus/menu");
        return modelAndView;
    }

    @RequestMapping(value = "/menu/delete.html", method = RequestMethod.DELETE)
    public ModelAndView deleteMenu() {
        List<Menu> menuList = menuService.getMenuList();
        ModelAndView modelAndView = super.page("ace/modules/menus/menu");
        return modelAndView;
    }

    @RequestMapping(value = "/menu/add.html", method = RequestMethod.GET)
    public ModelAndView addMenu() {
        List<Menu> menuList = menuService.getMenuList();
        ModelAndView modelAndView = super.page("ace/modules/menus/menu");
        return modelAndView;
    }

    @RequestMapping(value = "/menu/edit.html", method = RequestMethod.GET)
    public ModelAndView editMenu() {
        List<Menu> menuList = menuService.getMenuList();
        ModelAndView modelAndView = super.page("ace/modules/menus/menu");
        return modelAndView;
    }

}

