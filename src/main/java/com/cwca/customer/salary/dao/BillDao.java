package com.cwca.customer.salary.dao;

import com.cwca.customer.common.dao.BaseDao;
import com.cwca.customer.salary.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillDao extends BaseDao<Bill> {
    int insertObjects(List<Bill> t);
    List<Bill> selectObject(@Param("zydm") String zydm);
}
