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
<div class="col-md-12">
	<button class="btn btn-info pull-right" onclick="showAddModal()">新增</button>
</div>
<table id="prepaidInfo" class="table table-striped table-bordered bootstrap-datatable datatable responsive" style="width:100%">
    <thead>
	    <tr>
	    	<th></th>
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

<!--add Modal -->
<div class="modal fade" id="add_edit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">新增任务</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="form" data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
                    data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
                    data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
                    <input type="hidden" id="id" name="id"/>
					<div class="form-group">
						<label class="col-md-3 control-label">标题:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="title" name="title" placeholder="" data-bv-notempty="true" data-bv-notempty-message="必填项">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">内容:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="content" name="content" placeholder="" data-bv-notempty="true" data-bv-notempty-message="必填项">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">项目地区:</label>
						<div class="col-md-7">
							<select class="form-control" name="province" id="province" style="width:100%" data-bv-notempty="true" data-bv-notempty-message="必填项"></select>
							<select class="form-control" name="city" id="city" style="width:100%" data-bv-notempty="true"  data-bv-notempty-message="必填项"></select>
							<select class="form-control" name="district" id="district" style="width:100%"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">具体地址:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="address" name="address" placeholder="" data-bv-notempty="true" data-bv-notempty-message="必填项">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">雇主:</label>
						<div class="col-md-7">
							<select class="form-control" name="employer" id="employer" style="width:100%" data-bv-notempty="true" data-bv-notempty-message="必填项">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">预计金额:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="estimatedAmount" name="estimatedAmount" placeholder="" data-bv-notempty="true" data-bv-notempty-message="必填项">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">计划执行时间:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="planTime" id="planTime" placeholder="" data-bv-notempty="true" data-bv-notempty-message="必填项">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="addBtn" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>

<!--delete Modal -->
<div class="modal fade" id="delete_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">删除</h4>
			</div>
			<div class="modal-body">
				确认删除这一条任务吗？
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-danger" onclick="deleteTask()">确认</button>
			</div>
		</div>
	</div>
</div>
<script>
$.fn.select2.defaults.set( "theme", "bootstrap" );
var table,selectedId;
var provinces=[];//公用的
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
		columns: [{
				data : ""
			},
			{
            	data: "avatarUrl",
            	render:function( data, type, row ){
            		return '<img src="'+data.replace("https","http")+'" width="50"/>';
            	}
			},
            {
            	data: "nickName"
			},
            { 
            	data: "realName"
            },
            {
            	data: "gender",
            	render:function( data, type, row ){
            		var gender="未知";
            		switch(data){
            			case "MAN":gender="男";break;
            			case "WOMAN":gender="女";break;
            			case "UNKNOW":gender="未知";break;
            		}
            		return gender;
            	}
            },
            { 
            	data : "area",
				render : function(data, type, row) {
					return row.province.text + "-" + row.city.text  + (row.district?"-"+row.district.text:"");
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
            			case "WORKER":type="工人";break;
            			case "EMPLOYER":type="雇主";break;
            			case "ADMIN":type="管理员";break;
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
            },
            {
				data : "id",
				render : function(data, type, row, position) {
					return 	'<btn class="btn btn-xs btn-warning" onclick="showEditModal('+ position.row +')">'+
	                			'<i class="glyphicon glyphicon-edit icon-white"></i>'+
	                			' 编辑'+
	            			'</btn>'+
							'<btn class="btn btn-xs btn-danger" onclick="showDeleteModal('+ data +')">'+
				                '<i class="glyphicon glyphicon-trash icon-white"></i>'+
				                ' 删除'+
				            '</btn>';
				}
			}
	    ],
	     fnDrawCallback : function(){
			this.api().column(0).nodes().each(function(cell, i) {
				cell.innerHTML =  i + 1;
			});
		}
    });
	
	$.ajax({
		url: '${basePath}/admin/area/findAllProvince',
	  	dataType: 'json',
	  	success:function(data){
		  	provinces=data;	
			$('#province').select2({
				data: provinces
			}).trigger('change');
	  	}
	});
	
	$('#province').on('select2:select', function (e) {
		$('#city').empty();//删除子元素(option)
		$('#city').val(null).trigger('change');
		$('#district').val(null).trigger('change');
		findCities();
	});
	
	$('#city').on('select2:select', function (e) {
		//var data = e.params.data;
		$('#district').empty();//删除子元素(option)
		$('#district').val(null).trigger('change');
		findDistrict();
	});
});
	
var search=function(){
	var ajax=table.api().ajax;
	ajax.url("${basePath}/admin/user/query?phoneNo="+$('#phone').val()).load();
};
</script>

