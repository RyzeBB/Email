package com.cwca.customer.common.utils;

import java.util.Map;

/**
 * 使用org.apache.commons.beanutils进行转换
 */
public class MapToObject {

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if(obj == null)
            return null;
        return new org.apache.commons.beanutils.BeanMap(obj);
    }

}