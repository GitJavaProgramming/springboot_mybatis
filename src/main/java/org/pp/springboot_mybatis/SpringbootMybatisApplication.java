package org.pp.springboot_mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.pp.springboot_mybatis.mapper") //扫描的mapper
@SpringBootApplication/*(exclude = {DataSourceAutoConfiguration.class})*/
public class SpringbootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisApplication.class, args);
    }

}
