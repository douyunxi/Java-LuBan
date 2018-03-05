<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath }" scope="application"/>
<div class="col-md-12">
	<button class="btn btn-success pull-right" data-toggle="modal" data-target="#addModal">新增</button>
</div>
<table id="prepaidInfo" class="table table-striped table-bordered bootstrap-datatable datatable responsive" style="width:100%">
    <thead>
	    <tr>
	    	<th></th>
	        <th>标题</th>
	        <th>内容</th>
	        <th>项目地区</th>
	        <th>具体地址</th>
	        <th>雇主</th>
	        <th>预计金额</th>
	        <th>任务创建时间</th>
	        <th>计划执行时间</th>
	        <th>工人</th>
	        <th>实际执行时间</th>
	        <th>实际完成时间</th>
	        <th>实际发生金额</th>
	        <th>施工状态</th>
	        <th>操作</th>
	    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">新增任务</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="addForm">
					<div class="form-group">
						<label class="col-md-3 control-label">标题:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="title" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">内容:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="content" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">项目地区:</label>
						<div class="col-md-7">
							<select class="form-control col-md-2" name="provinces" id="add_provinces"></select>
							<select class="form-control col-md-2" name="city" id="add_city"></select>
							<select class="form-control col-md-2" name="district" id="add_district"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">具体地址:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="address" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">雇主:</label>
						<div class="col-md-7">
							<select class="form-control" name="createUser">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">预计金额:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="estimatedAmount" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">计划执行时间:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="planTime" placeholder="">
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
<link href="${basePath}/resources/bower_components/select2-4.0.5/dist/css/select2.min.css" rel="stylesheet" />
<link href="${basePath}/resources/bower_components/select2-bootstrap-theme/dist/select2-bootstrap.min.css" rel="stylesheet" />
<script src="${basePath}/resources/bower_components/select2-4.0.5/dist/js/select2.min.js"></script>
<script src="${basePath}/resources/bower_components/select2-4.0.5/dist/js/i18n/zh-CN.js"></script>
<script>
	$.fn.select2.defaults.set( "theme", "bootstrap" );
	var table;
	$(function() {
		table = $("#prepaidInfo").dataTable({
			searching : false,
			ordering : false,
			serverSide : true,
			ajax : {
				url : "${basePath}/admin/task/query"/* ,
	            dataSrc:"content",
	            dataFilter: function(data){
	                var json = jQuery.parseJSON( data );
	                json.recordsTotal = json.total;
	                json.recordsFiltered = json.total;
	                json.data = json.list;
	                return JSON.stringify( json ); // return JSON string
	            }*/
			},
			columns : [{
					data : ""
				},
				{
					data : "title"
				},
				{
					data : "content"
				},
				{
					data : "area",
					render : function(data, type, row) {
						console.log(data, type, row)
						return row.province + "-" + row.city + "-" + row.district;
					}
				},
				{
					data : "address"
				}, 
				{
					data : "createUser"
				}, 
				{
					data : "estimatedAmount"
				},
				{
					data : "createTime"
				}, 
				{
					data : "planTime"
				},
				{
					data : "receiver"
				},
				{
					data : "buildingTime"
				},
				{
					data : "finishTime"
				},
				{
					data : "actualAmount"
				},
				{
					data : "status",
					render : function(data, type, row) {
						var status = "未开始";
						switch (data) {
							case "1":
								status = '<span class="label label-default label-info">未开始</span>';break;
							case "2":
								status = '<span class="label label-default label-danger">施工中</span>';break;
							case "3":
								status = '<span class="label label-default label-success">已完成</span>';break;
						}
						return status;
					}
				},
				{
					data : "",
					render : function(data, type, row) {
						return 
							'<a class="btn btn-info" href="#">'+
	                			'<i class="glyphicon glyphicon-edit icon-white"></i>'+
	                			'分配任务'+
	            			'</a>'+
							'<a class="btn btn-warning" href="#">'+
	                			'<i class="glyphicon glyphicon-edit icon-white"></i>'+
	                			'编辑'+
	            			'</a>'+
							'<a class="btn btn-danger" href="#">'
				                '<i class="glyphicon glyphicon-trash icon-white"></i>'+
				                '删除'+
				            '</a>';
					}
				}],
			fnDrawCallback : function() {
				this.api().column(0).nodes().each(function(cell, i) {cell.innerHTML = i + 1;});
			}
		});
		
		$('#addForm').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	title: {
	                validators: {
	                    notEmpty: {
	                        message: 'The value is required and cannot be empty'
	                    }
	                }
	            },
	            content: {
	                validators: {
	                    notEmpty: {
	                        message: 'The value is required and cannot be empty'
	                    }
	                }
	            }
	        }
	    });

	    // Validate the form manually
	    $('#addBtn').click(function() {
	        $('#addForm').bootstrapValidator('validate');
	    });
	    
	    $('#resetBtn').click(function() {
	        $('#form').data('bootstrapValidator').resetForm(true);
	    });
	    
	    $("#accountNo").focus();
	});
	
	//公用的省份
	var provinces=[];
	
	$.ajax({
		url: '${basePath}/admin/area/findAllProvince',
	  	dataType: 'json',
	  	success:function(data){
		  	provinces=data;	
			$('#add_provinces').select2({
				data: data
			});	
	  	}
	});
	
	$('#add_provinces').on('select2:select', function (e) {
		var data = e.params.data;
	    console.log(data);
	    $('#add_city').select2({
			ajax: {
				url: '${basePath}/admin/area/findAllProvince',
				dataType: 'json'
		    	
		  	}
		});
	});
	
</script>

