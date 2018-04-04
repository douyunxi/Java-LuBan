<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath }" scope="application"/>
<div class="col-sm-2 col-lg-2">
    <div class="sidebar-nav">
        <div class="nav-canvas">
            <div class="nav-sm nav nav-stacked">	
            </div>
            <ul class="nav nav-pills nav-stacked main-menu">
                <li class="nav-header">Main</li>
                <li><a class="ajax-link" href="${basePath}/admin/user"><i class="glyphicon"></i><span> 用户管理</span></a></li>
                <li><a class="ajax-link" href="${basePath}/admin/task"><i class="glyphicon"></i><span> 任务管理</span></a></li>
                <li><a class="ajax-link" href="${basePath}/admin/salary"><i class="glyphicon"></i><span> 工资管理</span></a></li>
                <li><a class="ajax-link" href="${basePath}/admin/message"><i class="glyphicon"></i><span> 通知/消息管理</span></a></li>
                <li><a class="ajax-link" href="https://mpkf.weixin.qq.com/cgi-bin/kfindex?token=1875813560" target="_blank"><i class="glyphicon"></i><span> 在线客服</span></a></li>
            </ul>
        </div>
    </div>
</div>
