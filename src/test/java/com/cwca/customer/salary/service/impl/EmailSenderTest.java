package com.cwca.customer.salary.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes={RootConfig.class})
@WebAppConfiguration
public class EmailSenderTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleEmail(){
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom("nxhxemail@163.com");//发件人
        message.setTo("nxhxemail@163.com");//收件人
        message.setSubject("Spring Email Test");//主题
        message.setText("hello world!!");//正文
        mailSender.send(message);
        System.out.println("邮件发送完毕");
    }

    /**
     * 发送带有附件的email
     * @throws MessagingException
     */
    @Test
    public void sendEmailWithAttachment() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);//构造消息helper，第二个参数表明这个消息是multipart类型的
        helper.setFrom("nxhxemail@163.com");
        helper.setTo("nxhxemail@163.com");
        helper.setSubject("Spring Email Test");
        helper.setText("这是一个带有附件的消息");
        //使用Spring的FileSystemResource来加载fox.png
        FileSystemResource image = new FileSystemResource("F:\\3.xlsx");
        System.out.println(image.exists());
        helper.addAttachment("3.xlsx", image);//添加附加，第一个参数为添加到Email中附件的名称，第二个人参数是图片资源
        mailSender.send(message);
        System.out.println("邮件发送完毕");
    }

}