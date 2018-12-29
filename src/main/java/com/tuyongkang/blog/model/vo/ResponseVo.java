package com.tuyongkang.blog.model.vo;

/**
 * 通用的ajax响应结果封装
 */
public class ResponseVo<T> {

    /**
     * 结果码
     */
    private Integer code; //200 OK, 300 INFO, 500 ERROR

    /**
     * 结果信息
     */
    private String message;

    /**
     * 结果数据
     */
    private T data;

    public ResponseVo(){}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
