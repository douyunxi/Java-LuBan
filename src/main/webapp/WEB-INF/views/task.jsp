<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath }" scope="application"/>
<div class="col-md-12">
	<button class="btn btn-info pull-right" onclick="showAddModal()">新增</button>
</div>
<table id="prepaidInfo" class="table table-striped table-bordered bootstrap-datatable datatable responsive" style="width:100%">
    <thead>
	    <tr>
	    	<th></th>
	        <th>标题</th>
	        <th>内容</th>
	        <th>项目地区</th>
	        <!-- <th>具体地址</th> -->
	        <th>雇主</th>
	        <th>预计金额</th>
	        <!-- <th>任务创建时间</th> -->
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
				<form class="form-horizontal" id="form">
					<div class="form-group">
						<label class="col-md-3 control-label">标题:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="title" name="title" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">内容:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="content" name="content" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">项目地区:</label>
						<div class="col-md-7">
							<select class="form-control" name="province" id="province" style="width:100%"></select>
							<select class="form-control" name="city" id="city" style="width:100%"></select>
							<select class="form-control" name="district" id="district" style="width:100%"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">具体地址:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="address" name="address" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">雇主:</label>
						<div class="col-md-7">
							<select class="form-control" name="employer" id="employer" style="width:100%">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">预计金额:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" id="estimatedAmount" name="estimatedAmount" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">计划执行时间:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="planTime" id="planTime" placeholder="">
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
	var table,selectedRow;
	var provinces=[],employers=[],workers=[];//公用的
	
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
					data : "title",
					render : function(data, type, row) {
						return '<a href="${basePath}/admin/task/'+ row.id +'">'+ data +'</a>';
					}
				},
				{
					data : "content"
				},
				{
					data : "area",
					render : function(data, type, row) {
						return row.province.text + "-" + row.city.text  + (row.district?"-"+row.district.text:"");
					}
				},
				/* {
					data : "address"
				}, */
				{
					data : "employer",
					render : function(data, type, row) {
						return '<a href="${basePath}/admin/user/'+ data.id +'">'+ data.realName +'</a>';
					}
				}, 
				{
					data : "estimatedAmount"
				},
				/* {
					data : "createTime"
				},  */
				{
					data : "planTime"
				},
				{
					data : "worker",
					render : function(data, type, row) {
						return data?('<a href="${basePath}/admin/user/'+ data.id +'">'+ data.realName +'</a>'):'';
					}
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
							case "NO_START":
								status = '<span class="label label-default label-info">未开始</span>';break;
							case "PROCESSING":
								status = '<span class="label label-default label-danger">施工中</span>';break;
							case "FINISHED":
								status = '<span class="label label-default label-success">已完成</span>';break;
						}
						return status;
					}
				},
				{
					data : "id",
					render : function(data, type, row) {
						selectedRow=row;
						return 	'<btn class="btn btn-xs btn-success">'+
		                			'<i class="glyphicon glyphicon-share icon-white"></i>'+
		                			' 派单'+
		            			'</btn>'+
								'<btn class="btn btn-xs btn-warning" onclick="showEditModal()">'+
		                			'<i class="glyphicon glyphicon-edit icon-white"></i>'+
		                			' 编辑'+
		            			'</btn>'+
								'<btn class="btn btn-xs btn-danger">'+
					                '<i class="glyphicon glyphicon-trash icon-white"></i>'+
					                ' 删除'+
					            '</btn>';
					}
				}],
			fnDrawCallback : function() {
				this.api().column(0).nodes().each(function(cell, i) {cell.innerHTML = i + 1;});
			}
		});
		
		$('#form').bootstrapValidator({
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
	        $('#form').bootstrapValidator('validate');
	        if($("#form").data('bootstrapValidator').isValid()){
	        	$.ajax({
	                //几个参数需要注意一下
                    type: "POST",//方法类型
                    dataType: "json",//预期服务器返回的数据类型
                    url: "${basePath}/admin/task/add" ,
                    data: $('#form').serialize(),
                    success: function (result) {
                        console.log(result);
                        if (result.resultCode == 200) {
                            alert("SUCCESS");
                        }
                        ;
                    },
                    error : function() {
                        alert("异常！");
                    }
                });
	        }
	    });
	    
	    /* $('#resetBtn').click(function() {
	        $('#form').data('bootstrapValidator').resetForm(true);
	    }); */
	    
	    
	    $.ajax({
			url: '${basePath}/admin/area/findAllProvince',
		  	dataType: 'json',
		  	success:function(data){
			  	provinces=data;	
				$('#province').select2({
					data: provinces
				}).trigger('select2:select');
		  	}
		});
		
		$('#province').on('select2:select', function (e) {
			$('#city').val(null).trigger('change');
			$('#district').val(null).trigger('change');
			findCities();
		});
		
		$('#city').on('select2:select', function (e) {
			//var data = e.params.data;
			$('#district').val(null).trigger('change');
			findDistrict();
		});
		
		$.ajax({
			url: '${basePath}/admin/user/findAllEmployer',
		  	dataType: 'json',
		  	success:function(data){
		  		//数据转换
		  		for(var i=0;i<data.length;i++){
		  			employers.push({id:data[i].id,text:data[i].realName});
		  		}
				$('#employer').select2({
					data: employers
				}).trigger("change");
		  	}
		});
		
		$('#planTime').datetimepicker({
			language:'zh-cn',
			format:"YYYY-MM-DD HH:mm:ss",
			useCurrent : false,
			useSeconds: true,
			autoclose:true
		})//.setMinDate(now());;
		
	});
	
	var findCities=function(){
		$('#city').select2({
			ajax: {
				url: '${basePath}/admin/area/findCities',
				dataType: 'json',
		    	data:{
		    		provinceId:$('#province').val()
		    	},
				processResults: function (data) {
					return {
						results: data
					};
				}
		  	}/* ,
		  	templateResult:function(){
		  		alert(1);
		  	},
		  	templateSelection:function(){
		  		alert(2);
		  	} */
		});
	};
	
	var findDistrict=function(){
		$('#district').select2({
			ajax: {
				url: '${basePath}/admin/area/findDistrictes',
				dataType: 'json',
		    	data:{
		    		cityId:$('#city').val()
		    	},
				processResults: function (data) {
					return {
						results: data
					};
				}
		  	}
		});
	};
	
	var showAddModal=function(){
		$('#add_edit_modal').modal('show');
		$('#myModalLabel').html('新增任务');
		$('form input').val('');
	};
	var showEditModal=function(row){
		var row=selectedRow;
		$('#add_edit_modal').modal('show');
		$('#myModalLabel').html('修改任务');
		$('#title').val(row.title);
		$('#form #content').val(row.content);
		$('#province').val(row.province.id).trigger("change");
		findCities();
		$('#city').val(row.city.id).trigger("select");
		if(row.district){
			findDistrict();
			$('#district').val(row.district.id).trigger("select");
		}
		$('#address').val(row.address);
		//console.log(row,row.employer)
		//$('#employer').val(row.employer.id).trigger("select");
		$('#estimatedAmount').val(row.estimatedAmount);
		$('#planTime').val(row.planTime);
	};
</script>

