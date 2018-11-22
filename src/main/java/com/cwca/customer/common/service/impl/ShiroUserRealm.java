package com.cwca.customer.common.service.impl;

import com.cwca.customer.common.exception.ServiceException;
import com.cwca.customer.system.dao.SysRoleDao;
import com.cwca.customer.system.dao.SysUserDao;
import com.cwca.customer.system.dao.SysUserRoleDao;
import com.cwca.customer.system.entity.SysRole;
import com.cwca.customer.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroUserRealm extends AuthorizingRealm{
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysUserRoleDao sysUserRoleDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("==doGetAuthorizationInfo==");
		//权限查询
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		int userId = user.getId();		
		List<String> permsList = new ArrayList<String>();
		permsList = sysUserDao.findUserPermissions(userId);
		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perm : permsList){
			if(perm!=null && !("".equals(perm))){
				permsSet.add(perm);
			}
		}
		//角色查询
		List<String> roleList = new ArrayList<>();
		List<String> userRoles = sysUserDao.findUserRoles(userId);
		Set<String> roleSet = new HashSet<String>();
		for(String role:userRoles){
			if(role!=null && !("".equals(role))){
				roleSet.add(role);
			}
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		info.setRoles(roleSet);
		return info;
	}

	/**
	 * 认证
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("==doGetAuthenticationInfo==");
		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		//2. 从 UsernamePasswordToken 中来获取 username
		String username = upToken.getUsername();
		//判断用户名是否存在，若存在，返回user对象
		SysUser user =sysUserDao.findObjectByName(username);
		if(user==null)
			throw new ServiceException("该用户不存在");
		//盐值. 
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		//自动完成密码比对   - 密码的比对:
		//通过 AuthenticatingRealm 的 credentialsMatcher 属性来进行的密码的比对!
		SimpleAuthenticationInfo info =
		new SimpleAuthenticationInfo(
		username, user.getPassword(),credentialsSalt,getName());
		//session 这里应该没必要使用，直接使用principals即可
		SecurityUtils.getSubject().getSession()
		.setAttribute("currentUser",user);
		List <Integer> roleIds = sysUserRoleDao.findRoleIds(user.getId());
		SecurityUtils.getSubject().getSession()
				.setAttribute("currentUserRoles",roleIds);
		return info;
	}
}
