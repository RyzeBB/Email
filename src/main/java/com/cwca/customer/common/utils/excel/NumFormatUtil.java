package com.cwca.customer.common.utils.excel;

public class NumFormatUtil {
    public static String removezero(String str){
        if(str.endsWith(".0")){
            return str.substring(0,str.lastIndexOf("."));
        }
        return str;
    }

}
