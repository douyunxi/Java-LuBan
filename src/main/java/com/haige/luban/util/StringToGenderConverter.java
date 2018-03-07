package com.haige.luban.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.haige.luban.enums.EnumGenderType;

@Configuration
public class StringToGenderConverter implements Converter<String, EnumGenderType> {

	@Override
	public EnumGenderType convert(String gender) {
		if(gender.equalsIgnoreCase("MAN")) {
			return EnumGenderType.MAN;
		}
		else if(gender.equalsIgnoreCase("WOMAN")) {
			return EnumGenderType.WOMAN;
		}
		else{
			return EnumGenderType.UNKNOW;
		}
	}

}
