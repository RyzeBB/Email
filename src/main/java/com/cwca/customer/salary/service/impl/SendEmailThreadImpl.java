package com.cwca.customer.salary.service.impl;

import com.cwca.customer.salary.entity.SendEmailTask;
import com.cwca.customer.salary.service.SendEmailThread;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
@Service
public class SendEmailThreadImpl implements SendEmailThread{
    private static Logger log = LoggerFactory.getLogger(SendEmailThreadImpl.class);
    //@Setter@Getter
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Override
    public void runTask(String host,int port,String username,String password,String protocol,String[] toEmails, String fromEmail, String subject, String text, File attachment) {
        if (threadPoolTaskExecutor == null) {
            log.debug("the poolTaskExecutor is null");
        } else {
            System.out.println(attachment.getName());
            if (toEmails != null && toEmails.length> 0) {
                if (toEmails.length < threadPoolTaskExecutor.getCorePoolSize())//当任务数小于创建的线程
                    threadPoolTaskExecutor.setCorePoolSize(toEmails.length);
                for (String toEmail : toEmails) {
                    try {
                        threadPoolTaskExecutor.execute(getNextTask(host,port,username,password,protocol,toEmail,fromEmail,subject,text,attachment));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public Runnable getNextTask(String host,int port,String username,String password,String protocol,String toEmail, String fromEmail, String subject,String text,File attachment) {
        return new SendEmailTask(host,port,username,password,protocol,toEmail,fromEmail,subject,text,attachment);
    }
/*
    public static void main(String[] args) {
        SendEmailThread st = new SendEmailThread("th","d","107968518@qq.com","nxhxemail@163.com");
        st.runTask();
    }*/
}