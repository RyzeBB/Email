package com.cwca.customer.common.service.impl;
import com.cwca.customer.common.exception.ServiceException;
import com.cwca.customer.common.service.SysShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class SysShiroServiceImpl  implements SysShiroService {
	@Override
	public void login(String username, String password,Boolean rememberMe) {
		Subject subject = SecurityUtils.getSubject();
		//解决同一回话中登错账号的问题
        if(subject.getPrincipal()!=null && !username.equals(subject.getPrincipal())){
            throw new ServiceException("请退出系统后重新登陆");
        }
        //验证登陆或rememberme 二选一
		/*if(Boolean.TRUE.equals(subject.isAuthenticated()) || Boolean.TRUE.equals(subject.isRemembered()))return;*/
		if(Boolean.TRUE.equals(subject.isAuthenticated()) || Boolean.TRUE.equals(subject.isRemembered()))return;

        // 把用户名和密码封装为 UsernamePasswordToken 对象
        UsernamePasswordToken token = 
        new UsernamePasswordToken(username, password);
        token.setRememberMe(rememberMe);
        try{//登录认证 - 调用userRealm
        	subject.login(token);
          /*  System.out.println(subject.hasRole("42"));*/
           /* boolean permitted = subject.isPermitted("product:list");
            System.out.println(permitted);*/
        }catch (IncorrectCredentialsException ice) {
        throw new ServiceException("密码错误！");
        } catch(AuthenticationException ae){
        ae.printStackTrace();
        throw new ServiceException("认证失败");
        }
	}

   /* @Override
    public void guestlogin(String role) {
	    if(role==null){
	        throw new ServiceException("进入失败");
        }
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        if(subject.getPrincipal()!=null){
            throw new ServiceException("请退出浏览器重新进入");
        }
    }*/

}
