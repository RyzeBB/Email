package com.cwca.customer.salary.dao;

import com.cwca.customer.salary.entity.Tc;

import java.util.List;

public interface TcDao {
    int insertObjects(List<Tc> t);
    List<Tc> selectObjects();
    void truncateTc();
}
