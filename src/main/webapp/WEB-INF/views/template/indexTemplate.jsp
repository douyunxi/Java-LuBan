<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% request.setAttribute("context",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()); %>

<c:set var="basePath" value="${pageContext.request.contextPath }" scope="application"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Merchant Back End</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- The styles -->
    <link id="bs-css" href="${basePath}/resources/css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href='${basePath}/resources/css/jquery.dataTables.min.css' rel='stylesheet'>
	<link href='${basePath}/resources/css/dataTables.bootstrap.min.css' rel='stylesheet'>
    <link href="${basePath}/resources/css/app.css" rel="stylesheet">
    <link href='${basePath}/resources/bower_components/bootstrapvalidator/dist/css/bootstrapValidator.min.css' rel='stylesheet'>
    <link href='${basePath}/resources/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='${basePath}/resources/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='${basePath}/resources/bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='${basePath}/resources/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='${basePath}/resources/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='${basePath}/resources/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='${basePath}/resources/css/jquery.noty.css' rel='stylesheet'>
    <link href='${basePath}/resources/css/noty_theme_default.css' rel='stylesheet'>
    <link href='${basePath}/resources/css/elfinder.min.css' rel='stylesheet'>
    <link href='${basePath}/resources/css/elfinder.theme.css' rel='stylesheet'>
    <link href='${basePath}/resources/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='${basePath}/resources/css/uploadify.css' rel='stylesheet'>
    <link href='${basePath}/resources/css/animate.min.css' rel='stylesheet'>

    <!-- jQuery -->
    <script src="${basePath}/resources/bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="${basePath}/resources/img/favicon.ico">

</head>

	<body>
	    <!-- topbar starts -->
	    <div class="navbar navbar-default" role="navigation">
	        <div class="navbar-inner">
	            <button type="button" class="navbar-toggle pull-left animated flip">
	                <span class="sr-only">Toggle navigation</span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>
	            <a class="navbar-brand" href="${basePath}/index"> <img alt="Logo" src="${basePath}/resources/img/logo.png" class="hidden-xs"/></a>
	
	            <!-- user dropdown starts -->
	            <div class="btn-group pull-right">
	                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
	                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> ${mpUser.realName}</span>
	                    <span class="caret"></span>
	                </button>
	                <ul class="dropdown-menu">
	                    <li><a href="#">Profile</a></li>
	                    <li class="divider"></li>
	                    <li><a href="${basePath}/<tiles:getAsString name="path" ignore="true"/>/logout">Logout</a></li>
	                </ul>
	            </div>
	            <!-- user dropdown ends -->
	
	            <!-- theme selector starts -->
	            <div class="btn-group pull-right theme-container animated tada">
	                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
	                    <i class="glyphicon glyphicon-tint"></i><span
	                        class="hidden-sm hidden-xs"> Change Theme / Skin</span>
	                    <span class="caret"></span>
	                </button>
	                <ul class="dropdown-menu" id="themes">
	                    <!-- <li><a data-value="classic" href="#"><i class="whitespace"></i> Classic</a></li> -->
	                    <li><a data-value="cerulean" href="#"><i class="whitespace"></i> Cerulean</a></li>
	                    <!-- <li><a data-value="cyborg" href="#"><i class="whitespace"></i> Cyborg</a></li>
	                    <li><a data-value="simplex" href="#"><i class="whitespace"></i> Simplex</a></li>
	                    <li><a data-value="darkly" href="#"><i class="whitespace"></i> Darkly</a></li>
	                    <li><a data-value="lumen" href="#"><i class="whitespace"></i> Lumen</a></li>
	                    <li><a data-value="slate" href="#"><i class="whitespace"></i> Slate</a></li>
	                    <li><a data-value="spacelab" href="#"><i class="whitespace"></i> Spacelab</a></li> -->
	                    <li><a data-value="united" href="#"><i class="whitespace"></i> United</a></li>
	                </ul>
	            </div>
	            <!-- theme selector ends -->
	        </div>
	    </div>
	    <!-- topbar ends -->
	    
		<div class="ch-container">
		    <div class="row">
		        <!-- left menu starts -->
		        <tiles:insertAttribute name="menu" ignore="true"/>
		        <!-- left menu ends -->
		
		        <noscript>
		            <div class="alert alert-block col-md-12">
		                <h4 class="alert-heading">Warning!</h4>
		
		                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
		                    enabled to use this site.</p>
		            </div>
		        </noscript>
		
		        <div id="content" class="col-lg-10 col-sm-10">
		            <!-- content starts -->
		            <div>
					    <ul class="breadcrumb">
					        <li>
					            <a href="#">Home</a>
					        </li>
					        <li>
					            <a href="#"><tiles:getAsString name="title" ignore="true"/></a>
					        </li>
					    </ul>
					</div>
		
					<div class="row">
					    <div class="box col-md-12">
					        <div class="box-inner">
					            <div class="box-header well">
					                <h2><i class="glyphicon"></i><tiles:getAsString name="title" ignore="true"/></h2>
					            </div>
					            <div class="box-content">
					                <tiles:insertAttribute name="body" ignore="true"/>
					            </div>
					        </div>
					    </div>
					</div>
				    <!-- content ends -->
			    </div><!--/#content.col-md-0-->
			</div><!--/fluid-row-->
		
		    <hr>
		
		    <footer class="row">
		        <p class="col-md-12 col-sm-12 col-xs-12 text-center copyright">&copy; <a href="http://www.ucfpay.com" target="_blank">UCF-PAY</a> 2012 - 2017</p>
		    </footer>
		
		</div><!--/.fluid-container-->

		<!-- external javascript -->
		
		<script src="${basePath}/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		
		<!-- library for cookie management -->
		<script src="${basePath}/resources/js/jquery.cookie.js"></script>
		
		<!-- bootstrap validator-->
		<script src='${basePath}/resources/bower_components/bootstrapvalidator/dist/js/bootstrapValidator.min.js'></script>
		<script src='${basePath}/resources/bower_components/bootstrapvalidator/dist/js/language/en_US.js'></script>
		
		<!-- calender plugin -->
		<script src='${basePath}/resources/bower_components/moment/min/moment.min.js'></script>
		<script src='${basePath}/resources/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
		<!-- data table plugin -->
		<script src='${basePath}/resources/js/jquery.dataTables.min.js'></script>
		
		<!-- select or dropdown enhancer -->
		<script src="${basePath}/resources/bower_components/chosen/chosen.jquery.min.js"></script>
		<!-- plugin for gallery image view -->
		<script src="${basePath}/resources/bower_components/colorbox/jquery.colorbox-min.js"></script>
		<!-- notification plugin -->
		<script src="${basePath}/resources/js/jquery.noty.js"></script>
		<!-- library for making tables responsive -->
		<script src="${basePath}/resources/bower_components/responsive-tables/responsive-tables.js"></script>
		<!-- tour plugin -->
		<script src="${basePath}/resources/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
		<!-- star rating plugin -->
		<script src="${basePath}/resources/js/jquery.raty.min.js"></script>
		<!-- for iOS style toggle switch -->
		<script src="${basePath}/resources/js/jquery.iphone.toggle.js"></script>
		<!-- autogrowing textarea plugin -->
		<script src="${basePath}/resources/js/jquery.autogrow-textarea.js"></script>
		<!-- multiple file upload plugin -->
		<script src="${basePath}/resources/js/jquery.uploadify-3.1.min.js"></script>
		<!-- history.js for cross-browser state change on ajax -->
		<script src="${basePath}/resources/js/jquery.history.js"></script>
		<!-- application script-->
		<script src="${basePath}/resources/js/app.js"></script>
	</body>
</html>
	

