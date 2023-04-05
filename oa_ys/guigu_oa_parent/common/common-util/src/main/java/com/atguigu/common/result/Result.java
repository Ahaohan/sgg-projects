package com.atguigu.common.result;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;      //状态码
    private String message;     //返回信息
    private T data;     //数据

    //构造私有化（这个类 不能new了，只能我自己操作，但可以提供对外操作的方法）
    private Result(){ }

    //封装返回数据，让返回数据格式统一
    //建造者模式
    //感觉这里搞太复杂了 思路是很对的 其实可以继承造一个类继承map实现
    //瑞吉这里的处理比这边的要好一点点
    //为什么不用postman来
    //postman是真的吃内存


    //封装返回的数据
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();
        //封装数据
        if (body!=null){
            result.setData(body);
        }

        //状态码
        result.setCode(resultCodeEnum.getCode());
        //返回信息
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }


    //成功
    public static<T> Result<T> ok(){
        return build(null,ResultCodeEnum.SUCCESS);
    }

    public static<T> Result<T> ok(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }


    //失败
    public static<T> Result<T> fail(){
        return build(null,ResultCodeEnum.FAIL);
    }

    public static<T> Result<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }


    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

}
