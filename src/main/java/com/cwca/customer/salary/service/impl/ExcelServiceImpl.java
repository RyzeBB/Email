package com.cwca.customer.salary.service.impl;

import com.cwca.customer.common.exception.ServiceException;
import com.cwca.customer.common.utils.BillUtil;
import com.cwca.customer.common.utils.MailUtil;
import com.cwca.customer.common.utils.excel.POIExcelUtil;
import com.cwca.customer.common.utils.time.DateUtil;
import com.cwca.customer.salary.dao.BillDao;
import com.cwca.customer.salary.dao.EmpInfoDao;
import com.cwca.customer.salary.entity.Bill;
import com.cwca.customer.salary.entity.EmpInfo;
import com.cwca.customer.salary.entity.Records;
import com.cwca.customer.salary.service.ExcelService;
import com.cwca.customer.salary.service.SendEmailThread;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource
    private EmpInfoDao empInfoDao;
    @Resource
    private BillDao billDao;
  /*  @Resource
    private SendEmailThread sendEmailThread;*/
    @Override
    public void uploadEmpInfo(MultipartFile mFile) {
        if (mFile.isEmpty()) {
            throw new ServiceException("文件不能为空");
        }
        if(mFile == null)
            throw new ServiceException("请选择文件后上传");
        List<EmpInfo> emplist;

        //读取excel
        try{
            Workbook excelWorkbookByFile = POIExcelUtil.getExcelWorkbookByFile(mFile);
            Sheet sheet = POIExcelUtil.getSheetByNum(excelWorkbookByFile, 1);
            emplist = POIExcelUtil.getSheetDataObject(sheet,EmpInfo.class,BillUtil.getbillprop("00"));
        }catch (Exception e){
            throw new ServiceException("excel解析失败,请检查文件",e);
        }

        if(emplist==null)
            throw new ServiceException("数据不能为空");
        try{
            if("01".equals(mFile.getOriginalFilename().substring(0,mFile.getOriginalFilename().indexOf(".")))){
                empInfoDao.insertObjectsForHx(emplist);
            }else if ("02".equals(mFile.getOriginalFilename().substring(0,mFile.getOriginalFilename().indexOf(".")))){
                empInfoDao.insertObjectsForWb(emplist);
            }else if("03".equals(mFile.getOriginalFilename().substring(0,mFile.getOriginalFilename().indexOf(".")))){
                empInfoDao.insertObjectsForCa(emplist);
            }
        }catch (org.springframework.dao.DuplicateKeyException e){
            Throwable cause = e.getCause();
            if(cause instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException){
                //com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'dd' for key 'email_unique'
                String s = e.getCause().toString();
                if("email_unique".equals(s.substring(s.indexOf("key")+5,s.lastIndexOf("'")))){
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

        List<Bill> billlist = new ArrayList<>();
        List<String> proplist = new ArrayList <>();

       /* String name = mFile.getName();
        String originalFilename = mFile.getOriginalFilename();
        int i = originalFilename.indexOf(".");
        String substring = originalFilename.substring(0, i);*/
        //01.xlsx -->航信  02 -->外包  03 -->ca
        try{
            if("01".equals(mFile.getOriginalFilename().substring(0,mFile.getOriginalFilename().indexOf(".")))){
                proplist = BillUtil.getbillprop("01");
            }else if ("02".equals(mFile.getOriginalFilename().substring(0,mFile.getOriginalFilename().indexOf(".")))){
                proplist = BillUtil.getbillprop("02");
            }else if("03".equals(mFile.getOriginalFilename().substring(0,mFile.getOriginalFilename().indexOf(".")))){
                proplist= BillUtil.getbillprop("03");
            }
            Workbook excelWorkbookByFile = POIExcelUtil.getExcelWorkbookByFile(mFile);
            Sheet sheet = POIExcelUtil.getSheetByNum(excelWorkbookByFile, 1);
            billlist = POIExcelUtil.getSheetDataObjectSkipHead(sheet,Bill.class,proplist);
        }catch (Exception e){
            throw new ServiceException("excel解析失败,请检查文件",e);
        }
        if(billlist==null)
            throw new ServiceException("数据不能为空");
        try{
            billDao.insertObjects(billlist);
        }catch (org.springframework.dao.DuplicateKeyException e){
            System.out.println(e.getMessage());
            Throwable cause = e.getCause();
            if(cause instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException){
                //com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'dd' for key 'email_unique'
                String s = e.getCause().toString();
                if("zydm_unique".equals(s.substring(s.indexOf("key")+5,s.lastIndexOf("'")))){
                    int entry = s.indexOf("entry");
                    int fr = s.indexOf("for");
                    String trim = s.substring(entry + 5, fr).trim();
                    throw new ServiceException("职员代码"+trim+"已存在,请更改后重新上传");
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
    public Records sendEmail(String username, String password, HttpServletRequest request) {
        if(StringUtils.isEmpty(username)){
            throw new ServiceException("请输入邮箱");
        }
        if(StringUtils.isEmpty(password)){
            throw new ServiceException("请输入授权码");
        }
        String host = "smtp.163.com";
        int port = 25;
        String protocol = "smtp";
        String uname = username;
        String pwd = password;
        String subject = ""+DateUtil.getCurrectYear()+"年"+(DateUtil.getCurrectMonth()-1)+"月"+"工资条";
        String text = "长得太美，就不要想的太美";
        String attachementname = "工资条";
        //______
        List<EmpInfo> empInfos = empInfoDao.selectObjects(null, null, null);
        Records records = new Records();
        records.setEmpTotal(empInfos.size());

        int noSendTotal = 0;
        List<String> noSendList = new ArrayList<>();
        //______________
        String fromEmail = username;
        for (EmpInfo ef:
             empInfos) {

            //针对不同组织生成不同的工资条
            List<Bill> bills = billDao.selectObject(ef.getEmpcode());
            try{
                FileSystemResource attachment = BillUtil.getBill(ef.getEmail(), request, ef.getOrgncode(), bills);
                JavaMailSender javaMailSender = MailUtil.getmailSender(host, port, uname, pwd, protocol);
                MailUtil.mailSendAttachment(javaMailSender,ef.getEmail(),fromEmail,subject,text,attachment,attachementname);
            }catch (Exception e){
                System.out.println("发送中出现错误:"+e.getMessage());
               // throw new ServiceException("发送失败"+
                noSendList.add(ef.getEmpname());
                continue;
            }
        }
        return records;
       // sendEmailThread.runTask(host,port,username,password,protocol,toEmails,fromEmail,subject,text,attachement);
    }

}
