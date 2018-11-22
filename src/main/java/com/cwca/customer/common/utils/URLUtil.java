package com.cwca.customer.common.utils;

import javax.servlet.http.HttpServletRequest;

public class URLUtil {

    public static String webUrl(HttpServletRequest request){
       String url =  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        return url;
    }
}
