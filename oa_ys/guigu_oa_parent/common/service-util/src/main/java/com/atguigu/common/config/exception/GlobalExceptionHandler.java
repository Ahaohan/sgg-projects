package com.atguigu.common.config.exception;

import com.atguigu.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理，执行的方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    //这是切面吧 切点相当于是controller注解
    public Result error(Exception e) {
        e.printStackTrace();    //输出异常信息
        return Result.fail().message("执行全局异常处理...");
    }

    //特定异常处理 先找特定异常，如果有就执行特定，没有就都按全局异常进行处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    //这是切面吧 切点相当于是controller注解
    public Result error(ArithmeticException e) {
        e.printStackTrace();    //输出异常信息
        return Result.fail().message("执行特定异常处理...");
    }

    //自定义异常处理  自己写一个异常类，然后手动抛出，让它抛出自己定义的异常
    @ExceptionHandler(GuiguException.class)
    @ResponseBody
    //这是切面吧 切点相当于是controller注解
    public Result error(GuiguException e) {
        e.printStackTrace();    //输出异常信息
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }
}
