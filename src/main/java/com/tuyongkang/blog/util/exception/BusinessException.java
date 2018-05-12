package com.tuyongkang.blog.util.exception;

/**
 * 自定义的业务异常
 */
public class BusinessException extends RuntimeException {

    public BusinessException(){
        super();
    }

    public BusinessException(String message){
        super(message);
    }

}
