package com.controller;

import com.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/ace")
public class ErrorController extends CommonController {
	private static final Logger log = LogManager.getLogger(ErrorController.class.getName());


	@RequestMapping(value = "/404.html", method = RequestMethod.GET)
	public ModelAndView error404() {
		ModelAndView modelAndView = super.page("ace/error404");
		return modelAndView;
	}

	@RequestMapping(value = "/500.html", method = RequestMethod.GET)
	public ModelAndView error500() {
		ModelAndView modelAndView = super.page("ace/error500");
		return modelAndView;
	}

	@RequestMapping(value = "/error.html", method = RequestMethod.GET)
	public ModelAndView error() {
		ModelAndView modelAndView = super.page("ace/error");
		return modelAndView;
	}


}
