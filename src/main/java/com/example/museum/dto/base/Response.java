package com.example.museum.dto.base;

import java.io.Serializable;

/**
 * 公用返回类
 * @author 小明
 */
public class Response<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4599494560630345233L;

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 默认构造函数
     */
    public Response() {
        super();
    }

    /**
     * 响应码的构造函数
     *
     * @param codeTable
     */
    public Response(CodeTable codeTable) {
        this.code = codeTable.getCode();
        this.message = codeTable.getMessage();
        this.data = null;
    }

    /**
     * 自定义相应信息
     *
     * @param codeTable
     * @param msg
     */
    public Response(CodeTable codeTable, String msg) {
        this.code = codeTable.getCode();
        this.message = msg;
    }

    /**
     * 构造函数  (成功默认调用)
     *
     * @param data 响应数据
     */
    public Response(T data) {
        this.code = CodeTable.SUCCESS.getCode();
        this.message = CodeTable.SUCCESS.getMessage();
        this.data = data;
    }


    /**
     * 构造函数  (成功默认调用)
     *
     * @param data 响应数据
     */
    public Response(CodeTable codeTable, String msg, T data) {
        this.code = codeTable.getCode();
        this.message = msg;
        this.data = data;
    }

    /**
     * 响应是否成功
     */
    public boolean isSuccess() {
        return CodeTable.SUCCESS.getCode() == code;
    }

    public int getCode() {
        return code;
    }

    public Response<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code:" + code +
                ", message:'" + message + '\'' +
                ", data:" + data +
                '}';
    }
}
