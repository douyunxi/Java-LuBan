package com.haige.luban.bo;

import java.util.List;

import lombok.Data;

/**
 * datatables要求的返回格式
 * https://www.datatables.net/manual/server-side	Returned data
 * @author 林伟
 *
 */
@Data
public class DataTables {
	
	private Integer draw;
	
	private Long recordsTotal;
	
	private Long recordsFiltered;
	
	private List<? extends Object> data;
		
	private String error;
}
