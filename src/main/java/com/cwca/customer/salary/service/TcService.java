package com.cwca.customer.salary.service;

import com.cwca.customer.salary.entity.EmpInfo;
import com.cwca.customer.salary.entity.Tc;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public interface TcService {

    int insertTc(List<Tc> tcList );
    //  void insertEmpInfos(List <T> empInfo);
    void deleteEmpInfo(Integer id);
    void updateEmpInfo(T empInfo);
    EmpInfo selectEmpInfoById(Integer id);
    Map<String,Object> findAll(String email, int pageCurrent);
}
