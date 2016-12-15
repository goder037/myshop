<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>商品类别列表</title>

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
                    		商品类别查询
                    	</div>
                    	<div class="panel-body">
                    		<div class="row">
				                <div class="col-lg-12">
				                	<form class="form-horizontal" id="queryItemCategoryForm">
				                		<div class="col-lg-3">
									  		<div class="input-group">
												<span class="input-group-addon" id="basic-addon1">类别名称:</span>
												<input type="text" name="name" class="form-control" placeholder="规格名称">
											</div>
										</div>
										<div class="col-lg-6">
									  		<div class="input-group">
												<span class="input-group-addon" id="basic-addon1">创建日期:</span>
												<input type="text" class="form-control form-datetime" name="createTimeBegin" placeholder="开始日期">
												<span  class="input-group-addon">至</span>
												<input type="text" name="createTimeEnd" class="form-control form-datetime" placeholder="结束日期">
											</div>
										</div>
										<div class="col-lg-1">
									  		<button type="button" class="btn btn-primary" onclick="queryItemCategory()">搜索</button>
										</div>
										<div class="col-lg-2">
									  		<button type="button" class="btn btn-success" onclick="addCategory()">新增顶层类别</button>
										</div>
				                	</form>
				                </div>
				            </div>
                    	</div>
                    </div>
					<div class="panel panel-default">
						<div class="panel-heading">商品类别列表查询</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
				            <div class="row">
								<table width="100%"	id="itemCategoryData" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>类别Id</th>
											<th style="width:250px;">类别名称</th>
											<th>子类别数目</th>
											<th>商品数量</th>
											<th>排序值</th>
											<th>类目等级</th>
											<th>创建时间</th>
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
	
	<%@include file="../templete/footjs.jsp" %>

	<script id="itemCategoryList" type="text/html">
	[[each list as value i]]
    	<tr expand="false" parent="[[value.parentId]]">
			[[if value.level>1]]
				<td></td>
			[[else]]
				<td class="text-center">
					<a href="javascript:void(0)" onclick="expandOrfoldCategory(this, [[value.id]], [[value.level]])"><span class="glyphicon glyphicon-plus"></span></href>
				</td>
			[[/if]]
			<td>
				[[if value.level>1]]
					<a href="javascript:void(0)" onclick="expandOrfoldCategory(this, [[value.id]], [[value.level]])">
						<span style="padding-left: [[(value.level-2)*30+10]]px;" class="glyphicon glyphicon-plus"></span>
					</a>
				[[/if]]
				[[value.name]]
			</td>
			<td>[[value.subCategoryNum]]</td>
			<td>[[value.itemsNum]]</td>
			<td>[[value.rank]]</td>
			<td>[[value.level]]</td>
			<td>[[value.createTime]]</td>
			<td>
				<button type="button" onclick="addCategory([[value.id]])" class="btn btn-sm btn-info">添加子类别</button>
				<button type="button" onclick="addItemForCategory([[value.id]])" class="btn btn-sm btn-success">添加商品</button>
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
	function queryItemCategory(){
		var params = formDataToJson($("#queryItemCategoryForm"));
		params.length = 10;
		params.start = 0;
		ajaxRequest({
	    	type : "POST",
			url : "/items/listItemsCategory.json",
			success: function(data, textStatus, jqXHR){
		    	var ittemCategoryListHtml = template("itemCategoryList", data);
			     $("#ittemCategoryBody").html(ittemCategoryListHtml);
			     var paginationText = renderPaginator(pageNum, data.total);
			     $("#paginationText").html(paginationText);
		    }
	    }, params);
	}
	function addCategory(categoryId){
		//$('#comfirmDeleteDialog').modal('show');
		BootstrapDialog.show({
			title: '添加类别',
            message: function(dialog) {
                var $message = $('<div></div>');
                var pageToLoad = dialog.getData('pageToLoad');
                $message.load(pageToLoad);
                return $message;
            },
            data: {
                'pageToLoad': '/items/additemsCategory.html'
            },
            buttons: [{
            	id:"addBtn",
                label: '添加',
                cssClass: 'btn-success',
                action: function(dialogRef){
                	var itemCategoryAddForm = dialogRef.getModalBody().find('#itemCategoryAddForm');
                	var verifyFlag = verifyForm(itemCategoryAddForm);
                	if(verifyFlag){
                		var params = formDataToJson(itemCategoryAddForm);
                		if(categoryId){
                			params.parentId = categoryId;
                		}
                    	ajaxRequest({
            		    	type : "POST",
            				url : "/items/addItemsCategory.json",
            				success: function(data, textStatus, jqXHR){
            					if(data.success){
            						$.scojs_message('添加成功', $.scojs_message.TYPE_OK);
            						pageRequestData(1);
                                	dialogRef.close();
            					}else{
            						console.log(data);
            						console.log(dialogRef.getModalBody());
            						if(data.reasons){
            							var i = 1;
            							for(var key in data.reasons)
            							{
            								i++;
            								var errorNode = dialogRef.getModalBody().find("[name="+key+"]");
            								errorNode.parent().prev('label').addClass('error-message');
            								errorNode.removeClass("success").parent().addClass("has-error has-feedback");
            								errorNode.after("<span class='glyphicon glyphicon-remove form-control-feedback' onclick='cleanInput(this)'></span>");
            								errorNode.attr("data-original-title", data.reasons[key]);
            								if(i==1){
            									errorNode.focus();
            								}
            							}
            						}
            					}
            			    }
            		    }, params);
                	}
                }
            },
            {
            	id:"backBtn",
                label: '返回',
                cssClass: 'btn-warning',
                action: function(dialogRef){
                    dialogRef.close();
                }
            }],
            onshown: function(){
            	// 调用提示工具（Tooltip）插件
 		    	initTooltip();

 		    	// 输入项验证
 		    	$(document).delegate("[data-pattern]","blur",function(){
 		        	verifyOneField(this);
 		    	});

 		    	// 通过类 .submitForm 绑定提交时遍历输入项事件
 		    	$("form.valid-form").on("submit",function(){
 		        // 创建错误计数变量errNum，并把表单对象传进verifyForm()中
 		        	var verify = verifyForm($(this));
 		        	if(verify){
 		            	return true;
 		        	}else{
 		            	return false;
 		        	}
 		    	});
            }
        });
	}
	
	function deleteCategory(deleteNode, categoryId){
		 BootstrapDialog.confirm({
			 title: '系统警告',
			 message:'<h4 style="color:#CB4F2E"><span class="glyphicon glyphicon-exclamation-sign"></span>确认删除该商品类别？</h4>',
			 type: BootstrapDialog.TYPE_WARNING,
	         btnOKLabel: '取消', 
			 btnCancelLabel: '删除', 
	         btnCancelClass: 'btn-danger',
	         callback: function(result) {
	               if(result) {
	                   //alert('Yup.');
	               }else {
	               	params = "categoryId="+categoryId;
	               	ajaxRequest({
	       		    	type : "POST",
	       		    	contentType:"application/x-www-form-urlencoded",
	       				url : "/items/deleteItemsCategory.json",
	       				success: function(data, textStatus, jqXHR){
	       					if(data.success){
	       						$.scojs_message('删除成功', $.scojs_message.TYPE_OK);
	       						$(deleteNode).parent().parent();
	       					}else{
	       						console.log(data);
	       					}
	       			    }
	       		    }, params);
	               }
	           }
		 });
	}
	function updateCategory(updateNode, categoryId){
		BootstrapDialog.show({
			title: '更改商品类别',
            message: function(dialog) {
                var $message = $('<div></div>');
                var pageToLoad = dialog.getData('pageToLoad');
                $message.load(pageToLoad);
                return $message;
            },
            data: {
                'pageToLoad': '/items/updateitemsCategory.html'
            },
            buttons: [{
            	id:"addBtn",
                label: '更改',
                cssClass: 'btn-success',
                action: function(dialogRef){
                	var itemCategoryUpdateForm = dialogRef.getModalBody().find('#itemCategoryUpdateForm');
                	var verifyFlag = verifyForm(itemCategoryUpdateForm);
                	if(verifyFlag){
                		var params = formDataToJson(itemCategoryUpdateForm);
                    	ajaxRequest({
            		    	type : "POST",
            				url : "/items/updateItemsCategory.json",
            				success: function(data, textStatus, jqXHR){
            					if(data.success){
            						$.scojs_message('更改成功', $.scojs_message.TYPE_OK);
            						pageRequestData(1);
                                	dialogRef.close();
            					}else{
            						console.log(data);
            						console.log(dialogRef.getModalBody());
            					}
            			    }
            		    }, params);
                	}
                }
            },
            {
            	id:"backBtn",
                label: '返回',
                cssClass: 'btn-warning',
                action: function(dialogRef){
                    dialogRef.close();
                }
            }],
            onshown: function(dialogRef){
            	// 调用提示工具（Tooltip）插件
 		    	initTooltip();
 		    	// 输入项验证
 		    	$(document).delegate("[data-pattern]","blur",function(){
 		        	verifyOneField(this);
 		    	});
 		    	// 通过类 .submitForm 绑定提交时遍历输入项事件
 		    	$("form.valid-form").on("submit",function(){
 		        // 创建错误计数变量errNum，并把表单对象传进verifyForm()中
 		        	var verify = verifyForm($(this));
 		        	if(verify){
 		            	return true;
 		        	}else{
 		            	return false;
 		        	}
 		    	});
 		    	 var updateNodeTr = $(updateNode).parent().parent();
                 var tdNodes = updateNodeTr.find("td");
                 var nameText = $(tdNodes[1]).text().trim();
                 var rankText = $(tdNodes[4]).text().trim();
                 var inputNodes = dialogRef.getModalBody().find("input");
                 $(inputNodes[0]).val(nameText);
                 $(inputNodes[1]).val(rankText);
            }
        });
	}
	function expandOrfoldCategory(categoryNode, categoryId, level){
		$(categoryNode).children().toggleClass("glyphicon-plus");
		$(categoryNode).children().toggleClass("glyphicon-minus");
		var categoryTr = $(categoryNode).parent().parent();
		//console.log(categoryTr);
		var expandFlag = categoryTr.attr("expand");
		if(expandFlag=="true"){
			categoryTr.attr("expand", "false");
			//categoryTr.parent().remove("[parent='"+categoryId+"']");
			$("tr").remove("[parent='"+categoryId+"']");
		}else{
			params = "parentId="+categoryId;
			ajaxRequest({
		    	type : "POST",
		    	contentType:"application/x-www-form-urlencoded",
				url : "/items/getSubItemsCategory.json",
				success: function(data, textStatus, jqXHR){
					categoryTr.attr("expand", "true");
					var expandItemCategoryHtml = template("itemCategoryList", data);
					//console.log(expandItemCategoryHtml);
					categoryTr.after(expandItemCategoryHtml);
			    }
		    }, params);
		}
	}
	
	function pageRequestData(pageNum){ 
		var startRecord = (pageNum - 1) * 10;
		var params = {"length":10,"start":startRecord, "level":1};
	    ajaxRequest({
	    	type : "POST",
			url : "/items/listItemsCategory.json",
			success: function(data, textStatus, jqXHR){
		    	var ittemCategoryListHtml = template("itemCategoryList", data);
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
