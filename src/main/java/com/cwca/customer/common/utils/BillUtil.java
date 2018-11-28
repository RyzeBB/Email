package com.cwca.customer.common.utils;

import com.cwca.customer.common.utils.excel.ExcelExportSXXSSF;
import com.cwca.customer.common.utils.time.DateUtil;
import org.springframework.core.io.FileSystemResource;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillUtil {
    private static final String[] hxbill = {"zydm", "zyxm", "bmmc", "jbgz", "gzjxgzjs", "gzjxkhxs", "gzjx", "yjjxgzjs", "yjjxkhxs"
            , "yjjx", "tc", "jbbz", "kqkk", "flf", "jtf", "cnf", "cnfksbf", "txf", "zxjl", "qt", "qtkk", "flhj", "yfgz", "ylbx", "sybx", "yilbx"
            , "zfgjj", "qynj", "dkgs", "sfgz"};
    private static final String[] wbbill = {"zydm", "zyxm", "bmmc","tzhyxbz", "yyxbz", "txfybz", "txfxbz", "txfce", "lzjtf", "jbgz",
            "gzjxgzjs", "gzjxkhxs", "qt", "tc", "yjjxgzjs", "yjjxkhxs", "jbbz", "zxjl", "txf", "kqkk", "qtkk", "yfgz", "ylbx", "yilbx",
            "sybx", "zfgjj", "qynj", "flf", "ynssde", "dkgs", "sfgz", "jrtxfyhzzsf"};
    private static final String[] cabill = {"zydm", "zyxm", "bmmc", "jbgz", "gzjxgzjs", "gzjxkhxs", "gzjx", "yjjxgzjs", "yjjxkhxs"
            , "yjjx", "tc", "kqkk","cnf", "cnfksbf","txf", "flf","gjf","jj","flhj","jbbz","zxjl","qt","kkhj","yfgz","ylbx","sybx","yilbx","zfgjj"
    ,"qynj","dkgs","qtkk","sfgz"};

    private static final String[] empprop ={"empcode","empname","departname","email"};

    private static final String[] hxBillFileNames = {"职员代码","职员姓名","部门名称","基本工资","工作绩效工资基数","工作绩效考核系数","工作绩效","业绩绩效工资基数","业绩绩效考核系数"
            ,"业绩绩效","提成","加班补助","考勤扣款","福利费","交通费","采暖费","采暖费扣税部分","通讯费","专项奖励","其他","其它扣款","福利合计","应发工资","养老保险"
            ,"失业保险","医疗保险","住房公积金","企业年金","代扣个税","实发工资"};
    private static final String[] wbBillFileNames = {"职员代码","职员姓名","部门名称","调整后月薪标准","原月薪标准","通讯费原标准","通讯费现标准","通讯费差额","履职交通费","基本工资"
            ,"工作绩效工资基数","工作绩效考核系数","其他","提成","业绩绩效工资基数","业绩绩效考核系数","加班补助","专项奖励","通讯费","考勤扣款","其他扣款","应发工资","养老保险"
            ,"医疗保险","失业保险","住房公积金","企业年金","福利费","应纳税所得额","代扣个税","实发工资","加入通讯费用后最终实发"};
    private static final String[] caBillFileNames = {"职员代码","职员姓名","部门名称","基本工资","工作绩效工资基数","工作绩效考核系数","工作绩效","业绩绩效工资基数"
            ,"业绩绩效考核系数","业绩绩效","提成","考勤扣款","采暖费","采暖费扣税部分","通讯费","福利费","过节费","奖金","福利合计","加班补助","专项奖励","其他",
            "扣款合计","应发工资","养老保险","失业保险","医疗保险","住房公积金","企业年金","代扣个税","其它扣款","实发工资"};

    private static final String[] tcprop = {"xh","xm","bmmc","xsjl","fpcxxfjl","skfwfjl","jdfwfjl","xkhjl","dsjl","xj","bmwcbl","jrxdjl","pxjl","xmjljjl","tchj","yx"};
    private static final String[] tcFileNames ={"序号","姓名","部门名称","销售奖励","发票查询续费奖励","税控服务费奖励","金盾服务费奖励","新开户奖励(包含补发）","地税奖励","小计","部门完成比例","金融信贷奖励",
            "培训奖励","项目及累计奖励","提成合计","邮箱"};
    public static final String gzwebpath = "/export_gz";
    public static final String tcwebpath = "/export_tc";
    private static final String gzFilePrefix = "工资条";
    public static final String gzSubject = "" + DateUtil.getCurrectYear() + "年" + (DateUtil.getCurrectMonth() - 1) + "月"+gzFilePrefix;
    public static final String gzText = gzFilePrefix;
    public static final String gzAttachementname = gzFilePrefix+".xlsx";
    private static final String tcFilePrefix = "提成";
    public static final String tcSubject = "" + DateUtil.getCurrectYear() + "年" + (DateUtil.getCurrectMonth() - 1) + "月"+tcFilePrefix;
    public static final String tcText = tcFilePrefix;
    public static final String tcAttachementname = tcFilePrefix+".xlsx";
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
        }else if("tc".equals(orgncode)){
            codelist = Arrays.asList(tcprop);
        }
        return  codelist;
    }


    private static List<String> getBillFileName(String orgncode){
        List<String> namelist = new ArrayList<>();
        if("01".equals(orgncode)){
            namelist = Arrays.asList(hxBillFileNames);
        }else if("02".equals(orgncode)){
            namelist = Arrays.asList(wbBillFileNames);
        }else if("03".equals(orgncode)){
            namelist = Arrays.asList(caBillFileNames);
        }else if("tc".equals(orgncode)){
            namelist = Arrays.asList(tcFileNames);
        }
        return  namelist;
    }

    public static FileSystemResource getBill(HttpServletRequest request, String orgncode, List objectlist,String sendType) throws Exception{
        String webpath=null;
        String filePrefix = null;
        if("gz".equals(sendType)){
            webpath = gzwebpath;
            filePrefix = gzFilePrefix;
        }
        if("tc".equals(sendType)){
            webpath = tcwebpath;
            filePrefix = tcFilePrefix;
        }
        String realPath = request.getSession().getServletContext().getRealPath(webpath);
        File file = new File(realPath);
        if(!file.exists()){
            boolean mkdir = file.mkdir();
            if(!mkdir)
                throw new RuntimeException("存储文件夹创建失败");
        }
        //区分航信 外包 ca
        ExcelExportSXXSSF start = ExcelExportSXXSSF.start(realPath, webpath+"/", filePrefix, getBillFileName(orgncode), getbillprop(orgncode), flushRows);
        start.writeDatasByObject(objectlist);
        String allpath = start.exportFile(true);
        return new FileSystemResource(allpath);
    }

    public static void main(String[] args) {
        System.out.println(cabill.length);
        System.out.println(caBillFileNames.length);
    }
}