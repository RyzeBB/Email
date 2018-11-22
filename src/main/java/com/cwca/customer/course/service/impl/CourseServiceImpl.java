package com.cwca.customer.course.service.impl;

import com.cwca.customer.common.exception.ServiceException;
import com.cwca.customer.common.web.PageObject;
import com.cwca.customer.course.dao.CourseDao;
import com.cwca.customer.course.entity.Course;
import com.cwca.customer.course.service.CourseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;
    @Override
    public Map<String,Object> findObjects(
            String name,
            Integer valid,
            int pageCurrent) {
        int pageSize=4;
        int startIndex=(pageCurrent-1)*pageSize;
        List<Course> list=
                courseDao.findObjects(
                        name,valid,startIndex,pageSize);
        int rowCount=
                courseDao.getRowCount(name,valid);
        PageObject pageObject=new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setStartIndex(startIndex);
        Map<String,Object> map=
                new HashMap<String,Object>();
        map.put("list", list);
        map.put("pageObject", pageObject);
        return map;
    }
    @Override
    public void validById(String idStr,Integer valid) {
        if(idStr==null||idStr.trim().length()==0)
            throw new ServiceException("至少选择一项");
        if(valid!=0&&valid!=1)
            throw new ServiceException("valid值必须是0或者1");
        String[] ids=idStr.split(",");
        courseDao.validById(ids,valid);
    }

    @Override
    public void saveObject(Course entity) {
        if(entity.getName()==null)
            throw new ServiceException("请输入课程名");
        int rows= courseDao.insertObject(entity);
        if(rows==-1)
            throw new ServiceException("insert error");
    }

    @Override
    public Course findObjectById(
            Integer id) {
        if(id==null||id<=0)
            throw new ServiceException(
                    "id值无效:id="+id);
        Course course=
                courseDao.findObjectById(id);
        if(course==null)
            throw new ServiceException(
                    "没有找到对应的记录");
        return course;
    }
    @Override
    public void updateObject(Course entity) {
        if(entity.getName()==null)
            throw new ServiceException("被修改的记录不能空");
        int rows=courseDao.updateObject(entity);
        if(rows!=1)
            throw new RuntimeException("修改失败");
    }
}
