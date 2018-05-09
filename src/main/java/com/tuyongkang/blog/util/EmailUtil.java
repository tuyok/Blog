package com.tuyongkang.blog.util;

import com.tuyongkang.blog.exception.BusinessException;
import com.tuyongkang.blog.util.Vo.EmailAttachmentVo;
import com.tuyongkang.blog.util.Vo.EmailInfoVo;
import com.tuyongkang.blog.util.Vo.MailAccount;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 邮件发送工具类
 */
public class EmailUtil {

    private static final String MAIL_SMTP_HOST = "smtp.exmail.qq.com";
    private static final String MAIL_SMTP_PORT = "465";

    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    /***
     * 提供外部调用的方法
     * @param mailInfo
     * @return
     */
    public static boolean sendMail(EmailInfoVo mailInfo) {
        try {
            sendMailWithEx(mailInfo);
        }
        catch (Exception ex) {
            logger.warn("发送邮件时出现异常：{}", ex);
            return false;
        }
        return true;
    }

    /**
     * 处理异常的发送邮件封装
     * @param mailInfo
     */
    public static void sendMailWithEx(EmailInfoVo mailInfo) {
        checkParams(mailInfo); //参数检查
        logger.info("邮件发送参数: 主题 [{}]，发件人 [{}]，密码 [{}]，收件人 [{}]，发件人昵称 [{}]，smtpServer [{}]，smtpPort [{}]，邮件内容 [{}]，附件个数 [{}]",
                new Object[]{mailInfo.getSubject(), mailInfo.getUserName(), mailInfo.getPassword(),
                        Arrays.asList(mailInfo.getTos()), mailInfo.getSendName(),
                        mailInfo.getSmtpServer(), mailInfo.getSmtpPort(),
                        mailInfo.getContent(),
                        mailInfo.getAttachmentList() != null ? mailInfo.getAttachmentList().size() : 0}
        );
        mailInfo.setFrom(mailInfo.getUserName());
        if (StringUtils.isBlank(mailInfo.getSendName())) {
            mailInfo.setSendName("技术部智能机器人");
        }
        MailAccount account = new MailAccount(mailInfo.getUserName(), mailInfo.getPassword(),
                StringUtils.isBlank(mailInfo.getSmtpServer()) ? MAIL_SMTP_HOST : mailInfo.getSmtpServer(),
                StringUtils.isBlank(mailInfo.getSmtpPort()) ? MAIL_SMTP_PORT : mailInfo.getSmtpPort(),
                true);
        try {
            sendMail(mailInfo, account, false);  //false不用debug模式发送
        } catch (UnsupportedEncodingException e) {
            logger.error("邮件发送出错：", e);
            throw new BusinessException(e.getClass().getSimpleName() + ": " + e.getMessage());
        } catch (SendFailedException e) {
            //如果发送失败，找出发送失败的邮箱并跳过，只发送有效的邮箱
            //取出失效的邮箱地址,日志记录
            Address[] inVaild = e.getInvalidAddresses();
            if (inVaild != null) {
                for (Address address : inVaild) {
                    logger.error("无效的邮箱:"+address);
                }
            }
            //取出有效且未发送的地址,重新发送一次
            Address[] vaild = e.getValidUnsentAddresses();
            if (vaild != null && vaild.length > 0) {
                String[] new_emails = address2emails(vaild);
                mailInfo.setTos(new_emails);
                try {
                    sendMail(mailInfo, account, false);  //false不用debug模式发送
                } catch (Exception e1) {
                    logger.error("重新发送邮件出错:", e1);
                    throw new BusinessException(e1.getClass().getSimpleName() + ": " + e1.getMessage());
                }
            }
        } catch (MessagingException |IOException e) {
            logger.error("邮件发送出错：", e);
            throw new BusinessException(e.getClass().getSimpleName() + ": " + e.getMessage());
        }

    }

    /**
     * 底部发送邮件方法不直接对外调用
     * @param mailInfo 邮件信息
     * @param mailAccount 邮箱账号地址信息
     * @param debug 是否debug打印出发送过程
     *
     * @throws SendFailedException
     * @throws MessagingException
     * @throws IOException
     */
    private static void sendMail(EmailInfoVo mailInfo, MailAccount mailAccount, boolean debug)
            throws MessagingException, IOException {

        final String userName = mailAccount.getUserName();
        final String password = mailAccount.getPassword();

        String mail_transport_protocol = "smtp";
        Properties props = new Properties();
        if (debug) props.setProperty("mail.debug", "true"); // 开启debug调试
        props.setProperty("mail.transport.protocol", mail_transport_protocol);  // 发送邮件协议名称
        props.setProperty("mail.smtp.auth", "true");                            // 发送服务器需要身份验证
        props.setProperty("mail.smtp.host", mailAccount.getMail_smtp_host());   // 发送邮件服务器

        // 端口
        String mail_smtp_port = mailAccount.getMail_smtp_port();
        if (mail_smtp_port != null && mail_smtp_port.length() > 0) {
            props.setProperty("mail.smtp.port", mail_smtp_port);
        }

        // 使用ssl
        if (mailAccount.isSsl()) {
            String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            props.setProperty("mail.smtp.socketFactory.port", mail_smtp_port);
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.ssl.enable", "true");
        }

        // 设置环境信息
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(userName, password);
            }
        });

        // 定义邮件信息
        Message msg = new MimeMessage(session);
        msg.setSubject(encodeText(mailInfo.getSubject()));                                      // 邮件主题
        msg.setFrom(new InternetAddress(mailInfo.getFrom(), mailInfo.getSendName(), "UTF-8"));  // 发件人
        msg.setRecipients(Message.RecipientType.TO, emails2address(mailInfo.getTos()));         // 收件人
        msg.setSentDate(new Date());                                                            // 发送时间

        // 邮件内容+附件
        MimeMultipart partList = new MimeMultipart();
        msg.setContent(partList);

        // 邮件内容
        MimeBodyPart contentPart = new MimeBodyPart();
        contentPart.setContent((mailInfo.getContent() == null) ? "" : mailInfo.getContent(),"text/html;charset=utf-8");
        partList.addBodyPart(contentPart);

        // 附件
        List<File> attachFileList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(mailInfo.getAttachmentList())) {
            for (EmailAttachmentVo attach : mailInfo.getAttachmentList()) {
                // 将附件内容写入临时文件
                File attachFile = createFile(attach);
                attachFileList.add(attachFile);

                MimeBodyPart attachPart = new MimeBodyPart();
                attachPart.attachFile(attachFile);
                if (!StringUtils.isBlank(attach.getFileName())) {
                    attachPart.setFileName(encodeText(attach.getFileName()));
                }
                else {
                    attachPart.setFileName(encodeText(attachFile.getName()));
                }
                partList.addBodyPart(attachPart);
            }
        }

        // 发送邮件
        Transport.send(msg);

        // 删除临时文件
        if (!CollectionUtils.isEmpty(attachFileList)) {
            deleteFile(attachFileList);
        }
    }

    /**
     * 参数检查
     * @param mailInfo
     */
    private static void checkParams(EmailInfoVo mailInfo) {
        if (mailInfo==null) {
            logger.error("参数对象为null!");
            throw new RuntimeException("EmailInfoVo不能为null");
        }
        if (StringUtils.isBlank(mailInfo.getUserName())) {
            logger.error("发送邮箱为null!");
            throw new RuntimeException("发送邮箱为null");
        }
        if (mailInfo.getTos() == null || mailInfo.getTos().length < 1) {
            logger.error("收件人邮箱列表为空!");
            throw new RuntimeException("收件人邮箱列表为空");
        }
        if (StringUtils.isBlank(mailInfo.getPassword())) {
            logger.error("发送邮箱密码异常!");
            throw new RuntimeException("发送邮箱密码异常");
        }
        if (!StringUtils.isBlank(mailInfo.getSmtpPort())) {
            int smtpPort;
            try {
                smtpPort = Integer.valueOf(mailInfo.getSmtpPort());
                if (!(0 <= smtpPort && smtpPort <= 65535)) {
                    throw new RuntimeException();
                }
            }
            catch (Exception ex) {
                throw new RuntimeException("发送服务器SMTP端口错误");
            }
        }
    }

    /**
     * 邮件Address数组转为字符串的邮箱数组
     * @param address
     * @return
     */
    private static String[] address2emails(Address[] address) {
        if(address == null || address.length == 0){
            return new String[]{};
        }

        String[] emails = new String[address.length];
        for (int i = 0; i < address.length; ++i) {
            emails[i] = address[i].toString();
        }
        return emails;
    }

    /**
     * 邮件字符串数组转成互联网地址数组
     * @param emails
     * @return
     * @throws AddressException
     */
    private static Address[] emails2address(String[] emails) throws AddressException {
        if (emails == null || emails.length == 0) {
            return new Address[]{};
        }

        Address[] address = new Address[emails.length];
        for (int i = 0; i < emails.length; ++i) {
            address[i] = new InternetAddress(emails[i]);
        }
        return address;
    }

    /**
     * 防止发送的邮件出现乱码
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String encodeText(String text) throws UnsupportedEncodingException {
        return MimeUtility.encodeText(text, "UTF-8", "B");
    }

    /**
     * 将一个 EmailAttachmentVo 转换成一个临时文件
     * @param attach
     * @return
     * @throws IOException
     */
    private static File createFile(EmailAttachmentVo attach) throws IOException {
        File attachFile = File.createTempFile("message-email.", ".attach");
        try (FileOutputStream out = new FileOutputStream(attachFile)) {
            out.write(attach.getFileContent());
            out.flush();
            return attachFile;
        }catch(Exception ex){
            return null;
        }
    }

    /**
     * 删除单个文件
     * @param file
     */
    private static void deleteFile(File file) {
        if (file != null) {
            try {
                file.delete();
            }
            catch (Exception ex) {}
        }
    }

    /**
     * 删除多个文件
     * @param fileList
     */
    private static void deleteFile(List<File> fileList) {
        if (!CollectionUtils.isEmpty(fileList)) {
            for (File file : fileList) {
                deleteFile(file);
            }
        }
    }

}
