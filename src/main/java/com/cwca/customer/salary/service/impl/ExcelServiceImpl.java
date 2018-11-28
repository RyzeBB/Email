package com.cwca.customer.salary.service.impl;

import com.cwca.customer.common.exception.ServiceException;
import com.cwca.customer.common.utils.BillUtil;
import com.cwca.customer.common.utils.MailUtil;
import com.cwca.customer.common.utils.excel.POIExcelUtil;
import com.cwca.customer.salary.dao.BillDao;
import com.cwca.customer.salary.dao.EmpInfoDao;
import com.cwca.customer.salary.dao.TcDao;
import com.cwca.customer.salary.entity.Bill;
import com.cwca.customer.salary.entity.EmpInfo;
import com.cwca.customer.salary.entity.Records;
import com.cwca.customer.salary.entity.Tc;
import com.cwca.customer.salary.service.ExcelService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 发件人邮箱需要在个人信息中设置姓名，显示在对方的发件人信息中。不设置可能导致邮件被拒收。
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource
    private EmpInfoDao empInfoDao;
    @Resource
    private BillDao billDao;
    @Resource
    private TcDao tcDao;

    /*  @Resource
      private SendEmailThread sendEmailThread;*/
    @Override
    public void uploadEmpInfo(MultipartFile mFile) {
        if (mFile == null)
            throw new ServiceException("请选择文件后上传");
        if (mFile.isEmpty()) {
            throw new ServiceException("文件内容不能为空");
        }
        List<EmpInfo> emplist;
        //读取excel
        try {
            Workbook excelWorkbookByFile = POIExcelUtil.getExcelWorkbookByFile(mFile);
            Sheet sheet = POIExcelUtil.getSheetByNum(excelWorkbookByFile, 1);
            emplist = POIExcelUtil.getSheetDataObjectSkipHead(sheet, EmpInfo.class, BillUtil.getbillprop("00"));
        } catch (Exception e) {
            throw new ServiceException("excel解析失败,请检查文件", e);
        }

        if (emplist == null)
            throw new ServiceException("数据不能为空");
        try {
            System.out.println(mFile.getOriginalFilename().substring(0, mFile.getOriginalFilename().indexOf(".")));
            if ("01".equals(mFile.getOriginalFilename().substring(0, mFile.getOriginalFilename().indexOf(".")))) {
                empInfoDao.insertObjectsForHx(emplist);
            } else if ("02".equals(mFile.getOriginalFilename().substring(0, mFile.getOriginalFilename().indexOf(".")))) {
                empInfoDao.insertObjectsForWb(emplist);
            } else if ("03".equals(mFile.getOriginalFilename().substring(0, mFile.getOriginalFilename().indexOf(".")))) {
                empInfoDao.insertObjectsForCa(emplist);
            }else{
                throw new ServiceException("请对文件重命名后再上传");
            }
        } catch (org.springframework.dao.DuplicateKeyException e) {
            Throwable cause = e.getCause();
            if (cause instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                //com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'dd' for key 'email_unique'
                String s = e.getCause().toString();
                if ("email_unique".equals(s.substring(s.indexOf("key") + 5, s.lastIndexOf("'")))) {
                    int entry = s.indexOf("entry");
                    int fr = s.indexOf("for");
                    String trim = s.substring(entry + 5, fr).trim();
                    throw new ServiceException(trim + "已存在,请更改后重新上传");
                }
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void uploadBill(MultipartFile mFile) {
        if (mFile == null)
            throw new ServiceException("请选择文件后上传");
        if (mFile.isEmpty()) {
            throw new ServiceException("文件不能为空");
        }

        List<Bill> billlist;
        List<String> proplist = new ArrayList<>();

        //01.xlsx -->航信  02 -->外包  03 -->ca
        try {
            if ("01".equals(mFile.getOriginalFilename().substring(0, mFile.getOriginalFilename().indexOf(".")))) {
                proplist = BillUtil.getbillprop("01");
            } else if ("02".equals(mFile.getOriginalFilename().substring(0, mFile.getOriginalFilename().indexOf(".")))) {
                proplist = BillUtil.getbillprop("02");
            } else if ("03".equals(mFile.getOriginalFilename().substring(0, mFile.getOriginalFilename().indexOf(".")))) {
                proplist = BillUtil.getbillprop("03");
            }
            Workbook excelWorkbookByFile = POIExcelUtil.getExcelWorkbookByFile(mFile);
            Sheet sheet = POIExcelUtil.getSheetByNum(excelWorkbookByFile, 1);
            billlist = POIExcelUtil.getSheetDataObjectSkipHead(sheet, Bill.class, proplist);
        } catch (Exception e) {
            throw new ServiceException("excel解析失败,请检查文件", e);
        }
        if (billlist == null)
            throw new ServiceException("数据不能为空");
        try {

            billDao.insertObjects(billlist);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            System.out.println(e.getMessage());
            Throwable cause = e.getCause();
            if (cause instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                //com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'dd' for key 'email_unique'
                String s = e.getCause().toString();
                if ("zydm_unique".equals(s.substring(s.indexOf("key") + 5, s.lastIndexOf("'")))) {
                    int entry = s.indexOf("entry");
                    int fr = s.indexOf("for");
                    String trim = s.substring(entry + 5, fr).trim();
                    throw new ServiceException("职员代码" + trim + "已存在,请更改后重新上传");
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    public void uploadTc(MultipartFile mFile) {
        if (mFile == null)
            throw new ServiceException("请选择文件后上传");
        if (mFile.isEmpty()) {
            throw new ServiceException("文件不能为空");
        }
        List<Tc> tcList;
        try{
            Workbook excelWorkbookByFile = POIExcelUtil.getExcelWorkbookByFile(mFile);
            Sheet sheet = POIExcelUtil.getSheetByNum(excelWorkbookByFile, 1);
            tcList = POIExcelUtil.getSheetDataObjectSkipHead(sheet, Tc.class, BillUtil.getbillprop("tc"));
        }catch (Exception e){
            throw new ServiceException("excel解析失败,请检查文件", e);
        }

        try {
            tcDao.truncateTc();
            tcDao.insertObjects(tcList);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            System.out.println(e.getMessage());
            Throwable cause = e.getCause();
            if (cause instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                //com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'dd' for key 'email_unique'
                String s = e.getCause().toString();
                if ("email_unique".equals(s.substring(s.indexOf("key") + 5, s.lastIndexOf("'")))) {
                    int entry = s.indexOf("entry");
                    int fr = s.indexOf("for");
                    String trim = s.substring(entry + 5, fr).trim();
                    throw new ServiceException("邮箱" + trim + "已存在,请更改后重新上传");
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("数据插入失败");
        }
    }


    @Override
    public Records sendEmail(String username, String password, HttpServletRequest request,String sendType) {
        if (StringUtils.isEmpty(username)) {
            throw new ServiceException("请输入邮箱");
        }
        if (StringUtils.isEmpty(password)) {
            throw new ServiceException("请输入授权码");
        }
        //______
        List<EmpInfo> empInfos = empInfoDao.selectObjects(null, null, null);
        Records records = new Records();
        records.setEmpTotal(empInfos.size());
        List<String> failReasonlist = new ArrayList<>();

        for (EmpInfo ef :
                empInfos) {
            //针对不同组织,根据每个人的查询结果，生成不同的工资条,这里是一个人的。为了参数统一，add到list
            List<Bill> bills = billDao.selectObject(ef.getEmpcode());
            try {
                FileSystemResource attachment = BillUtil.getBill(request, ef.getOrgncode(), bills,sendType);
                JavaMailSender javaMailSender = MailUtil.getmailSender(MailUtil.host, MailUtil.port, username, password, MailUtil.protocol);
                MailUtil.mailSendAttachment(javaMailSender, ef.getEmail(), username, BillUtil.gzSubject, BillUtil.gzText, attachment, BillUtil.gzAttachementname);
            } catch (Exception e) {
                failReasonlist.add("【"+ef.getEmail() + "-->" + e.getMessage() + ";"+"】");
                continue;
            }
        }
        records.setNoSendTotal(failReasonlist.size());
        return records;
      }


    @Override
    public Records sendTcEmail(String username, String password, HttpServletRequest request,String sendType) {
        if (StringUtils.isEmpty(username)) {
            throw new ServiceException("请输入邮箱");
        }
        if (StringUtils.isEmpty(password)) {
            throw new ServiceException("请输入授权码");
        }
        List<Tc> empInfos = tcDao.selectObjects();
        Records records = new Records();
        records.setEmpTotal(empInfos.size());

        List<String> failReasonlist = new ArrayList<>();
        for (Tc ef :
                empInfos) {
            //针对不同组织生成不同的工资条
            List<Tc> tcList  = new ArrayList<>();
            tcList.add(ef);
            try {
                FileSystemResource attachment = BillUtil.getBill(request, "tc", tcList,sendType);
                JavaMailSender javaMailSender = MailUtil.getmailSender(MailUtil.host, MailUtil.port, username, password, MailUtil.protocol);
                MailUtil.mailSendAttachment(javaMailSender, ef.getYx(), username, BillUtil.tcSubject, BillUtil.tcText, attachment, BillUtil.tcAttachementname);
            } catch (Exception e) {
                failReasonlist.add("【"+ef.getYx() + "-->" + e.getMessage() + ";"+"】");
                records.setFailReason(failReasonlist);
                continue;
            }
        }
        records.setNoSendTotal(failReasonlist.size());
        return records;
    }

    @Override
    public void gzFileDelete(boolean checked, HttpServletRequest request) {
        if (!checked) {
            return;
        }
        String realPath = request.getSession().getServletContext().getRealPath(BillUtil.gzwebpath);
        try {
            FileUtils.cleanDirectory(new File(realPath));
            billDao.truncateBill();
        } catch (java.io.IOException e) {
            throw new ServiceException("工资数据删除失败");
        } catch (Exception e){
            throw new ServiceException("数据表清空失败");
        }
    }

    @Override
    public void tcFileDelete(boolean checked, HttpServletRequest request) {
        if (!checked) {
            return;
        }
        String realPath = request.getSession().getServletContext().getRealPath(BillUtil.tcwebpath);
        try {
            FileUtils.cleanDirectory(new File(realPath));
            tcDao.truncateTc();
        } catch (java.io.IOException e) {
            throw new ServiceException("工资数据删除失败");
        } catch (Exception e){
            throw new ServiceException("数据表清空失败");
        }
    }


}
