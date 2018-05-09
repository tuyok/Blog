package com.tuyongkang.blog.controller;

import com.tuyongkang.blog.model.vo.LoginInVo;
import com.tuyongkang.blog.model.vo.ResponseVo;
import com.tuyongkang.blog.service.LoginService;
import com.tuyongkang.blog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class LoginController {

    private LoginService loginService;

    @RequestMapping("login")
    @ResponseBody
    public ResponseVo login(LoginInVo loginInVo, HttpServletRequest request){
        ResponseVo responseVo;
        try{
        }catch(Exception e){
            e.printStackTrace();
            return ResponseUtil.renderError();
        }
        return ResponseUtil.renderError();
    }

}
