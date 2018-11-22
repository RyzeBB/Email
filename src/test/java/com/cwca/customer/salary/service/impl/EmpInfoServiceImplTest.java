package com.cwca.customer.salary.service.impl;

import com.cwca.customer.salary.dao.EmpInfoDao;
import com.cwca.customer.salary.entity.EmpInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mvc.xml", "/spring-mybatis.xml", "/spring-shiro.xml"})
public class EmpInfoServiceImplTest {
    @Resource
    private EmpInfoDao empInfoDao;

    @Test
    public void insertEmpInfo() {
        EmpInfo e = new EmpInfo("d","f","d","o");
        empInfoDao.insertObject(e);
    }

    @Test
    public void insertEmpInfos() {
    }

    @Test
    public void deleteEmpInfo() {
    }

    @Test
    public void updateEmpInfo() {
    }

    @Test
    public void selectEmpInfoById() {
    }

    @Test
    public void findAll() {
        empInfoDao.selectObjects(null, null, null);
    }
}