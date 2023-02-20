package com.controller;

import com.controller.common.CommonController;
import com.models.entity.Reports;
import com.models.entity.Users;
import com.models.info.ReportsInfo;
import com.service.ReportsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Classname: ReportController
 * @Date: 2022/11/13 上午 01:16
 * @Author: kalam_au
 * @Description:
 */


@Controller
@RequestMapping("/ace")
public class ReportController extends CommonController {
    private static final Logger log = LogManager.getLogger(ReportController.class.getName());

    private final ReportsService reportsService;


    @Autowired
    public ReportController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @RequestMapping(value = "/report/list.html", method = RequestMethod.GET)
    public ModelAndView getReports() {
        log.info("access report/list");
        ModelAndView modelAndView = super.page("ace/modules/report/report-list");
        modelAndView.addObject("reports", reportsService.getReportList());
        return modelAndView;
    }

    @RequestMapping(value = "/report/newIssue.html", method = RequestMethod.GET)
    public ModelAndView newIssue(@ModelAttribute Reports reports) {
        log.info("access report/newIssue.html => create new issue");
        ModelAndView modelAndView = super.page("ace/modules/report/report");
        //   reports = reportsService.saveAndFlush(reports);
        modelAndView.addObject("report", reports);
        return modelAndView;
    }


    @RequestMapping(value = "/report/reportId.html/{reportId}", method = RequestMethod.GET)
    public ModelAndView getReportInfoById(@PathVariable(value = "reportId") Long reportId) {
        log.info("access report/list {} => update issue", reportId);
        ModelAndView modelAndView = super.page("ace/modules/report/report");
        ReportsInfo reportsInfo = reportsService.getReportInfoById(reportId);
        modelAndView.addObject("report", reportsInfo);
        return modelAndView;
    }


    @RequestMapping(value = "/report/submit.html", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute ReportsInfo reportsInfo) {
        log.info("access report/submit.html");
        ModelAndView modelAndView = super.page("ace/modules/report/report");
        reportsInfo = reportsService.saveAndFlush(reportsInfo);

        modelAndView.addObject("report", reportsInfo);
        return modelAndView;
    }

    @RequestMapping(value = "/report/delete.html/{reportId}", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteReportById(@PathVariable Long reportId) {
        log.info("access report/delete reportId: {}", reportId);
        reportsService.deleteById(reportId);
        return true;
    }

}

