<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath }" scope="application"/>
<form class="form-horizontal alert alert-info">
	<div class="form-group">
		<label class="col-sm-2 control-label">Phone No.:</label>
		<div class="col-sm-3">
			<input type="text" class="form-control" id="phone" placeholder="">
		</div>
		<div class="col-sm-2">
			<button type="button" class="btn btn-info" onclick="search()">Search</button>
			<button class="btn btn-default">Reset</button>
		</div>
	</div>
</form>

<table id="prepaidInfo" class="table table-striped table-bordered bootstrap-datatable datatable responsive" style="width:100%">
    <thead>
	    <tr>
	    	<th>头像</th>
	        <th>昵称</th>
	        <th>真实姓名</th>
	        <th>性别</th>
	        <th>地区</th>
	        <th>详细地址</th>
	        <th>用户类型</th>
	        <th>电话</th>
	        <th>创建时间</th>
	        <th>修改时间</th>
	    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
<script>
var table;
$(function(){
	table=$("#prepaidInfo").dataTable({
		searching: false,
    	ordering:  false,
		serverSide: true,
		ajax: {
            url: "${basePath}/admin/user/query"/* ,
            dataSrc:"content",
            dataFilter: function(data){
                var json = jQuery.parseJSON( data );
                json.recordsTotal = json.total;
                json.recordsFiltered = json.total;
                json.data = json.list;
                return JSON.stringify( json ); // return JSON string
            }*/
        },
		columns: [
			{
            	data: "avatarUrl",
            	render:function( data, type, row ){
            		return '<img src="'+data.replace("https","http")+'" width="50"/>';
            	}
			},
            {
            	data: "nickname"
			},
            { 
            	data: "realName"
            },
            {
            	data: "gender",
            	render:function( data, type, row ){
            		var gender="未知";
            		switch(data){
            			case "0":gender="未知";break;
            			case "1":gender="男";break;
            			case "2":gender="女";break;
            		}
            		return gender;
            	}
            },
            { 
            	data: "",
            	render:function( data, type, row ){
            		console.log(data,type,row)
            		return row.country+"-"+row.province+"-"+row.district;
            	}
            },
            { 
            	data: "address"
            },
            { 
            	data: "type",
            	render:function( data, type, row ){
            		var type="未知";
            		switch(data){
            			case "0":type="管理员";break;
            			case "1":type="工人";break;
            			case "2":type="雇主";break;
            		}
            		return type;
            	}
            },
            { 
            	data: "mobile"
            },
            {
            	data: "createTime"/* ,
            	render:function( data, type, row ){
            		return moment(data).format("YYYY-MM-DD HH:mm")
            	} */
            },
            {
            	data: "updateTime"/* ,
            	render:function( data, type, row ){
            		return moment(data).format("YYYY-MM-DD HH:mm:ss");
            	} */
            }
	    ]/* ,
	     fnDrawCallback : function(){
			this.api().column(0).nodes().each(function(cell, i) {
				cell.innerHTML =  i + 1;
			});
		} */
    });
});
var search=function(){
	var ajax=table.api().ajax;
	ajax.url("${basePath}/admin/user/query?phoneNo="+$('#phone').val()).load();
};
</script>

