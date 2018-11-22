package com.cwca.customer.common.service;

public interface SysShiroService {
	void login(String username, String password,Boolean rememberMe);
	/*void guestlogin(String role);*/

}
