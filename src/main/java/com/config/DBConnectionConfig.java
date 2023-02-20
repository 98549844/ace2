package com.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.controller.common.CommonController;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * @Classname: DBConnectionConfig
 * @Date: 4/4/2022 2:06 AM
 * @Author: garlam
 * @Description:
 */

//@MapperScan(basePackages = "com.mapper" , sqlSessionTemplateRef = "template_name")
//@Configuration
public class DBConnectionConfig extends CommonController {
    private static final Logger log = LogManager.getLogger(DBConnectionConfig.class.getName());

    //yml有local和remote remote datasource
    //启动时因为两个datasource用相同的实例, 所以报错
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource);
        return sqlSessionFactoryBean.getObject();
    }

    //  @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("sqlSessionFactory的对应pojo");
        return mapperScannerConfigurer;
    }

    //作用和MapperScannerConfigurer但不需要指定package scan
    // @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.remote")
    public DataSource remoteDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory remoteSqlSessionFactory(DataSource remoteDatasource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(remoteDatasource);
        return sqlSessionFactoryBean.getObject();
    }

    //  @Bean
    public MapperScannerConfigurer remoteMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("remoteSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("remoteSqlSessionFactory的对应pojo");
        return mapperScannerConfigurer;
    }

    //作用和MapperScannerConfigurer但不需要指定package scan
    //@Bean
    public SqlSessionTemplate remoteSqlSessionTemplate(SqlSessionFactory remoteSqlSessionFactory) {
        return new SqlSessionTemplate(remoteSqlSessionFactory);
    }

}

