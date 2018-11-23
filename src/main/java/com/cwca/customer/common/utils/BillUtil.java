package com.cwca.customer.common.utils;

import com.cwca.customer.common.utils.excel.ExcelExportSXXSSF;
import org.springframework.core.io.FileSystemResource;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillUtil {
    public static final String[] hxbill = {"zydm", "zyxm", "bmmc", "jbgz", "gzjxgzjs", "gzjxkhxs", "gzjx", "yjjxgzjs", "yjjxkhxs"
            , "yjjx", "tc", "jbf", "kqkk", "flf", "jtf", "cnf", "cnfksbf", "txf", "zxjl", "qt", "qtkk", "flhj", "yfgz", "ylbx", "sybx", "yilbx"
            , "zfgjj", "qynj", "dkgs", "sfgz"};
    public static final String[] wbbill = {"xh", "zyxm", "bmmc", "tzhyxbz", "yyxbz", "txfybz", "txfxbz", "txfce", "lzjtf", "jbgz",
            "gzjxgzjs", "gzjxkhxs", "qt", "tc", "yjjxgzjs", "yjjxkhxs", "jbbz", "zxjl", "txf", "kqkk", "qtkk", "yfgz", "ylbx", "yilbx",
            "sybx", "gjj", "kqynj", "flf", "ynssde", "kgrsds", "sfgz", "jrtxfyhzzsf"};
    public static final String[] cabill = {"zydm", "zyxm", "bmmc", "gzjxgzjs", "gzjxkhxs", "gzjx", "yjjxgzjs", "yjjxkhxs", "yjjx", "tc"
            , "kqkk", "cnf", "cnfksbf", "txf", "flf", "gjf", "jj", "flhj", "jbbz", "zxjl", "qt", "kshj", "yfgz", "ylbx", "sybx", "yilbx", "zfgjj"
            , "qynj", "dkgs", "qtkk", "sfgz"};

    public static final String[] empprop ={"empcode","empname","departname","email"};

    public static final String[] hxBillFileNames = {"职员代码","职员姓名","部门名称","基本工资","工作绩效工资基数","工作绩效考核系数","工作绩效","业绩绩效考核系数"
            ,"业绩绩效","提成","加班费","考勤扣款","福利费","交通费","采暖费","采暖费扣税部分","通讯费","专项奖励","其他","其它扣款","福利合计","应发工资","养老保险"
            ,"失业保险","医疗保险","住房公积金","企业年金","代扣个税","实发工资"};
    public static final String[] wbBillFileNames = {"序号","所属部门","调整后月薪标准","原月薪标准","通讯费原标准","通讯费现标准","通讯费差额","履职交通费","基本工资"
            ,"工作绩效工资基数","工作绩效考核系数","其他","提成","业绩绩效工资基数","业绩绩效考核系数","加班补助","专项奖励","通讯费","考勤扣款","其他扣款","应发工资","养老保险"
            ,"医疗保险","失业保险","公积金","扣企业年金","福利费","应纳税所得额","扣个人所得税","实发工资"};
    public static final String[] caBillFileNames = {"职员代码","职员姓名","部门名称","基本工资","工作绩效工资基数","工作绩效考核系数","工作绩效","业绩绩效工资基数"
            ,"业绩绩效考核系数","业绩绩效","提成","考勤扣款","采暖费","采暖费扣税部分","通讯费","福利费","过节费","奖金","福利合计","加班补助","专项奖励","其他",
            "扣款合计","应发工资","养老保险","失业保险","医疗保险","住房公积金","企业年金","代扣个税","其它扣款","实发工资"};


    public static final String webpath = "/export_excel/";
    public static final String filePrefix = "工资条";
    private static final int flushRows =100;
    public static List<String> getbillprop(String orgncode){
        List<String> codelist = new ArrayList<>();
        if("01".equals(orgncode)){
            codelist = Arrays.asList(hxbill);
        }else if("02".equals(orgncode)){
            codelist = Arrays.asList(wbbill);
        }else if("03".equals(orgncode)){
            codelist = Arrays.asList(cabill);
        }else if("00".equals(orgncode)){
            codelist = Arrays.asList(empprop);
        }
        return  codelist;
    }


    public static List<String> getBillFileName(String orgncode){
        List<String> namelist = new ArrayList<>();
        if("01".equals(orgncode)){
            namelist = Arrays.asList(hxBillFileNames);
        }else if("02".equals(orgncode)){
            namelist = Arrays.asList(wbBillFileNames);
        }else if("03".equals(orgncode)){
            namelist = Arrays.asList(caBillFileNames);
        }
        return  namelist;
    }

    public static FileSystemResource getBill(String eamil, HttpServletRequest request, String orgncode, List objectlist) throws Exception{
        String realPath = request.getSession().getServletContext().getRealPath("/export_excel");
        if(!new File(realPath).exists()){
            new File(realPath).mkdir();
        }
        //区分航信 外包 ca
        ExcelExportSXXSSF start = ExcelExportSXXSSF.start(realPath, webpath, filePrefix, getBillFileName(orgncode), getbillprop(orgncode), flushRows);
        start.writeDatasByObject(objectlist);
        String allpath = start.exportFile(true);
        FileSystemResource attachment = new FileSystemResource(allpath);
        return  attachment;
    }
}