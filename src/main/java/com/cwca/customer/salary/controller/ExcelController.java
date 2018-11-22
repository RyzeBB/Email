package com.cwca.customer.salary.controller;

import com.cwca.customer.common.web.JsonResult;
import com.cwca.customer.salary.service.ExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
        System.out.println(mFile.getSize());
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


}
