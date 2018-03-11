package com.haige.luban.util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class UpdateUtil {
	
	/**
	 * 从源对象中拷贝非空属性到目标对象
	 * @param src
	 * @param target
	 */
	public static void copyNonNullProperties(Object  src,Object target){
        BeanUtils.copyProperties(src,target,getNullProperties(src));
    }

    private static String[] getNullProperties(Object src){
        BeanWrapper srcBean=new BeanWrapperImpl(src);
        PropertyDescriptor[] pds=srcBean.getPropertyDescriptors();
        Set<String> emptyName=new HashSet<>();
        for(PropertyDescriptor p:pds){
            Object srcValue=srcBean.getPropertyValue(p.getName());
            if(srcValue==null) emptyName.add(p.getName());
        }
        String[] result=new String[emptyName.size()];
        return emptyName.toArray(result);
    }
}
