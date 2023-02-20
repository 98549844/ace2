package com.report;

import com.lowagie.text.pdf.BaseFont;
import com.report.config.ReportConfig;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @ClassName: Reports
 * @Description: 打印动态pdf报表
 * @author: Administrator
 * @date: 2016年12月30日 下午8:01:09
 */

/**
 * @Classname: Report
 * @Date: 16/7/2021 4:43 下午
 * @Author: garlam
 * @Description:
 */


public class UserReport {
    private static Logger log = LogManager.getLogger(UserReport.class.getName());
    private ReportConfig reportConfig;


    @Autowired
    public UserReport(ReportConfig reportConfig) {
        this.reportConfig = reportConfig;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        buildReport(getConn(), null);
    }

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        return (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/ace?characterEncoding=UTF-8&useSSL=false&useTimezone=true&serverTimezone=GMT%2B8", "root", "garlamau");
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(reportConfig.getForName());
        return (Connection) DriverManager.getConnection(reportConfig.getUrl(), reportConfig.getUserName(), reportConfig.getPassword());
    }

    @SuppressWarnings("deprecation")
    public static void buildReport(Connection conn, String sqlString) {
        JasperReportBuilder report = DynamicReports.report();//创建空报表
        //设置报表的一系列样式 ，stl是创建和自定义风格的一组方法
        StyleBuilder boldStl = DynamicReports.stl.style().bold();
        StyleBuilder boldCenteredStl = DynamicReports.stl.style(boldStl).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder titleStl = DynamicReports.stl.style(boldCenteredStl).setFontSize(16);
        StyleBuilder columnTitleStl = DynamicReports.stl.style(boldCenteredStl).setBorder(DynamicReports.stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);//设置列名栏的背景颜色为灰色
        StyleBuilder fontStyleBuilder = DynamicReports.stl.style().setPadding(2).setPdfFontName("STSong-Light").setPdfEncoding("UniGB-UCS2-H").setPdfEmbedded(BaseFont.NOT_EMBEDDED);

        columnTitleStl.setPdfFontName("STSong-Light").setPdfEncoding("UniGB-UCS2-H").setPdfEmbedded(BaseFont.NOT_EMBEDDED);

        titleStl.setPdfFontName("STSong-Light").setPdfEncoding("UniGB-UCS2-H").setPdfEmbedded(BaseFont.NOT_EMBEDDED);

        report.setPageFormat(PageType.A5); //设置每一页的格式

        report.columns(Columns.column("操作日期", "createdDate", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER), Columns.column("用户姓名", "username", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER), Columns.column("ip", "ip", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER), Columns.column("主机", "hostName", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER), Columns.column("用户ID", "userId", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER), Columns.column("email", "email", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER)).setColumnStyle(fontStyleBuilder)   //查询的数据的字体格式
                .setColumnTitleStyle(columnTitleStl) //设置列名的风格
                .setHighlightDetailEvenRows(true)  //偶数行高亮显示
                .title(Components.text("客户消费单").setStyle(titleStl))//标题
                .pageFooter(Components.pageXofY().setStyle(boldCenteredStl))//页角
                .setDataSource("SELECT * FROM users", conn)
        //.setDataSource("SELECT * FROM ReportMessage WHERE OperateTimeCustomerID = '" + sqlString + "'", conn)
        ;//数据源
        try {
            //显示报表
            report.show(false);  //关闭预览窗口后不退出程序
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("/Users/garlam/IdeaProjects/ace/src/main/resources/files/" + System.currentTimeMillis() + ".pdf");//构建一个pdf存放的输出位置
                report.toPdf(fileOutputStream);//打印的pdf地址
                try {
                    fileOutputStream.flush();  //保证pdf输出完毕
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (DRException e) {
            e.printStackTrace();
        }
    }
}

