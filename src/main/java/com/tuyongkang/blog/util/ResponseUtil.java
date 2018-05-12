package com.tuyongkang.blog.util;

import com.tuyongkang.blog.mvc.model.ResponseVo;

/**
 * 返回数据工具类
 */
public class ResponseUtil {

    /**
     * 操作成功无异常返回成功
     * @return
     */
    public static ResponseVo renderSuccess(){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(200);
        responseVo.setMessage("操作成功");
        responseVo.setData(null);
        return responseVo;
    }

    public static ResponseVo renderSuccess(Object data){
        ResponseVo responseVo = renderSuccess();
        responseVo.setData(data);
        return responseVo;
    }

    /**
     * 抛出系统异常返回失败
     * @return
     */
    public static ResponseVo renderError(){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(500);
        responseVo.setMessage("系统出现异常");
        responseVo.setData(null);
        return responseVo;
    }

    /**
     * 抛出业务异常返回提示信息
     * @param message
     * @return
     */
    public static ResponseVo renderInfo(String message){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(300);
        responseVo.setMessage(message);
        responseVo.setData(null);
        return responseVo;
    }

}
