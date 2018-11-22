package com.cwca.customer.salary.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface ExcelService {

    void uploadEmpInfo(MultipartFile mFile);
    void uploadBill(MultipartFile mFile);
    void sendEmail();
}
