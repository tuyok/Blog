package com.tuyongkang.blog.model.vo;

/**
 * 登陆Vo
 */
public class LoginInVo {

    private String userName; //用户名

    private String password; //密码

    private String vCode; //验证码

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }
}
