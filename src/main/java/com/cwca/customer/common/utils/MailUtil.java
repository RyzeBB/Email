package com.cwca.customer.common.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

public class MailUtil {
    public static final String host = "smtp.163.com";
    public static final int port = 25;
    public static final String protocol = "smtp";
    public static JavaMailSender getmailSender(String host, int port, String username, String password, String protocol){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(port);//默认端口，标准的SMTP端口
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setProtocol(protocol);
       /* try{
            mailSender.testConnection();
        }catch (Exception e){
            throw new RuntimeException("邮箱认证失败");
        }*/
        return mailSender;
    }
    public static void mailSendAttachment(JavaMailSender javaMailSender,String toEmail, String fromEmail, String subject, String text, FileSystemResource attachement,String attachmentname)throws MailAuthenticationException ,javax.mail.MessagingException{
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);//构造消息helper，第二个参数表明这个消息是multipart类型的
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(text);
            //使用Spring的FileSystemResource来加载fox.png
            helper.addAttachment(attachmentname, attachement);//添加附加，第一个参数为添加到Email中附件的名称，第二个人参数是图片资源
            javaMailSender.send(message);
        }catch (org.springframework.mail.MailAuthenticationException e){

//org.springframework.mail.MailAuthenticationException: Authentication failed; nested exception is javax.mail.AuthenticationFailedException: 535 Error: authentication failed
/*org.springframework.mail.MailSendException: Failed messages: javax.mail.SendFailedException: Invalid Addresses;
  nested exception is:
	com.sun.mail.smtp.SMTPAddressFailedException: 550 Invalid User: 1232000000
; message exceptions (1) are:
Failed message 1: javax.mail.SendFailedException: Invalid Addresses;
  nested exception is:
	com.sun.mail.smtp.SMTPAddressFailedException: 550 Invalid User: 1232000000
*///service:Failed messages: javax.mail.SendFailedException: Invalid Addresses;
 //  nested exception is://	com.sun.mail.smtp.SMTPAddressFailedException: 550 User not found: zhahjsjfuuf0sfdsl@163.com
            //com.sun.mail.smtp.SMTPAddressFailedException

            //这个e是spring 的异常
            if(e.getMessage().contains("535") ){
                throw new MailAuthenticationException("邮箱认证失败");
            }
        }catch (org.springframework.mail.MailSendException e){
            if(e.getMessage().contains("550")){
                throw new MailSendException("地址无效");
            }
            throw new MailSendException("发送异常");
        }catch (MailParseException e){
            throw new MailParseException("邮件解析异常");
        }catch (MailPreparationException e){
            throw new MailPreparationException("Could not prepare mail");
        }catch (javax.mail.internet.AddressException e){
            if(e.getMessage().contains("illegal character")){
                throw new AddressException("地址包含非法字符");
            }
        }catch (Exception e){
            //javax.mail.internet.AddressException: Local address contains illegal character in string ``22[][][][][]1@qq.com''
            System.out.println(e.getMessage());
            throw e;
        }




        /*catch (com.sun.mail.smtp.SMTPAddressFailedException e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
           *//* if(e.getCause().toString().contains("550")){
                throw e;
            }*//*
        }catch (Exception e){

        }*/
        System.out.println("发送完成");
    }

    public static void main(String[] args) {
        int[] s = {1,2,3,4,5,6,7};
        for (int t:
             s) {

            if(t==4){
                continue;
            }

            System.out.println(t);
        }
    }
}
