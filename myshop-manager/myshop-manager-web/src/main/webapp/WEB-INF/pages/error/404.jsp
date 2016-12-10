<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>index</title>

   <%@include file="../templete/cssHeader.jsp"%>

</head>

<body>

    <div id="wrapper">
		<%@include file="../templete/navheader.jsp"%>
        <!-- Navigation -->
		<%@include file="../templete/menu.jsp"%>
       <!-- 页面内容 -->
		<div id="page-wrapper">
			<div class="row">
			    <div class="col-xs-12" style="margin-top: 100px;text-align: center;">
			       <div class="col-xs-6" style="text-align:right;">
			      	 <img  src="/images/error.png" style="width:100px;height:auto;"/>
			       </div>
			       <div class="col-xs-6" style="text-align:left;">
			       <div style="color: #2494F2;font-size: 80px;">404</div>
			       <div style="color: grey;">服务器找不到请求的网页</div>
			       </div>
			    </div>
			</div>
		</div>

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="/third-party/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/third-party/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/third-party/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/js/sb-admin-2.js"></script>

</body>

</html>
