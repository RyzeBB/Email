package com.cwca.customer.salary.controller;

import com.cwca.customer.common.web.JsonResult;
import com.cwca.customer.salary.entity.EmpInfo;
import com.cwca.customer.salary.service.EmpInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

public class TcController {

    @Resource
    private EmpInfoService empInfoService;

    @RequestMapping("listUI.do")
    public String listUI(){
        return "empinfo/empinfo_list";
    }

    @RequestMapping("editUI.do")
    public String editUI() {
        return "empinfo/empinfo_edit";
    }

    @RequestMapping("deleteUI.do")
    public String deleteUI(){
        return "empinfo/empinfo_delete";
    }
    @RequestMapping("doFindObjects.do")
    @ResponseBody
    public JsonResult findAll(String email, int pageCurrent){
        Map all = empInfoService.findAll(email, pageCurrent);
        return new JsonResult(all);
    }

    @RequestMapping("doSaveObject.do")
    @ResponseBody
    public JsonResult doSaveObject(EmpInfo entity) {
        empInfoService.insertEmpInfo(entity);
        return new JsonResult();
    }

    @RequestMapping("doFindObjectById.do")
    @ResponseBody
    public JsonResult doFindObjectById(
            Integer id) {
        EmpInfo empInfo = empInfoService.selectEmpInfoById(id);
        return new JsonResult(empInfo);
    }

    @RequestMapping("doUpdateObject.do")
    @ResponseBody
    public JsonResult doUpdateObject(
            EmpInfo entity) {
        empInfoService.updateEmpInfo(entity);
        return new JsonResult();
    }
    @RequestMapping("dodeleteObject.do")
    @ResponseBody
    public JsonResult deleteObject(Integer id){
        empInfoService.deleteEmpInfo(id);
        return new JsonResult();
    }

}
