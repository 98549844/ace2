package com.models.entity;

import cn.dev33.satoken.stp.StpUtil;
import com.models.entity.base.BaseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Classname: Reports
 * @Date: 2022/11/15 下午 12:14
 * @Author: kalam_au
 * @Description:
 */

@EntityListeners(AuditingEntityListener.class)
@Table(name = "reports")
@Entity
public class Reports extends BaseEntity {
    private static final Logger log = LogManager.getLogger(Reports.class.getName());


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    @Column
    private Long reportId;
    @Column
    private Long subReportId = 0L;
    @Column(nullable = false)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @Column
    private String status = NEW; //NEW, FOLLOWING, CLOSE
    public static final String NEW = "NEW";
    public static final String FOLLOWING = "FOLLOWING";
    public static final String CLOSE = "CLOSE";
    @Column(nullable = false)
    private String level; //info, warning, error, critical,fatal
    public static final String SUGGESTION = "SUGGESTION";
    public static final String INFO = "INFO"; //资料性质
    public static final String BUG = "BUG"; // 错误
    public static final String WARNING = "WARNING"; //警告但不应响使用
    public static final String ERROR = "ERROR"; // 警告并不能使用
    public static final String CRITICAL = "CRITICAL"; //模组崩溃
    public static final String FATAL = "FATAL"; //系统崩溃
    @Column
    private String attachment;
    @Column
    private LocalDateTime reportDate = LocalDateTime.now();
    @Column
    private Long reporter;


    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getSubReportId() {
        return subReportId;
    }

    public void setSubReportId(Long subReportId) {
        this.subReportId = subReportId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public Long getReporter() {
        return reporter;
    }

    public void setReporter() {
        Users user = (Users) StpUtil.getSession().get("user");
        this.reporter = user.getUserId();
    }
}

