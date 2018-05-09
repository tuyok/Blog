package com.tuyongkang.blog.util.Vo;

import java.io.Serializable;
import java.util.List;

public class EmailInfoVo implements Serializable {

    private static final long serialVersionUID = -6814843971004397989L;

    private String subject;     // 邮件主题
    private String content;     // 邮件内容
    private String[] tos;       // 收件人邮箱地址
    private String userName;    // 发送邮件地址
    private String password;    // 发送邮箱密码

    private String from;        // 发件人邮箱地址，非必填
    private String sendName;    // 昵称，非必填
    private String smtpServer;  // 发件服务器SMTP地址，非必填，默认使用 smtp.exmail.qq.com
    private String smtpPort;    // 发件服务器SMTP端口，非必填，默认使用 465
    private List<EmailAttachmentVo> attachmentList; // 附件列表，非必填

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getTos() {
        return tos;
    }

    public void setTos(String[] tos) {
        this.tos = tos;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public List<EmailAttachmentVo> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<EmailAttachmentVo> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
