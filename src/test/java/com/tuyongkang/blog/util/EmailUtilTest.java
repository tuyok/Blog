package com.tuyongkang.blog.util;

import com.tuyongkang.blog.util.Vo.EmailInfoVo;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtilTest {

    @Test
    public void sendEmail(){
        EmailInfoVo emailInfoVo = new EmailInfoVo();
        emailInfoVo.setSubject("邮件发送测试");
        emailInfoVo.setUserName("348727112@qq.com");
        emailInfoVo.setPassword("dxmukbwbfsufbhaf");
        emailInfoVo.setFrom("tuyongkang");
        emailInfoVo.setTos(new String[] {"2654126119@qq.com"});
        emailInfoVo.setContent("你好，涂永康");
        emailInfoVo.setSendName("而嗡嗡嗡");
        EmailUtil.sendMail(emailInfoVo);
    }

    @Test
    public void sendEmail2() throws Exception{
        // 配置参数

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        // 指定验证为true
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.timeout","1000");
        // 验证账号及密码，密码需要是第三方授权码
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("13570208304@163.com", "tyk19941111");
            }
        };
        Session session = Session.getInstance(props, auth);
        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);
// 设置发送者
        message.setFrom(new InternetAddress("13570208304@163.com"));
// 设置发送方式与接收者
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("3487227112@qq.com"));
// 设置主题
        message.setSubject("邮件发送测试");
// 设置内容
        message.setContent("hahahahhahahahhahahah", "text/html;charset=utf-8");

        Transport.send(message);

    }

}
