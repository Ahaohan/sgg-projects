package com.atguigu.auth;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//org.junit.Test//不要引入这个依赖，执行会有问题；现在默认使用junit5


@SpringBootTest
public class TestMpDemo2 {


    //注入  @Autowired、@Resource或
    @Autowired
    private SysRoleService service;

    //直接通过service调用（Mapper中的）方法，就可以实现对数据库进行操作，更多详细看课件：6.3、测试Service接口

    //查询所有的记录
    @Test
    public void getAll(){
        List<SysRole> list = service.list();
        System.out.println(list);
    }

}

