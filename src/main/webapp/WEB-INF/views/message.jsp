<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath }" scope="application"/>
<!-- <form class="form-horizontal alert alert-info">
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
</form> -->
<div class="col-md-12">
	<button class="btn btn-info pull-right" onclick="showAddModal()">新增</button>
</div>
<table id="prepaidInfo" class="table table-striped table-bordered bootstrap-datatable datatable responsive" style="width:100%">
    <thead>
	    <tr>
	    	<th></th>
	    	<th>标题</th>
	        <th>内容</th>
	        <th>发布状态</th>
	        <th>创建者</th>
	        <th>创建时间</th>
	        <th>接收者</th>
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
				<h4 class="modal-title" id="myModalLabel">新建消息</h4>
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
							<textarea class="form-control" id="content" name="content" placeholder="" data-bv-notempty="true" data-bv-notempty-message="必填项">
							</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">接收者:</label>
						<div class="col-md-7">
							<select class="form-control" name="reciver" id="reciver" style="width:100%" data-bv-notempty="true" data-bv-notempty-message="必填项">
								<option value="ALL">所有人</option>
								<option value="WORKER">所有工人</option>
								<option value="EMPLOYER">所有雇主</option>
								<option value="ADMIN">所有管理员</option>
							</select>
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

<!--publish Modal -->
<div class="modal fade" id="publish_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">发布消息</h4>
			</div>
			<div class="modal-body">
				发布消息后所有接收者将会看到此条信息？
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-success" onclick="publish()">确认</button>
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
				确认删除这一条消息吗？
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-danger" onclick="deleteUser()">确认</button>
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
var table,selectedId;
var recivers=[];//公用的
$(function(){
	table=$("#prepaidInfo").dataTable({
		searching: false,
    	ordering:  false,
		serverSide: true,
		ajax: {
            url: "${basePath}/admin/message/query"
        },
		columns: [{
				data : ""
			},
			{
            	data: "title"
			},
            {
            	data: "content"
			},
            {
            	data: "publishStatus",
            	render:function( data, type, row ){
            		var status="未发布";
            		switch(data){
            			case "UNPUBLISHED":status='<span class="label label-default label-info">未发布</span>';break;
            			case "PUBLISHED":status='<span class="label label-default label-success">已发布</span>';break;
            		}
            		return status;
            	}
			},
			{
            	data: "createUser"
			},
            {
            	data: "createTime"
            },
            {
            	data: "" ,
            	render:function( data, type, row ){
            		return '<a>查看</a>';
            	}
            },
            {
				data : "id",
				render : function(data, type, row, position) {
					var returnBtn="";
					if(row.publishStatus=="UNPUBLISHED"){
						returnBtn+='<btn class="btn btn-xs btn-success" onclick="showPublishModal('+ data +')">'+
					        			'<i class="glyphicon glyphicon-share icon-white"></i>'+
					        			' 发布'+
					    			'</btn> '+
					    			'<btn class="btn btn-xs btn-warning" onclick="showEditModal('+ position.row +')">'+
			                			'<i class="glyphicon glyphicon-edit icon-white"></i>'+
			                			' 编辑'+
			            			'</btn> ';
					}
					returnBtn+=	'<btn class="btn btn-xs btn-danger" onclick="showDeleteModal('+ data +')">'+
					                '<i class="glyphicon glyphicon-trash icon-white"></i>'+
					                ' 删除'+
					            '</btn>';
					return returnBtn;
					 
				}
			}
	    ],
	     fnDrawCallback : function(){
			this.api().column(0).nodes().each(function(cell, i) {
				cell.innerHTML =  i + 1;
			});
		}
    });
});

var showAddModal=function(){
	$('#add_edit_modal').modal('show');
	$('#myModalLabel').html('新增用户');
	$('form input,textarea').val('');
	$('#reciver').val('').trigger('change');
};
var showEditModal=function(row){
	var data=table.api().data()[row];
	$('#add_edit_modal').modal('show');
	$('#myModalLabel').html('修改消息');
	$('#id').val(data.id);
	$('#title').val(data.title);
	console.log(data.content)
	$('#form #content').html(data.content);
	//$('#reciver').val(data.reciver.id).trigger("change").trigger("select");
};
$('#add_edit_modal').on('show.bs.modal', function (e) {
	$('#form').bootstrapValidator('validate');//验证初始化
});
$('#add_edit_modal').on('hidden.bs.modal', function (e) {
	$('#form').data('bootstrapValidator').destroy();//验证状态清空
});
$('#addBtn').click(function() {
    $('#form').bootstrapValidator('validate');
    if($("#form").data('bootstrapValidator').isValid()){
    	$.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "${basePath}/admin/message/"+($("#id").val()?"update":"add"),
            data: $('#form').serialize(),
            success: function (result) {
            	if(result){
            		$("#add_edit_modal").modal('hide');
                	table.api().ajax.reload();
            	}
            },
            error : function() {
                alert("异常！");
            }
        });
    }
});
var showPublishModal=function(id){
	selectedId=id;
	$('#publish_modal').modal('show');
};
var publish=function(){
	$.ajax({
		url: '${basePath}/admin/message/publish',
	  	dataType: 'json',
	  	data:{
	  		id:selectedId
    	},
	  	success:function(result){
	  		if(result){
        		$("#publish_modal").modal('hide');
            	table.api().ajax.reload();
        	}
	  	}
	});
};
var showDeleteModal=function(id){
	selectedId=id;
	$('#delete_modal').modal('show');
};
var deleteUser=function(){
	$.ajax({
		url: '${basePath}/admin/message/delete',
	  	dataType: 'json',
	  	data:{
	  		id:selectedId
    	},
	  	success:function(result){
	  		if(result){
        		$("#delete_modal").modal('hide');
            	table.api().ajax.reload();
        	}
	  	}
	});
};
var search=function(){
	var ajax=table.api().ajax;
	ajax.url("${basePath}/admin/user/query?phoneNo="+$('#phone').val()).load();
};
</script>

