package com.cwca.customer.common.dao;



public interface BaseDao<T> {
	int insertObject(T entity);
	int deleteObject(int id);
	int updateObject(T entity);
	T selectObjectById(int id);




}




