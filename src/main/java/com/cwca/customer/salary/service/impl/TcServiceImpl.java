package com.cwca.customer.salary.service.impl;

import com.cwca.customer.salary.entity.EmpInfo;
import com.cwca.customer.salary.entity.Tc;
import com.cwca.customer.salary.service.TcService;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public class TcServiceImpl implements TcService {
    @Override
    public int insertTc(List<Tc> tcList) {

        return 0;
    }

    @Override
    public void deleteEmpInfo(Integer id) {

    }

    @Override
    public void updateEmpInfo(T empInfo) {

    }

    @Override
    public EmpInfo selectEmpInfoById(Integer id) {
        return null;
    }

    @Override
    public Map<String, Object> findAll(String email, int pageCurrent) {
        return null;
    }
}
