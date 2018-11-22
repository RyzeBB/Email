package com.cwca.customer.salary.service.impl;

import com.cwca.customer.common.exception.ServiceException;
import com.cwca.customer.common.utils.BillUtil;
import com.cwca.customer.common.utils.excel.POIExcelUtil;
import com.cwca.customer.salary.dao.BillDao;
import com.cwca.customer.salary.dao.EmpInfoDao;
import com.cwca.customer.salary.entity.Bill;
import com.cwca.customer.salary.entity.EmpInfo;
import com.cwca.customer.salary.service.ExcelService;
import com.cwca.customer.salary.service.SendEmailThread;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource
    private EmpInfoDao empInfoDao;
    @Resource
    private BillDao billDao;
    @Resource
    private SendEmailThread sendEmailThread;
    @Override
    public void uploadEmpInfo(MultipartFile mFile) {
        if (mFile.isEmpty()) {
            throw new ServiceException("文件不能为空");
        }
        if(mFile == null)
            throw new ServiceException("请选择文件后上传");
        List<EmpInfo> emplist;
        //和表字段中的顺序一致
        List<String> proplist = new ArrayList <>();
        proplist.add("empcode");
        proplist.add("empname");
        proplist.add("departname");
        proplist.add("email");
        //读取excel
        try{
            Workbook excelWorkbookByFile = POIExcelUtil.getExcelWorkbookByFile(mFile);
            Sheet sheet = POIExcelUtil.getSheetByNum(excelWorkbookByFile, 1);
            emplist = POIExcelUtil.getSheetDataObject(sheet,EmpInfo.class,proplist);
        }catch (Exception e){
            throw new ServiceException("excel解析失败,请检查文件");
        }

        if(emplist==null)
            throw new ServiceException("数据不能为空");
        try{
            empInfoDao.insertObjects(emplist);
        }catch (org.springframework.dao.DuplicateKeyException e){
            Throwable cause = e.getCause();
            if(cause instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException){
                //com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'dd' for key 'email_unique'
                String s = e.getCause().toString();
                if("email_unique".equals(s.substring(s.indexOf("key")+3))){
                    int entry = s.indexOf("entry");
                    int fr = s.indexOf("for");
                    String trim = s.substring(entry + 5, fr).trim();
                    throw new ServiceException(trim+"已存在,请更改后重新上传");
                }
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    public void uploadBill(MultipartFile mFile) {
        if (mFile.isEmpty()) {
            throw new ServiceException("文件不能为空");
        }
        if(mFile == null)
            throw new ServiceException("请选择文件后上传");

        List<Bill> billlist = new ArrayList <>();
        List<String> proplist = new ArrayList <>();
        int d = mFile.getName().indexOf(".");
        System.out.println(d);
        String ss = mFile.getName().substring(0,mFile.getName().indexOf("."));
        System.out.println(ss);
        //01.xlsx -->航信  02 -->外包  03 -->ca
        try{
            if("01".equals(mFile.getName().substring(0,mFile.getName().indexOf(".")))){
                proplist = BillUtil.getHxbill();
            }else if ("02".equals(mFile.getName().substring(0,mFile.getName().indexOf(".")))){
                proplist = BillUtil.getWbbill();
            }else if("03".equals(mFile.getName().substring(0,mFile.getName().indexOf(".")))){
                proplist= BillUtil.getCabill();
            }
            Workbook excelWorkbookByFile = POIExcelUtil.getExcelWorkbookByFile(mFile);
            Sheet sheet = POIExcelUtil.getSheetByNum(excelWorkbookByFile, 1);
            billlist = POIExcelUtil.getSheetDataObject(sheet,Bill.class,proplist);
        }catch (Exception e){
            throw new ServiceException("excel解析失败,请检查文件");
        }

        if(billlist==null)
            throw new ServiceException("数据不能为空");
        try{
            billDao.insertObjects(billlist);
        }catch (org.springframework.dao.DuplicateKeyException e){
            Throwable cause = e.getCause();
            if(cause instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException){
                //com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'dd' for key 'email_unique'
                String s = e.getCause().toString();
                if("empcode_unique".equals(s.substring(s.indexOf("key")+3))){
                    int entry = s.indexOf("entry");
                    int fr = s.indexOf("for");
                    String trim = s.substring(entry + 5, fr).trim();
                    throw new ServiceException(trim+"已存在,请更改后重新上传");
                }
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            throw new ServiceException("数据插入失败");
        }
    }

    //String host,int port,String username,String password,String protocol,
// String[] toEmails, String formEmail, String subject, String text, File attachment
    @Override
    public void sendEmail() {
        String host = "smtp.163.com";
        int port = 25;
        String username = "nxhxemail@163.com";
        String password = "qwe123";
        String protocol = "smtp";
        String toEmails[] = {"1079684518@qq.com","nxhxemail@163.com"};
        String fromEmail = "nxhxemail@163.com";
        String subject = "Spring thead email";
        String text = "test";
        File attachement = new File("f:/3.xlsx");
        System.out.println(attachement.getName());
        System.out.println(attachement.exists());

        sendEmailThread.runTask(host,port,username,password,protocol,toEmails,fromEmail,subject,text,attachement);
    }

}
