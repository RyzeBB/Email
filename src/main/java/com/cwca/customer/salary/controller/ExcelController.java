package com.cwca.customer.salary.controller;

import com.cwca.customer.common.web.JsonResult;
import com.cwca.customer.salary.entity.Records;
import com.cwca.customer.salary.service.ExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/attachment/")
public class ExcelController {

    @Resource
    private ExcelService excelService;
    @RequestMapping("listUI.do")
    public String listUI(){
        return "attachment/attachment";
    }
    @RequestMapping("empinfo.do")
    @ResponseBody
    public JsonResult uploadEmpInfo(MultipartFile mFile){
        excelService.uploadEmpInfo(mFile);
        return new JsonResult();
    }


    @RequestMapping("salary.do")
    @ResponseBody
    public JsonResult uploadBill(MultipartFile mFile){
      //  System.out.println(mFile.getSize());
        excelService.uploadBill(mFile);
        return new JsonResult();
    }
    @RequestMapping("tc.do")
    @ResponseBody
    public JsonResult uploadTc(MultipartFile mFile){
        //  System.out.println(mFile.getSize());
        excelService.uploadTc(mFile);
        return new JsonResult();
    }

    @RequestMapping("send.do")
    @ResponseBody
    public JsonResult sendEmail(String username, String password, HttpServletRequest request,String sendType){
        Records records = excelService.sendEmail(username, password, request,sendType);
        return new JsonResult(records);
    }

    @RequestMapping("sendtc.do")
    @ResponseBody
    public JsonResult sendTcEmail(String username, String password, HttpServletRequest request, String sendType){
        Records records = excelService.sendTcEmail(username, password, request,sendType);
        return new JsonResult(records);
    }

    @RequestMapping("gzFleDelete.do")
    @ResponseBody
    public JsonResult gzFileDelete(boolean checked,HttpServletRequest request){
        excelService.gzFileDelete(checked,request);
        return new JsonResult();
    }
    @RequestMapping("tcFleDelete.do")
    @ResponseBody
    public JsonResult tcFileDelete(boolean checked,HttpServletRequest request){
        excelService.tcFileDelete(checked,request);
        return new JsonResult();
    }
}
