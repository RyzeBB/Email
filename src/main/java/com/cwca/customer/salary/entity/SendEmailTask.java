package com.cwca.customer.salary.entity;

import com.cwca.customer.common.utils.MailUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;
@AllArgsConstructor
@Getter@Setter
public class SendEmailTask implements Runnable{
   private String host="";
   private int port;
   private String username;
   private String password;
   private String protocol;

   private String toEmail;
   private String fromEmail;
   private String subject;
   private String text;
   private File attchment;




   @Override
   public void run() {
      JavaMailSender javaMailSender = MailUtil.getmailSender(this.host, this.port, this.username, this.password, this.protocol);
      try{
         MailUtil.mailSendAttachment(javaMailSender,this.toEmail,this.fromEmail,this.subject,this.text,this.attchment);
      }catch (Exception e){
         throw new RuntimeException("MessagingException");
      }

   }


}