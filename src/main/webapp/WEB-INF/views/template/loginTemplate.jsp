<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath }" scope="application"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Merchant Back End</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- The styles -->
    <link href='${basePath}/resources/css/jquery.dataTables.min.css' rel='stylesheet'>
	<link href='${basePath}/resources/css/dataTables.bootstrap.min.css' rel='stylesheet'>
    <link id="bs-css" href="${basePath}/resources/css/bootstrap-cerulean.min.css" rel="stylesheet">

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
	<div class="ch-container">
	    <div class="row">
	        
		    <div class="row">
		        <div class="col-md-12 center login-header">
		            <h2>Welcome to OceanB.cn</h2>
		        </div>
		    </div>
	
		    <div class="row">
		        <div class="well col-md-5 center login-box">
	            	<c:choose>
	            		<c:when test="${errorMsg==null}">
	            			<div class="alert alert-info">
		                		Please login with your Username and Password.
				            </div>
	            		</c:when>
	            		<c:otherwise>
	            			<div class="alert alert-danger">
		                		${errorMsg}
				            </div>
	            		</c:otherwise>
	            	</c:choose>
		            <form id="form" class="form-horizontal" action="${basePath}/<tiles:getAsString name="action" ignore="true"/>" method="post">
		                <fieldset>
		                	<div id="userNameGroup">
			                    <div class="input-group input-group-lg">
			                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
			                        <input type="text" class="form-control" id="userName" name="userName" placeholder="Username">
			                    </div>
		                	</div>
		                    <div class="clearfix"></div><br>
							
							<div id="passwordGroup">
			                    <div class="input-group input-group-lg">
			                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
			                        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
			                    </div>
							</div>
		                    <div class="clearfix"></div>
		
		                    <div class="input-prepend">
		                        <label class="remember" for="remember"><input type="checkbox" id="remember" onclick="saveUserInfo()"> Remember me</label>
		                    </div>
		                    <div class="clearfix"></div>
		
		                    <p class="center col-md-5">
		                        <button type="submit" class="btn btn-primary">Login</button>
		                    </p>
		                </fieldset>
		            </form>
		        </div>
		    </div><!--/row-->
		</div><!--/fluid-row-->
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
	<script>
		$(function(){
			
			$("#userName").val("");
		    if ($.cookie("rmbUser") == "true") {
		        $("#remember").prop("checked", true);
		        $("#userName").val($.cookie("userName"));
		    }
			
			
			 $('#form').bootstrapValidator({
		        message: 'This value is not valid',
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	userName: {
		        		group: '#userNameGroup',
		                validators: {
		                    notEmpty: {
		                        message: 'The Username is required and cannot be empty'
		                    }
		                }
		            },
		            password: {
		            	group: '#passwordGroup',
		                validators: {
		                    notEmpty: {
		                        message: 'The Password is required and cannot be empty'
		                    }
		                }
		            }
		        }
		    });
		});
			
		function saveUserInfo() {
		    if (($("#remember").prop("checked") == true) || ($("#remember").prop("checked") == "checked")) {
		        var userName = $("#userName").val();
		        $.cookie("rmbUser", "true", { expires: 7 }); // 存储一个带7天期限的 cookie 是否已经选择记录用户名
		        $.cookie("userName", userName, { expires: 7 }); // 存储一个带7天期限的 cookie
		    }
		    else {
		        $.cookie("rmbUser", null);        // 删除 cookie
		        $.cookie("userName", null);
		    }
		}
		</script>
</body>
</html>
