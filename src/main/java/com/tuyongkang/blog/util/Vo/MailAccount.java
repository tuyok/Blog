package com.tuyongkang.blog.util.Vo;

import java.io.Serializable;

public class MailAccount implements Serializable {

    /**
     * 邮箱用户名
     */
    private String userName;
    /**
     * 邮箱用户密码
     */
    private String password;
    /**
     * 发送邮件服务器
     */
    private String mail_smtp_host;
    /**
     * 发送邮件端口 (使用默认端口请填null,否则填邮件服务器对应端口；如启用ssl方式发送邮件，请设置邮件服务器ssl端口)
     */
    private String mail_smtp_port;

    /**
     * SSL:使用SSL加密方式发送 true; 否则false
     */
    private boolean ssl;

    public MailAccount() {
    }

    public MailAccount(String userName, String password, String mail_smtp_host,
                       String mail_smtp_port, boolean ssl) {
        this.userName = userName;
        this.password = password;
        this.mail_smtp_host = mail_smtp_host;
        this.mail_smtp_port = mail_smtp_port;
        this.ssl = ssl;
    }

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

    public String getMail_smtp_host() {
        return mail_smtp_host;
    }

    public void setMail_smtp_host(String mail_smtp_host) {
        this.mail_smtp_host = mail_smtp_host;
    }

    public String getMail_smtp_port() {
        return mail_smtp_port;
    }

    public void setMail_smtp_port(String mail_smtp_port) {
        this.mail_smtp_port = mail_smtp_port;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

}
