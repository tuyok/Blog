package com.tuyongkang.blog.util;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class SimpleEmailUtil {

    String to = "";         // 收件人邮箱地址
    String from = "";       // 发件人邮箱地址
    String host = "";       // smtp主机
    String username = "";   //用户名
    String password = "";   //密码
    String filename = "";    // 附件文件名
    String subject = "";     // 邮件主题
    String content = "";     // 邮件正文
    Vector file = new Vector();// 附件文件集合

    public SimpleEmailUtil() {}

    public SimpleEmailUtil(String to, String from, String smtpServer,
                     String username, String password, String subject, String content) {
        this.to = to;
        this.from = from;
        this.host = smtpServer;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPassWord(String pwd) {
        this.password = pwd;
    }

    public void setUserName(String usn) {
        this.username = usn;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @param strText
     * @return
     * @Description: 把主题转为中文 utf-8
     * @author alexli
     * @Date   2017年6月22日 上午10:37:07
     */
    public String transferChinese(String strText) {
        try {
            strText = MimeUtility.encodeText(new String(strText.getBytes(),
                    "utf-8"), "utf-8", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strText;
    }

    public void attachfile(String fname) {
        file.addElement(fname);
    }

    /**
     * @return
     * @Description: 发送邮件，发送成功返回true 失败false
     * @author alexli
     * @Date   2017年6月22日 上午10:37:47
     */
    public boolean sendMail() {

        // 构造mail session
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "25");//465

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // 构造MimeMessage 并设定基本的值
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));

            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to));
            subject = transferChinese(subject);
            msg.setSubject(subject);

            // 构造Multipart
            Multipart mp = new MimeMultipart();

            // 向Multipart添加正文
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setContent(content, "text/html;charset=utf-8");

            // 向MimeMessage添加（Multipart代表正文）
            mp.addBodyPart(mbpContent);

            // 向Multipart添加附件
            Enumeration efile = file.elements();
            while (efile.hasMoreElements()) {

                MimeBodyPart mbpFile = new MimeBodyPart();
                filename = efile.nextElement().toString();
                FileDataSource fds = new FileDataSource(filename);
                mbpFile.setDataHandler(new DataHandler(fds));
                String filename = new String(fds.getName().getBytes(), "ISO-8859-1");

                mbpFile.setFileName(filename);
                // 向MimeMessage添加（Multipart代表附件）
                mp.addBodyPart(mbpFile);

            }

            file.removeAllElements();
            // 向Multipart添加MimeMessage
            msg.setContent(mp);
            msg.setSentDate(new Date());
            msg.saveChanges();

            // 发送邮件
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * @param args
     * @Description: 邮件测试main方法
     * @author alexli
     * @Date   2017年6月22日 上午10:40:36
     */
    public static void main(String[] args) throws Exception{
        // 配置参数

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        // 指定验证为true
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.timeout","1000");

        props.setProperty("mail.smtp.connectiontimeout", "10000");
        props.setProperty("mail.smtp.timeout", "10000");


        // 验证账号及密码，密码需要是第三方授权码
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("13570208304@163.com", "tyk19941111");
            }
        };
        Session session = Session.getInstance(props, auth);
        session.setDebug(true);
        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);
// 设置发送者
        message.setFrom(new InternetAddress("13570208304@163.com"));
// 设置发送方式与接收者
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("348727112@qq.com"));
// 设置主题
        message.setSubject("邮件发送测试");
// 设置内容
        message.setContent("hahahahhahahahhahahah", "text/html;charset=utf-8");

        Transport.send(message);
    }

}
