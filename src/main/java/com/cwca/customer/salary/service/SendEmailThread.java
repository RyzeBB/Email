package com.cwca.customer.salary.service;

import java.io.File;

public interface SendEmailThread {
    void runTask(String host,int port,String username,String password,String protocol,String[] toEmails, String formEmail, String subject, String text, File attachment);
    Runnable getNextTask(String host,int port,String username,String password,String protocol,String toEmail, String fromEmail, String subject,String text,File attachment);
}
