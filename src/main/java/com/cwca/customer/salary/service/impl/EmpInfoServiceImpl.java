package com.cwca.customer.salary.service.impl;

import com.cwca.customer.common.exception.ServiceException;
import com.cwca.customer.common.web.PageObject;
import com.cwca.customer.course.entity.Course;
import com.cwca.customer.salary.dao.EmpInfoDao;
import com.cwca.customer.salary.entity.EmpInfo;
import com.cwca.customer.salary.service.EmpInfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpInfoServiceImpl implements EmpInfoService<EmpInfo> {
    @Resource
    private EmpInfoDao empInfoDao;

    @Override
    public int insertEmpInfo(EmpInfo empInfo) {
        if(empInfo==null)
            throw new ServiceException("被保存的记录不能空");
        if(StringUtils.isEmpty(empInfo.getEmail()) || StringUtils.isBlank(empInfo.getEmail())){
            throw new ServiceException("请输入邮箱");
        }
        if(StringUtils.isEmpty(empInfo.getEmpname()) || StringUtils.isBlank(empInfo.getEmpname())){
            throw new ServiceException("请输入姓名");
        }
        int i = empInfoDao.insertObject(empInfo);
        if(i!=1)
            throw new ServiceException("保存失败");
        return 0;
    }

    @Override
    public void deleteEmpInfo(Integer id) {
        if(id==null){
            throw new ServiceException("主键id无效");
        }
        int i = empInfoDao.deleteObject(id);
        if(i<=0)
            throw new ServiceException("删除失败");
    }

    @Override
    public void updateEmpInfo(EmpInfo empInfo) {
        if(empInfo==null)
            throw new ServiceException("被修改的记录不能空");
        if(StringUtils.isEmpty(empInfo.getEmail()) || StringUtils.isBlank(empInfo.getEmail())){
            throw new ServiceException("请输入邮箱");
        }
        if(StringUtils.isEmpty(empInfo.getEmpname()) || StringUtils.isBlank(empInfo.getEmpname())){
            throw new ServiceException("请输入姓名");
        }
        int rows=empInfoDao.updateObject(empInfo);
        if(rows!=1)
            throw new RuntimeException("修改失败");
    }

    @Override
    public EmpInfo selectEmpInfoById(Integer id) {
        if(id==null||id<=0)
            throw new ServiceException(
                    "id值无效:id="+id);

        EmpInfo empInfo = empInfoDao.selectObjectById(id);
        if(empInfo==null)
            throw new ServiceException(
                    "没有找到对应的记录");
        return empInfo;
    }

    @Override
    public Map <String, Object> findAll(String email, int pageCurrent) {
        int pageSize=6;
        int startIndex=(pageCurrent-1)*pageSize;

        List <EmpInfo> ts = empInfoDao.selectObjects(
                email, startIndex, pageSize);
        int rowCount=
                empInfoDao.getRowCount(email);
        PageObject pageObject=new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setStartIndex(startIndex);
        Map<String,Object> map=
                new HashMap<String,Object>();
        map.put("list", ts);
        map.put("pageObject", pageObject);
        return map;
    }


}
