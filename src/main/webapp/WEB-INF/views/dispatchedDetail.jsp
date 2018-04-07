<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath }" scope="application"/>
<style>
#title{
	margin: 30px 0;
	padding:50px;
}
</style>
<div class="col-md-12">
	<a class="btn btn-success pull-right" href="${basePath}/admin/task">返回</a>
</div>
<div class="col-md-12 bg-info" id="title">
	${title}
</div>
<table class="table table-striped table-bordered" style="width:100%">
    <thead>
	    <tr>
	    	<th></th>
	    	<th>工人</th>
	        <th>接单详情</th>
	        <th>雇主评分</th>
	    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${relations}" var="relation" varStatus="status">
    		<tr>
    			<td>${status.index+1}</td>
    			<td>${relation.user.realName}</td>
    			<td>${relation.status.desc}
	    			<%-- <c:choose>
	    				<c:when test='${relation.status.desc == "NO_START"}'><span class="label label-default label-default">未开始</span></c:when>
	    				<c:when test='${relation.status.desc == "DISPATCHED"}'><span class="label label-default label-primary">已派单</span></c:when>
	    				<c:when test='${relation.status.desc == "RECEIPT_PART"}'><span class="label label-default label-muted">部分接单</span></c:when>
	    				<c:when test='${relation.status.desc == "REJECT_PART"}'><span class="label label-default label-danger">需重派单</span></c:when>
	    				<c:when test='${relation.status.desc == "RECEIPT_ALL"}'><span class="label label-default label-success">全部接单</span></c:when>
	    				<c:when test='${relation.status.desc == "PROCESSING"}'><span class="label label-default label-warning">施工中</span></c:when>
	    				<c:when test='${relation.status.desc == "CHECKING"}'><span class="label label-default label-info">验收中</span></c:when>
	    				<c:when test='${relation.status.desc == "FINISHED"}'><span class="label label-default label-success">已完成</span></c:when>
	    			</c:choose> --%>
    			</td>
    			<td></td>
    		</tr>
    	</c:forEach>
    </tbody>
</table>


<link href="${basePath}/resources/bower_components/select2-4.0.5/dist/css/select2.min.css" rel="stylesheet" />
<link href="${basePath}/resources/bower_components/select2-bootstrap-theme/dist/select2-bootstrap.min.css" rel="stylesheet" />
<script src="${basePath}/resources/bower_components/select2-4.0.5/dist/js/select2.min.js"></script>
<script src="${basePath}/resources/bower_components/select2-4.0.5/dist/js/i18n/zh-CN.js"></script>
<script>
	
</script>

