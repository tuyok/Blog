package com.tuyongkang.blog.mvc.controller;

import com.tuyongkang.blog.mvc.model.ResponseVo;
import com.tuyongkang.blog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping("loginSuccess")
    @ResponseBody
    public ResponseVo loginSuccess(){
        return ResponseUtil.renderSuccess();
    }

    @RequestMapping("loginError")
    @ResponseBody
    public ResponseVo loginError(){
        return ResponseUtil.renderInfo("用户名/密码错误");
    }

}
