package com.atguigu.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
      //报错SysRoleMapper的实现类找不到，可加这个注解(MapperScan("com.atguigu.auth.mapper"))，或在SysRoleMapper上方加@Mapper注解
@ComponentScan("com.atguigu")       //指定启动时扫描的哪个包下的内容
public class ServiceAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}
