package com.service;

import com.dao.ReportsDao;
import com.models.entity.Reports;
import com.models.info.ReportsInfo;
import com.util.BeanUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: ReportsService
 * @Date: 2022/11/15 下午 03:54
 * @Author: kalam_au
 * @Description:
 */

@Service
public class ReportsService {
    private static final Logger log = LogManager.getLogger(ReportsService.class.getName());

    private final ReportsDao reportsDao;

    @Autowired
    public ReportsService(ReportsDao reportsDao) {
        this.reportsDao = reportsDao;
    }

    public List<Reports> getReportList() {
        return reportsDao.findAllBySubReportIdOrderByCreatedDateDesc(0L);
    }


    public ReportsInfo getReportInfoById(Long reportId) {
        Reports report = reportsDao.findAllByReportId(reportId);
        BeanUtil beanUtil = new BeanUtil();
        ReportsInfo reportsInfo = beanUtil.copy(report, ReportsInfo.class);
        reportsInfo.setSubReports(getSubReports(reportsInfo));
        return reportsInfo;
    }

    private List<ReportsInfo> getSubReports(ReportsInfo reportsInfo) {
        BeanUtil beanUtil = new BeanUtil();
        List<Reports> reports = reportsDao.findAllBySubReportIdOrderByCreatedDateDesc(reportsInfo.getReportId());
        List<ReportsInfo> subReports = new ArrayList<>();
        for (Reports report : reports) {
            Reports r = reportsDao.findAllByReportId(report.getReportId());
            ReportsInfo info = beanUtil.copy(r, ReportsInfo.class);
            info.setSubReports(getSubReports(info));
            subReports.add(info);
        }
        return subReports;
    }

    public ReportsInfo saveAndFlush(ReportsInfo reportsInfo) {
        return reportsDao.saveAndFlush(reportsInfo);
    }

    public Reports saveAndFlush(Reports reports) {
        return reportsDao.saveAndFlush(reports);
    }

    public void deleteById(Long reportId) {
        reportsDao.deleteById(reportId);
    }

}

