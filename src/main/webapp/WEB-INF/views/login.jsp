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
                <li><a class="ajax-link" href="${basePath}/boss/userManagement/toQuery"><i class="glyphicon"></i><span> User Management</span></a></li>
                <li><a class="ajax-link" href="${basePath}/boss/tradeQuery/toQuery"><i class="glyphicon"></i><span> Transactions</span></a></li>
                <li><a class="ajax-link" href="${basePath}/boss/balanceQuery/toQuery"><i class="glyphicon"></i><span> Balance Inquiry</span></a></li>
                <li><a class="ajax-link" href="${basePath}/boss/cardManagement/toQuery"><i class="glyphicon"></i><span> Card Management</span></a></li>
            </ul>
        </div>
    </div>
</div>
