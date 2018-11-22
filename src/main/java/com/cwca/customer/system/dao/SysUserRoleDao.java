package com.cwca.customer.system.dao;
import com.cwca.customer.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleDao {
	int isUsedByUser(@Param("roleId") Integer roleId);
	int insertObject(@Param("userId") Integer userId,
                     @Param("roleIds") String[] roleIds);

	int deleteUserRoles(Integer userId);
	List<Integer> findRoleIds(Integer userId);
	int insertRoleNonMemebr(@Param("userId") Integer userId);
	int insertRoleMemebr(@Param("userId") Integer userId);

}
