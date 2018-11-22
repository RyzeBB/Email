package com.cwca.customer.common.filter;

import com.cwca.customer.system.dao.SysRoleDao;
import com.cwca.customer.system.dao.SysUserDao;
import com.cwca.customer.system.dao.SysUserRoleDao;
import com.cwca.customer.system.entity.SysUser;
import com.cwca.customer.system.entity.SysUserRole;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * 解决remember me 后session失效的问题，
 * 前提：采用用户名密码认证的，在 doGetAuthenticationInfo 方法里面是采用如下的方法来做认证：
 * info = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
 * 即principal equal username
 * 使用了authc的过滤的url的是不能使用记住我功能
 */
public class RememberMeFilter extends FormAuthenticationFilter {
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private SysUserRoleDao sysUserRoleDao;
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,Object mappedValue){
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();

        if(!subject.isAuthenticated() && subject.isRemembered() && session.getAttribute("currentUser")==null){
            Object principal = subject.getPrincipal();
            if(principal!=null){
                String s = principal.toString();
                SysUser sysUser = sysUserDao.findObjectByName(s);
                session.setAttribute("currentUser",sysUser);
                List <Integer> roleIds = sysUserRoleDao.findRoleIds(sysUser.getId());
                session.setAttribute("currentUserRoles",roleIds);
            }
        }
        return subject.isAuthenticated() || subject.isRemembered();
    }
}
