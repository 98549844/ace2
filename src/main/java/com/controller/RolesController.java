package com.controller;


import com.controller.common.CommonController;
import com.google.gson.Gson;
import com.models.entity.Roles;
import com.service.RolesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.util.NullUtil;

import java.util.List;


/**
 * @Classname: UserController
 * @Date: 11/11/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class RolesController extends CommonController {
    private static final Logger log = LogManager.getLogger(RolesController.class.getName());

    private final RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @RequestMapping(value = "/roles/getByUserId/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Roles> getRolesByUserId(@PathVariable Long userId) {
        log.info("access roles/getByUserId/{}", userId);
        return rolesService.getRolesByUserId(userId);
    }


    @RequestMapping(value = "/roles.html", method = RequestMethod.GET)
    public ModelAndView getRoleList() {
        List<Roles> rolesList = rolesService.findAll();
        ModelAndView modelAndView = super.page("ace/modules/roles/roles");
        modelAndView.addObject("roles", rolesList);
        return modelAndView;
    }

    @RequestMapping(value = "/roles/create.html", method = RequestMethod.GET)
    public ModelAndView insertRole(String roleName, String roleCode, String select, String update, String insert, String delete) {
        log.info("roleName: {}", roleName);
        log.info("roleCode: {}", roleCode);
        log.info("select: {}", select);
        log.info("update: {}", update);
        log.info("insert: {}", insert);
        log.info("delete: {}", delete);

        List<Roles> rolesList = rolesService.findAll();
        ModelAndView modelAndView = super.page("ace/modules/roles/roles");
        modelAndView.addObject("roles", rolesList);

        return modelAndView;
    }

    @RequestMapping(value = "/roles/check.html", method = RequestMethod.GET)
    public ModelAndView checkRole(String roleCode) {
        log.info("roleCode: {}", roleCode);
        Roles roles = rolesService.findByRoleCode(roleCode);
        ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        if (NullUtil.isNotNull(roles)) {
            log.info("exist");
            modelAndView.addObject("ajaxResult", "exist");
        } else {
            log.info("not exist");
            modelAndView.addObject("ajaxResult", "not exist");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/roles/edit.html", method = RequestMethod.GET)
    public ModelAndView editRole(Long roleId) {
        log.info("roleId: {}", roleId);
        Roles roles = rolesService.findRolesByRoleId(roleId);

        ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        modelAndView.addObject("ajaxResult", new Gson().toJson(roles));

        return modelAndView;
    }

}
