package com.cwca.customer.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class CodecUtil {
    private static final String salt = "sklk";
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String encodeString(String content, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(3) + content + salt.charAt(1) + salt.charAt(2);
        return md5(str);
    }

    public static String decodeString(String content, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(3) + content + salt.charAt(1) + salt.charAt(2);
        return md5(str);
    }

    public static void main(String[] args) {
        System.out.println(encodeString("123456", salt));
        System.out.println(decodeString(encodeString("123456", salt), salt));
    }

}
