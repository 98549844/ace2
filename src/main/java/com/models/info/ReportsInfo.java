package com.models.info;

import com.models.entity.Reports;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @Classname: ReportsInfo
 * @Date: 2022/11/15 下午 03:52
 * @Author: kalam_au
 * @Description:
 */


public class ReportsInfo extends Reports {
    private static final Logger log = LogManager.getLogger(ReportsInfo.class.getName());

    private List<ReportsInfo> subReports;


    public List<ReportsInfo> getSubReports() {
        return subReports;
    }

    public void setSubReports(List<ReportsInfo> subReports) {
        this.subReports = subReports;
    }
}

