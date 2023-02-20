package com.dao;

import com.models.entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname: ReportsDao
 * @Date: 2022/11/15 下午 03:56
 * @Author: kalam_au
 * @Description:
 */

@Repository
@Transactional
public interface ReportsDao extends JpaRepository<Reports, Long>, JpaSpecificationExecutor<Reports> {

    Reports findAllByReportId(Long reportId);

    List<Reports> findAllByReportIdInOrderByCreatedDateDesc(List<Long> reportIds);

    List<Reports> findAllBySubReportIdOrderByCreatedDateDesc(Long subReportId);


}
