package com.haige.luban.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.haige.luban.enums.EnumTaskStatus;

@Configuration
public class StringToTaskStatusConverter implements Converter<String, EnumTaskStatus> {

	@Override
	public EnumTaskStatus convert(String gender) {
		if(gender.equalsIgnoreCase("NO_START")) {
			return EnumTaskStatus.NO_START;
		}
		else if(gender.equalsIgnoreCase("DISPATCHED")) {
			return EnumTaskStatus.DISPATCHED;
		}
		else if(gender.equalsIgnoreCase("RECEIPT_PART")) {
			return EnumTaskStatus.RECEIPT_PART;
		}
		else if(gender.equalsIgnoreCase("REJECT_PART")) {
			return EnumTaskStatus.REJECT_PART;
		}
		else if(gender.equalsIgnoreCase("RECEIPT_ALL")) {
			return EnumTaskStatus.RECEIPT_ALL;
		}
		else if(gender.equalsIgnoreCase("PROCESSING")) {
			return EnumTaskStatus.PROCESSING;
		}
		else if(gender.equalsIgnoreCase("CHECKING")) {
			return EnumTaskStatus.CHECKING;
		}
		else{
			return EnumTaskStatus.FINISHED;
		}
	}

}
