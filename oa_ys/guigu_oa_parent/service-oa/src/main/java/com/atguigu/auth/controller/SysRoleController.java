package com.atguigu.auth.controller;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.common.config.exception.GuiguException;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.vo.system.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理接口")
@RestController         //Controller注解在Spring中进行注册，交给Spring管理；ResponseBody返回JSON数据
@RequestMapping("/admin/system/sysRole")        //执行他相关的返回路径
public class SysRoleController {

    //http://localhost:8800/admin/system/sysRole/findAll

    //注入service
    @Autowired
    private SysRoleService sysRoleService;

    //查询所有角色
//    @GetMapping("/findAll")
//    public List<SysRole> findAll(){
//        //调用service的方法
//        List<SysRole> list = sysRoleService.list();
//        return list;
//    }

    //统一返回数据结果
    @ApiOperation("查询所有角色")
    @GetMapping("/findAll")
    public Result findAll() {
        //调用service的方法
        List<SysRole> list = sysRoleService.list();

        //模拟异常效果
        try {
            int i = 10 / 0;
        }catch (Exception e){
            //抛出自定义异常
            throw new GuiguException(20001,"执行了自定义异常...");
        }


        return Result.ok(list);
    }

    //条件分页查询
    //page 当前页  limit 每页显示记录数
    //SysRoleQueryVo 条件对象
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo) {      //路径传值
        //调用service的方法实现
        //1 创建Page对象，传递分页相关参数
        //page 当前页  limit 每页显示记录数
        Page<SysRole> pageParam = new Page<>(page, limit);

        //2 封装条件，判断条件是否为空，不为空进行封装
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if (!StringUtils.isEmpty(roleName)) {
            //封装  like 模糊查询；eq 完全匹配
            wrapper.like(SysRole::getRoleName, roleName);
        }

        //3 调用方法实现
        IPage<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }


    //添加角色
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody SysRole role) {      //RequestBody，通过请求体传递（不用这个注解，是通过Get提交请求），可以理解为通过JSON形式传递，但是在get提交时是没有请求体的，所以换为PostMapping
        //调用service的方法
        boolean is_success = sysRoleService.save(role);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //修改角色 - 根据id查询
    @ApiOperation("根据id查询")
    @GetMapping("get/{id}")
    //给id查询起个名字，就叫get，后面传入id值
    public Result get(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    //修改角色 - 最终修改
    @ApiOperation("修改角色")
    @PutMapping("update")
    public Result update(@RequestBody SysRole role) {      //根据rest或rest for风格，查询用get提交，添加用post，修改用post也可以，可以改成put，删除用delete提交
        //调用service的方法
        boolean is_success = sysRoleService.updateById(role);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //根据id删除
    @ApiOperation("根据id删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_success = sysRoleService.removeById(id);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //批量删除
    //前端用JSON数组传递 [1,2,3] ，后端用list集合接收
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean is_success = sysRoleService.removeByIds(idList);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }       //removeByIds(ids) ? Result.ok() : Result.fail(); 优雅
    }


}
