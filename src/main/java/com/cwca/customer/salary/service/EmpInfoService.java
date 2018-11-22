package com.cwca.customer.salary.service;

import com.cwca.customer.salary.entity.EmpInfo;

import java.util.List;
import java.util.Map;

public interface EmpInfoService<T> {
    //empinfo handle
    int insertEmpInfo(EmpInfo empInfo);
  //  void insertEmpInfos(List <T> empInfo);
    void deleteEmpInfo(Integer id);
    void updateEmpInfo(T empInfo);
    EmpInfo selectEmpInfoById(Integer id);
    Map<String,Object> findAll(String email,int pageCurrent);

    //excel upload
  //  void uploadAndInsert(MultipartFile mFile, HttpServletRequest request);
}
