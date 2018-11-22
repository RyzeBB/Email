package com.cwca.customer.course.dao;

import com.cwca.customer.common.dao.BaseDao;
import com.cwca.customer.course.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao extends BaseDao<Course> {
    List<Course> findObjects(
            @Param("name") String name,
            @Param("valid") Integer valid,
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    int getRowCount(
            @Param("name") String name,
            @Param("valid") Integer valid);

    int validById(
            @Param("ids") String[] ids,
            @Param("valid") int valid);

    /*List<Map<String,Object>>
    findPrjIdAndNames();*/

    Course findObjectById(Integer id);
    Course findObjectByName(String coursename);
}
