package com.atguigu.auth;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
//org.junit.Test//不要引入这个依赖，执行会有问题；现在默认使用junit5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class TestMpDemo1 {


    //注入  @Autowired、@Resource或
    @Autowired
    private SysRoleMapper mapper;       //动态创建实现类对象；报错是因为SysRoleMapper是个接口，没有实现类；1.不管，可以执行，2.在SysRoleMapper接口上面加上@Repository，交给Spring（集）管理

    //直接调用Mapper中的方法，就可以实现对数据库进行操作

    //查询所有的记录
    @Test
    public void getAll(){
        List<SysRole> list = mapper.selectList(null);
        System.out.println(list);
    }

    //添加操作
    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员2");
        sysRole.setRoleCode("role2");
        sysRole.setDescription("角色管理员2");

        int rows = mapper.insert(sysRole);
        System.out.println(rows);       //输出影响行数
        System.out.println(sysRole.getId());

    }

    //修改操作
    @Test
    public void update() {
        //根据id查询
        SysRole role = mapper.selectById(4);
        //设置修改值
        role.setRoleName("atguigu角色管理员2");

        //调用方法实现最终修改
        int rows = mapper.updateById(role);
        System.out.println(rows);
    }

    //删除操作
    @Test
    public void deleteById() {
        int rows = mapper.deleteById(5);
    }

    //批量删除操作
    @Test
    public void testDeleteBatchIds() {
        int result = mapper.deleteBatchIds(Arrays.asList(5,6,7));       //简单构建一个集合
        System.out.println(result);
    }

    //条件查询
    @Test
    public void testQuery1(){
        //创建QueryWrapper对象，调用方法封装条件
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name","总经理");
        //调用mp方法实现查询操作
        List<SysRole> list = mapper.selectList(wrapper);
        System.out.println(list);
    }

    @Test
    public void testQuery2(){
        //创建LambdaQueryWrapper对象，调用方法封装条件
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getRoleName,"总经理");     //上面那种写法，字段名称必须要和表里的一样，这样写就不需要关注表中名称具体是什么
        //调用mp方法实现查询操作
        List<SysRole> list = mapper.selectList(wrapper);
        System.out.println(list);
    }


}

