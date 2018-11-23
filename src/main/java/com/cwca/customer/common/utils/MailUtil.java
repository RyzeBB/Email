package com.cwca.customer.common.utils;

import org.springframework.core.io.FileSystemResource;

import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

public class MailUtil {
    public static JavaMailSender getmailSender(String host, int port, String username, String password, String protocol) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(port);//默认端口，标准的SMTP端口
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setProtocol(protocol);
      /*  try{
            mailSender.testConnection();
            System.out.println(host);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }*/
        return mailSender;
    }
    public static void mailSendAttachment(JavaMailSender javaMailSender,String toEmail, String fromEmail, String subject, String text, FileSystemResource attachement,String attachmentname) throws javax.mail.MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);//构造消息helper，第二个参数表明这个消息是multipart类型的
        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text);
        //使用Spring的FileSystemResource来加载fox.png
        helper.addAttachment(attachmentname, attachement);//添加附加，第一个参数为添加到Email中附件的名称，第二个人参数是图片资源
        javaMailSender.send(message);
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
