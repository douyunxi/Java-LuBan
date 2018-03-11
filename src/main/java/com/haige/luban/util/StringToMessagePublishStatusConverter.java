package com.haige.luban.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.haige.luban.enums.EnumMessagePublishStatus;

@Configuration
public class StringToMessagePublishStatusConverter implements Converter<String, EnumMessagePublishStatus> {

	@Override
	public EnumMessagePublishStatus convert(String status) {
		if(status.equalsIgnoreCase("UNPUBLISHED")) {
			return EnumMessagePublishStatus.UNPUBLISHED;
		}
		else{
			return EnumMessagePublishStatus.PUBLISHED;
		}
	}

}
