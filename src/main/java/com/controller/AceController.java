package com.controller;

import com.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname: AceController
 * @Date: 1/7/2021 4:33 上午
 * @Author: garlam
 * @Description:
 */


@Controller
@RequestMapping("/ace")
public class AceController extends CommonController {
    private static final Logger log = LogManager.getLogger(AceController.class.getName());

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView index() {
        if (!isLogin()) {
            return logout();
        }
        ModelAndView modelAndView = super.page("ace/index");
        return modelAndView;
    }

    @RequestMapping(value = "/blank.html", method = RequestMethod.GET)
    public ModelAndView blank() {
        log.info("access ace/blank.html");
        ModelAndView modelAndView = super.page("ace/blank");
        return modelAndView;
    }

    @RequestMapping(value = "/elements.html", method = RequestMethod.GET)
    public ModelAndView elements() {
        ModelAndView modelAndView = super.page("ace/tool-pages/elements");
        return modelAndView;
    }

    @RequestMapping(value = "/buttons.html", method = RequestMethod.GET)
    public ModelAndView buttons() {
        ModelAndView modelAndView = super.page("ace/tool-pages/buttons");
        return modelAndView;
    }

    @RequestMapping(value = "/grid.html", method = RequestMethod.GET)
    public ModelAndView grid() {
        log.info("access ace/grid.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/grid");
        return modelAndView;
    }

    @RequestMapping(value = "/faq.html", method = RequestMethod.GET)
    public ModelAndView faq() {
        log.info("access ace/faq.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/faq");
        return modelAndView;
    }

/*    @RequestMapping(value = "/profile.html", method = RequestMethod.GET)
    public ModelAndView profile() {
        log.info("access ace/profile.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/profile");
        return modelAndView;
    }*/

    @RequestMapping(value = "/inbox.html", method = RequestMethod.GET)
    public ModelAndView inbox() {
        log.info("access ace/inbox.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/inbox");
        return modelAndView;
    }

    @RequestMapping(value = "/pricing.html", method = RequestMethod.GET)
    public ModelAndView pricing() {
        log.info("access ace/pricing.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/pricing");
        return modelAndView;
    }

    @RequestMapping(value = "/invoice.html", method = RequestMethod.GET)
    public ModelAndView invoice() {
        log.info("access ace/invoice.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/invoice");
        return modelAndView;
    }

    @RequestMapping(value = "/timeline.html", method = RequestMethod.GET)
    public ModelAndView timeline() {
        log.info("access ace/timeline.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/timeline");
        return modelAndView;
    }

    @RequestMapping(value = "/search.html", method = RequestMethod.GET)
    public ModelAndView search() {
        log.info("access ace/search.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/search");
        return modelAndView;
    }

    @RequestMapping(value = "/email.html", method = RequestMethod.GET)
    public ModelAndView email() {
        log.info("access ace/email.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/email");
        return modelAndView;
    }

    @RequestMapping(value = "/typography.html", method = RequestMethod.GET)
    public ModelAndView typography() {
        log.info("access ace/email.typography");
        ModelAndView modelAndView = super.page("ace/tool-pages/typography");
        return modelAndView;
    }

    @RequestMapping(value = "/content-slider.html", method = RequestMethod.GET)
    public ModelAndView contentSlider() {
        log.info("access ace/content-slider.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/content-slider");
        return modelAndView;
    }

    @RequestMapping(value = "/treeview.html", method = RequestMethod.GET)
    public ModelAndView treeView() {
        log.info("access ace/email.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/treeview");
        return modelAndView;
    }

    @RequestMapping(value = "/jquery-ui.html", method = RequestMethod.GET)
    public ModelAndView jqueryUi() {
        log.info("access ace/email.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/jquery-ui");
        return modelAndView;
    }

    @RequestMapping(value = "/nestable-list.html", method = RequestMethod.GET)
    public ModelAndView nestableList() {
        log.info("access ace/nestable-list.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/nestable-list");
        return modelAndView;
    }

    @RequestMapping(value = "/widgets.html", method = RequestMethod.GET)
    public ModelAndView widgets() {
        log.info("access ace/widgets.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/widgets");
        return modelAndView;
    }

    @RequestMapping(value = "/calendar.html", method = RequestMethod.GET)
    public ModelAndView calendar() {
        log.info("access ace/calendar.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/calendar");
        return modelAndView;
    }

    @RequestMapping(value = "/form-elements.html", method = RequestMethod.GET)
    public ModelAndView formElements() {
        log.info("access ace/form-elements.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/form-elements");
        return modelAndView;
    }

    @RequestMapping(value = "/form-elements-2.html", method = RequestMethod.GET)
    public ModelAndView formElements2() {
        log.info("access ace/form-elements-2.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/form-elements-2");
        return modelAndView;
    }

    @RequestMapping(value = "/form-wizard.html", method = RequestMethod.GET)
    public ModelAndView formWizard() {
        log.info("access ace/form-wizard.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/form-wizard");
        return modelAndView;
    }

    @RequestMapping(value = "/wysiwyg.html", method = RequestMethod.GET)
    public ModelAndView wysiwyg() {
        log.info("access ace/wysiwyg.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/wysiwyg");
        return modelAndView;
    }

    @RequestMapping(value = "/dropzone.html", method = RequestMethod.GET)
    public ModelAndView dropzone() {
        log.info("access ace/dropzone.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/dropzone");
        return modelAndView;
    }


    @RequestMapping(value = "/tables.html", method = RequestMethod.GET)
    public ModelAndView tables() {
        log.info("access ace/tables.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/tables");
        return modelAndView;
    }

    @RequestMapping(value = "/jqgrid.html", method = RequestMethod.GET)
    public ModelAndView jqgrid() {
        log.info("access ace/jqgrid.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/jqgrid");
        return modelAndView;
    }

    @RequestMapping(value = "/top-menu.html", method = RequestMethod.GET)
    public ModelAndView topMenu() {
        log.info("access ace/top-menu.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/top-menu");
        return modelAndView;
    }

    @RequestMapping(value = "/two-menu-1.html", method = RequestMethod.GET)
    public ModelAndView twoMenu1() {
        log.info("access ace/two-menu-1.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/two-menu-1");
        return modelAndView;
    }

    @RequestMapping(value = "/two-menu-2.html", method = RequestMethod.GET)
    public ModelAndView twoMenu2() {
        log.info("access ace/two-menu-2.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/two-menu-2");
        return modelAndView;
    }

    @RequestMapping(value = "/mobile-menu-1.html", method = RequestMethod.GET)
    public ModelAndView mobileMenu1() {
        log.info("access ace/mobile-menu-1.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/mobile-menu-1");
        return modelAndView;
    }

    @RequestMapping(value = "/mobile-menu-2.html", method = RequestMethod.GET)
    public ModelAndView mobileMenu2() {
        log.info("access ace/mobile-menu-2.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/mobile-menu-2");
        return modelAndView;
    }

    @RequestMapping(value = "/mobile-menu-3.html", method = RequestMethod.GET)
    public ModelAndView mobileMenu3() {
        log.info("access ace/mobile-menu-3.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/mobile-menu-3");
        return modelAndView;
    }


}

