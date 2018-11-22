package com.cwca.customer.salary.service.impl;

import com.cwca.customer.salary.service.ExcelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mvc.xml", "/spring-mybatis.xml", "/spring-shiro.xml"})
public class ExcelServiceImplTest {
    @Resource
    private ExcelService excelService;
    @Test
    public void uploadEmpInfo() {
    }

    @Test
    public void sendEmail() {
        excelService.sendEmail();
    }
}