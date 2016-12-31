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

<title>商品列表</title>

<%@include file="../templete/cssHeader.jsp"%>
<link href="/third-party/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" type="text/css">

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
						<li><a href="#">首页</a> <span class="divider">/商品列表</span></li>
					</ul>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
                    	<div class="panel-heading">
                    		商品查询
                    	</div>
                    	<div class="panel-body">
                    		<form class="form-horizontal" id="queryItemForm">
                    			<div class="row" style="padding-bottom: 16px;">
			                		<div class="col-sm-3">
								  		<div class="input-group">
											<span class="input-group-addon" id="basic-addon1">商品名称:</span>
											<input type="text" name="name" class="form-control" placeholder="商品名称">
										</div>
									</div>
									<div class="col-sm-3">
								  		<div class="input-group">
											<span class="input-group-addon" id="basic-addon1">品牌名称:</span>
											<input type="text" name="name" class="form-control" placeholder="品牌名称">
										</div>
									</div>
									<div class="col-sm-6">
								  		<div class="input-group">
											<span class="input-group-addon" id="basic-addon1">创建日期:</span>
											<input type="text" name="createTimeBegin" class="form-control form-datetime" placeholder="开始日期">
											<span  class="input-group-addon">至</span>
											<input type="text" name="createTimeEnd" class="form-control form-datetime" placeholder="结束日期">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-2">
										<select class="form-control selectpicker show-tick" name="itemClient" title="请选择销售渠道">
			                            	<option value="0">云商城</option>
			                            	<option value="1">微信</option>
			                            	<option value="1">android端</option>
                                      	</select>
									</div>
									<div class="col-sm-3">
										<select class="form-control selectpicker show-tick" data-live-search="true" name="itemCategory" id="itemCategory" title="请选择商品类别">
                                      	</select>
									</div>
									<div class="col-sm-2">
										<select class="form-control selectpicker show-tick" title="库存状态">
				                            <option value="0">库存充足</option>
				                            <option value="1">库存预警</option>
                                       	</select>
									</div>
									<div class="col-sm-1">
								  		<button type="button" class="btn btn-primary" onclick="queryItem()">搜索</button>
									</div>
				                </div>
				            </form>
                    	</div>
                    </div>
					<div class="panel panel-default">
						<div class="panel-heading">商品列表查询</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
				            <div class="row">
								<table width="100%"	class="table table-striped table-bordered table-hover">
									<thead>
										<tr class="">
											<th>商品图片</th>
											<th>商品名称</th>
											<th>SPU商家编码</th>
											<th>商品类目</th>
											<th>品牌</th>
											<th>销售价</th>
											<th>销售渠道</th>
											<th>库存</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="itemBody">
									
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
	
	<%@include file="../templete/footjs.jsp" %>
	<script type="text/javascript" src="/third-party/bootstrap-select/js/bootstrap-select.js" charset="UTF-8"/></script>
	<script type="text/javascript" src="/third-party/bootstrap-select/js/i18n/defaults-zh_CN.js" charset="UTF-8"/></script>

	<script id="itemList" type="text/html">
	[[each list as value i]]
    	<tr>
			<td>
				<img src="[[value.imgLogo]]" width="90px;" height="60px;"/>
			</td>
			<td>[[value.title]]</td>
			<td>[[value.sellerId]]</td>
			<td>[[value.itemCategoryName]]</td>
			<td>[[value.itemBrandName]]</td>
			<td>[[value.price]]</td>
			<td>[[value.saleClients]]</td>
			<td>[[value.num]]</td>
			<td>
				<button type="button" onclick="viewItemDetail([[value.id]])" class="btn btn-sm btn-success">预览</button>
				<button type="button" onclick="updateCategory(this, [[value.id]])" class="btn btn-sm btn-warning">修改</button>
				<button type="button" onclick="deleteCategory(this, [[value.id]])" class="btn btn-sm btn-danger">删除</button>
			</td>
		</tr>
	[[/each]]
    </script>
</body>
<script type="text/javascript">
	template.config("openTag", "[[");
	template.config("closeTag", "]]");
	var date = new Date();
	function queryItem(){
		var params = formDataToJson($("#queryItemForm"));
		params.length = 10;
		params.start = 0;
		ajaxRequest({
	    	type : "POST",
			url : "/items/listItem.json",
			success: function(data, textStatus, jqXHR){
		    	var ittemListHtml = template("itemList", data);
			     $("#itemBody").html(ittemListHtml);
			     var paginationText = renderPaginator(1, data.total);
			     $("#paginationText").html(paginationText);
		    }
	    }, params);
	}
	function pageRequestData(pageNum){ 
		var startRecord = (pageNum - 1) * 10;
		var params = {"length":10,"start":startRecord};
	    ajaxRequest({
	    	type : "POST",
			url : "/items/listItem.json",
			success: function(data, textStatus, jqXHR){
		    	var ittemListHtml = template("itemList", data);
				// console.log(data.total);
			     $("#itemBody").html(ittemListHtml);
			     var paginationText = renderPaginator(pageNum, data.total);
			     $("#paginationText").html(paginationText);
		    }		    
	    }, params);
	}
	$(function(){
		pageRequestData(1);
		var params = {"start":0, "length":-1};
		ajaxRequest({
		   	type : "POST",
			url : "/items/listItemsCategory.json",
			success: function(data, textStatus, jqXHR){
				var optionStr = "";
				$.each(data.list, function(i, value){
					optionStr += '<option value='+value.id+'>'+value.name+'</option>';
				});
			   	$("#itemCategory").html(optionStr);
			   	$('#itemCategory').selectpicker('refresh');
			}	    
		   }, params);
	});
</script>
</html>
