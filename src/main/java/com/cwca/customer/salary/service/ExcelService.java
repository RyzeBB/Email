package com.cwca.customer.salary.service;

import com.cwca.customer.salary.entity.Records;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ExcelService {

    void uploadEmpInfo(MultipartFile mFile);
    void uploadBill(MultipartFile mFile);
    void uploadTc(MultipartFile mFile);
    Records sendEmail(String username, String password, HttpServletRequest requestd,String sendType);
    Records sendTcEmail(String username, String password, HttpServletRequest requestd,String sendType);
    void gzFileDelete(boolean checked,HttpServletRequest request);
    void tcFileDelete(boolean checked,HttpServletRequest request);
}
