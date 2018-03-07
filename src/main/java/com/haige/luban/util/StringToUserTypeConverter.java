package com.haige.luban.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.haige.luban.enums.EnumUserType;

@Configuration
public class StringToUserTypeConverter implements Converter<String, EnumUserType> {

	@Override
	public EnumUserType convert(String gender) {
		if(gender.equalsIgnoreCase("WORKER")) {
			return EnumUserType.WORKER;
		}
		else if(gender.equalsIgnoreCase("EMPLOYER")) {
			return EnumUserType.EMPLOYER;
		}
		else{
			return EnumUserType.ADMIN;
		}
	}

}
