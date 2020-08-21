package com.kiwihouse.config.database;

/**
 * @author yjzn
 * @date 2019年12月19日15:37:31
 */

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

// 扫描 Mapper 接口并容器管理
@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {

    // 精确到 slave 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.kiwihouse.mapper";
    static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Value("${kiwihouse.datasource.url}")
    private String url;

    @Value("${kiwihouse.datasource.username}")
    private String user;

    @Value("${kiwihouse.datasource.password}")
    private String password;

    @Value("${kiwihouse.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "kiwihouseDataSource")
    @Primary
    public DataSource kiwihouseDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "kiwihouseTransactionManager")
    @Primary
    public DataSourceTransactionManager kiwihouseTransactionManager() {
        return new DataSourceTransactionManager(kiwihouseDataSource());
    }

    @Bean(name = "SqlSessionFactory")
    @Primary
    public SqlSessionFactory SqlSessionFactory(@Qualifier("kiwihouseDataSource") DataSource kiwihouseDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        //开启驼峰命名
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);

        sessionFactory.setDataSource(kiwihouseDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
