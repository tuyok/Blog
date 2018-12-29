package com.tuyongkang.blog.controller;

import com.tuyongkang.blog.model.vo.ResponseVo;
import com.tuyongkang.blog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping("/login")
    public void login(HttpServletResponse response) throws IOException{
        response.sendRedirect("/login.html");
    }

    @RequestMapping("loginSuccess")
    @ResponseBody
    public ResponseVo loginSuccess(){
        return ResponseUtil.renderSuccess();
    }

    @RequestMapping("loginError")
    @ResponseBody
    public ResponseVo loginError(HttpServletRequest request){
        Exception e = (Exception) request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        return ResponseUtil.renderInfo(e.getMessage());
    }

}
