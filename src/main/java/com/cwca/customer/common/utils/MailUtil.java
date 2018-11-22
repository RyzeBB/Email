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
        mailSender.setHost("smtp.163.com");//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(25);//默认端口，标准的SMTP端口
        mailSender.setUsername("nxhxemail@163.com");
        mailSender.setPassword("qwe123");
        mailSender.setProtocol("smtp");
        try{
            mailSender.testConnection();
            System.out.println(host);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return mailSender;
    }
    public static void mailSendAttachment(JavaMailSender javaMailSender,String toEmail, String fromEmail, String subject, String text, File attachement) throws javax.mail.MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);//构造消息helper，第二个参数表明这个消息是multipart类型的
        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text);
        //使用Spring的FileSystemResource来加载fox.png
        FileSystemResource image = new FileSystemResource("F:\\3.xlsx");
        System.out.println(image.exists());
        helper.addAttachment("3.xlsx", image);//添加附加，第一个参数为添加到Email中附件的名称，第二个人参数是图片资源
        javaMailSender.send(message);
        System.out.println("发送完成");
    }
}
