//构建分页对象
function Page(pageNum, totalRecord) {
	var limit = 10; // 页面显示记录大小
	var pageSize = 5; // 页码大小
	this.totalRecord = totalRecord;// 总页数
	this.pageNum = pageNum;
	this.totalPage = Math.ceil(this.totalRecord / limit);
	// 计算开始记录数
	this.startRecord = (pageNum - 1) * limit;

	if (this.totalPage <= pageSize) {
		this.startPage = 1;
		this.endPage = this.totalPage;
	} else {
		this.startPage = this.pageNum - 2;
		this.endPage = pageNum + 3;
		if (this.startPage < 1) {
			this.startPage = 1;
			this.endPage = pageSize;
		}
		if (this.endPage > this.totalPage) {
			this.endPage = this.totalPage;
			this.startPage = this.totalPage - 5;
		}
	}
};

function saveObject(key, object) {
	window.sessionStorage[key] = JSON.stringify(object);
}
function loadObject(key) {
	var objectString = window.sessionStorage[key];
	return (objectString == null || objectString == "undefined") ? null : JSON
			.parse(objectString);
}
function deleteObject(key) {
	window.sessionStorage[key] = null;
}
/*
 * 前台统一使用方法名为pageRequestData()进行ajax分页，一方面统一规范，另外封装js减少参数，更加易于使用
 */
function renderPaginator(pageNum, totalRecord) {
	var page = new Page(pageNum, totalRecord);
	var source = '<ul class="pagination pull-right" >';
	source += '<span class="paginationText">共 '+page.totalPage+' 页 ,当前第 '+page.pageNum+' 页</span>';
	if(pageNum != 1){
		source += '<li><a href="javascript:pageRequestData(' + 1+ ')">第一页</a></li>';
	}
	if(pageNum == 1){
		source += '<li class="disabled"><a href="javascript:void(0)">上一页</a></li>';
	}else{
		source += '<li><a href="javascript:pageRequestData(' + (pageNum-1) + ')">上一页</a></li>';
	}
	for(var i= page.startPage; i<=page.endPage; i++){
		if(i == pageNum){
			source += '<li class="active"><a href="javascript:pageRequestData(' + i + ')">'+i+'</a></li>';
		}else{
			source += '<li><a href="javascript:pageRequestData(' + i + ')">'+i+'</a></li>';
		}
	}
	if(page.totalPage >5){
		source += '<li><a href="javascript:void(0)">...</a></li>';
	}
	if(pageNum < page.totalPage){
	  source += '<li><a href="javascript:pageRequestData(' + (pageNum+1) + ')">下一页</a></li>';
	}else{
		source += '<li class="disabled"><a href="javascript:void(0)">下一页</a></li>';
	}
	if(page.totalPage != pageNum){
		source += '<li><a href="javascript:pageRequestData(' + page.totalPage + ')">末页</a></li>';
	}
	source +='<span class="paginationText">共 '+totalRecord+' 条记录</span></ul>';
	return source;
}
var ajaxRequest = function(settings, requestObj) {
	var timeout = 20 * 1000;
	if (settings.timeout) {
		this.timeout = settings.timeout;
	}
	var type = "POST";
	if (settings.type) {
		type = settings.type;
	}
	var contentType = "application/json";// 默认:	// "application/x-www-form-urlencoded"
	if (settings.contentType) {
		contentType = settings.contentType;
	}
	var data = requestObj;
	if (contentType == "application/json") {
		data = JSON.stringify(data);
	}
	var options = {
		contentType : contentType,
		dataType : "json",
		cache : false,
		timeout : timeout,
		type : type,
		url : settings.url,
		data : data,
		crossDomain : false, // 跨域请求
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("token", date.getTime());
			//console.log(XMLHttpRequest);
		},
		success : function(data, textStatus, jqXHR) {
			//console.log(data);
			if (data.success) {
				settings.success(data, textStatus, jqXHR);
			} else {
				settings.success(data, textStatus, jqXHR);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 展示对话框
			//settings.error(data, textStatus, jqXHR);
			console.log(XMLHttpRequest);
			console.log(textStatus);
			console.log(errorThrown);
			var message = '系统出错， 请联系管理员！';
			if(textStatus=='timeout' || errorThrown=='timeout'){
				message = '请求服务器超时，请确认网络正常！';
			}else if(textStatus=='error'){
				var errorJson = XMLHttpRequest.responseJSON;
				message = '<h4>远程服务器出错！</h4>错误码：'+errorJson.code+' <br/>'+
				'发邮件反馈--<a href="'+errorJson.moreInfoUrl+'">发邮件</a>';
			}
			BootstrapDialog.show({
				type: BootstrapDialog.TYPE_DANGER,
				title: '系统提示',
				message: message,
				closable: false,
				buttons: [{
                    label: '确认',
                    cssClass: 'btn-danger',
                    action: function(dialogRef){
                        dialogRef.close();
                    }
                }]
			});
		},
	};
	$.ajax(options);
};


