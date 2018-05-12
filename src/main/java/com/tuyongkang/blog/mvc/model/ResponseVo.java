package com.tuyongkang.blog.mvc.model;

/**
 * 通用的ajax响应结果封装
 */
public class ResponseVo {

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
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
