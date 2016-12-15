<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<link rel="shortcut icon" type="image/ico" href="/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>商品品牌列表</title>

<%@include file="../templete/cssHeader.jsp"%>

</head>

<body>

	<div id="wrapper">
		<!-- Navigation -->
		<%@include file="../templete/navheader.jsp"%>
        <!-- Navigation -->
		<%@include file="../templete/menu.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="#">首页</a> <span class="divider">/商品类别列表</span></li>
					</ul>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
                    	<div class="panel-heading">
                    		商品品牌查询
                    	</div>
                    	<div class="panel-body">
                    		<div class="row" style="">
				                <div class="col-sm-12">
				                	<form class="form-horizontal">
				                		<div class="col-sm-3">
									  		<div class="input-group">
												<span class="input-group-addon" id="basic-addon1">品牌名称:</span>
												<input type="text" class="form-control" placeholder="规格名称" aria-describedby="basic-addon1">
											</div>
										</div>
										<div class="col-sm-6">
									  		<div class="input-group">
												<span class="input-group-addon" id="basic-addon1">创建日期:</span>
												<input type="text" class="form-control form-datetime" placeholder="开始日期" aria-describedby="basic-addon1">
												<span  class="input-group-addon">至</span>
												<input type="text" class="form-control form-datetime" placeholder="结束日期" aria-describedby="basic-addon1">
											</div>
										</div>
										<div class="col-sm-1">
									  		<button type="button" class="btn btn-primary">搜索</button>
										</div>
										<div class="col-sm-2">
									  		<button type="button" class="btn btn-success" onclick="showSpec('新增')">新增</button>
										</div>
				                	</form>
				                </div>
				            </div>
                    	</div>
                    </div>
					<div class="panel panel-default">
						<div class="panel-heading">商品品牌列表查询</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
				            <div class="row">
								<table width="100%"	id="itemCategoryData" class="table table-striped table-bordered table-hover">
									<thead>
										<tr class="">
											<th>品牌Id</th>
											<th>品牌名称</th>
											<th>创建时间</th>
											<th>Logo图片</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="ittemCategoryBody">
									
									</tbody>
								</table>
								<!-- /.table-responsive -->
							</div>
						</div>
						<!-- /.panel-body -->
						<div class="panel-footer">
	                        <div class="row">
	                            <div class="col-lg-12" id="paginationText">
	                                
	                            </div>
	                        </div>
                    	</div>
                    	<!-- /.panel-footer -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<a id="message_trigger_ok" href="#">Click to see the info message</a>
	
	<%@include file="../templete/footjs.jsp" %>

	<script id="itemCategoryList" type="text/html">
	[[each list as value i]]
    	<tr>
			<td>[[value.id]]</td>
			<td>[[value.name]]</td>
			<td>[[value.createTime]]</td>
			<td>
				[[if value.status]]
					<span class="label label-success">正常</span>
				[[/if]]
			</td>
		</tr>
	[[/each]]
    </script>
</body>
<script type="text/javascript">
	template.config("openTag", "[[");
	template.config("closeTag", "]]");
	var date = new Date();
	 $('#message_trigger_ok').on('click', function(e) {
		    e.preventDefault();
		    $.scojs_message('保存成功！', $.scojs_message.TYPE_OK);
	});
	
	function pageRequestData(pageNum){ 
		var startRecord = (pageNum - 1) * 10;
		var params = {"length":10,"start":startRecord};
	    ajaxRequest({
	    	type : "POST",
			url : "/items/itemsBrands.json",
			success: function(data, textStatus, jqXHR){
		    	var ittemCategoryListHtml = template("itemCategoryList", data);
				// console.log(data.total);
			     $("#ittemCategoryBody").html(ittemCategoryListHtml);
			     var paginationText = renderPaginator(pageNum, data.total);
			     $("#paginationText").html(paginationText);
		    }		    
	    }, params);
	}
	$(function(){
		pageRequestData(1);
	});
</script>
</html>
