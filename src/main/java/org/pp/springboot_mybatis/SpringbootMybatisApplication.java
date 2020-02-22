package org.pp.springboot_mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@ComponentScan(basePackages = {"org.pp"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {IgnoreScanBean.class}),
//        @ComponentScan.Filter(type = FilterType.CUSTOM, value = {BeanTypeScanFilter.class})})
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan("org.pp.springboot_mybatis.mapper") //扫描的mapper
@SpringBootApplication/*(exclude = {DataSourceAutoConfiguration.class})*/
@EnableScheduling
public class SpringbootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisApplication.class, args);
    }

}
