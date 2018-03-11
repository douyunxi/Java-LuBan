package com.haige.luban.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.haige.luban.enums.EnumMessageStatus;

@Configuration
public class StringToMessageStatusConverter implements Converter<String, EnumMessageStatus> {

	@Override
	public EnumMessageStatus convert(String status) {
		if(status.equalsIgnoreCase("UNREAD")) {
			return EnumMessageStatus.UNREAD;
		}
		else{
			return EnumMessageStatus.READ;
		}
	}

}
