package com.cwca.customer.salary.dao;

import com.cwca.customer.common.dao.BaseDao;
import com.cwca.customer.course.entity.Course;
import com.cwca.customer.salary.entity.EmpInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface EmpInfoDao extends BaseDao<EmpInfo> {

    int insertObjects(List<EmpInfo> t);
    List<EmpInfo> selectObjects( @Param("email") String email,
                                 @Param("startIndex") Integer startIndex,
                                 @Param("pageSize") Integer pageSize);

    int getRowCount(
            @Param("email") String email);

}
