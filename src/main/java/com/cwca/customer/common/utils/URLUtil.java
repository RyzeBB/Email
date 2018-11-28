package com.cwca.customer.common.utils;

import javax.servlet.http.HttpServletRequest;

public class URLUtil {

    public static String webUrl(HttpServletRequest request){
       String url =  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        return url;
    }

    public static void main(String[] args) {
        System.out.println(Boolean.getBoolean("1"));
    }
}
