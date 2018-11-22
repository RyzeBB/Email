package com.cwca.customer.system.dao;

import com.cwca.customer.common.dao.BaseDao;
import com.cwca.customer.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserDao extends BaseDao<SysUser> {

	List<SysUser> findPageObjects(
            @Param("username") String username,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);
	
	int getRowCount(@Param("username") String username);
	SysUser findObjectById(Integer id);
	int validById(@Param("id") Integer id, @Param("valid") Integer valid);
	SysUser findObjectByName(String username);
	//SysUser findObjectByTaxAndMobile(@Param("unittax") String unittax, @Param("mobile") String mobile);
	SysUser findObjectByTax(@Param("unittax") String unittax);
	SysUser findObjectByTaxForOpen(@Param("unittax") String unittax);
	int updateObject(SysUser sysUser);
	int updateQrCodePath(@Param("id") Integer id, @Param("qrCodePath") String qrCodePath);
	int insertObjectList(List<SysUser> entitylist);

	/**
	 * 查询用户得权限
	 * @param userId
	 */
	List<String> findUserPermissions(Integer userId);

	/**
	 * 查询用户的角色
	 * @param userId
	 * @return
	 */
	List<String> findUserRoles(Integer userId);
	List<Map<String,Object>> findUserMenus(Integer userId);
	int updateOpenId(@Param("openid") String openid,@Param("id") Integer id);

}
