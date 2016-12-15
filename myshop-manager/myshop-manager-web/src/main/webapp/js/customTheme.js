$(function() {

	// 4、调用提示工具（Tooltip）插件
	initTooltip();

	// 5、输入项验证
	$(document).delegate("[data-pattern]", "blur", function() {
		verifyOneField(this);
	});

	// 6、通过类 .submitForm 绑定提交时遍历输入项事件
	$("form.valid-form").on("submit", function() {
		// 创建错误计数变量errNum，并把表单对象传进verifyForm()中
		var verify = verifyForm($(this));
		if (verify) {
			return true;
		} else {
			return false;
		}
	});

	$('.form-datetime').datetimepicker({
		language : 'zh-CN',
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		autoclose : 1,
		format : 'yyyy-mm-dd'
	});

});

// 调用提示工具（Tooltip）
function initTooltip() {
	$("[data-toggle='tooltip']").tooltip({
		'trigger' : 'focus',
		'placement' : 'top'
	});
	// 绑定 show.bs.tooltip 事件，如果当前输入项输入正确，即不调用提示工具
	$("[data-toggle='tooltip']").on('show.bs.tooltip', function() {
		if ($(this).hasClass("success"))
			return false;
	});
}

// 输入项验证，通过在输入项上添加属性 data-pattern="正则表达式" 来绑定，并通过属性 data-required="required"
// 确定是否为必填项;
function verifyOneField(field) {
	var thisVal = $(field).val(), // 输入项的值;
	required = $(field).attr("data-required"), // 是否为必填项;
	pattern = new RegExp($(field).attr("data-pattern")), // 正则表达式对象;
	result = pattern.test(thisVal); // 正则表达式的结果;
	if (thisVal != "") { // 输入项的值不为空时;
		if (result) { // 正则表达式的结果为true时;
			$(field).addClass("success").parent().removeClass(
					"has-error has-feedback");
			$(field).siblings(".glyphicon").removeClass("glyphicon-remove")
					.removeClass("glyphicon-success");
		} else { // 正则表达式的结果为false时;
			$(field).parent().prev('label').addClass('error-message');
			$(field).removeClass("success").parent().addClass("has-error has-feedback");
			$(field).after("<span class='glyphicon glyphicon-remove form-control-feedback' onclick='cleanInput(this)'></span>");
			$(field).focus();
		}
	} else { // 输入项值为空时
		$(field).removeClass("success").parent().removeClass("has-success");
		if (required) { // 当为必填项时,增加错误提示样式.has-error;
			$(field).parent().addClass("has-error has-feedback");
			$(field)
					.after(
							"<span class='glyphicon glyphicon-remove form-control-feedback' onclick='cleanInput(this)'></span>");
			$(field).focus();
		} else { // 当为选填项时，移除错误提示样式.has-error;
			$(field).parent().removeClass("has-error");
		}
	}
}
function formDataToJson(formNode) {
	var formArray = formNode.serializeArray();
	var dataArray = {};
	$.each(formArray, function() {
		if (dataArray[this.name]) {
			if (!dataArray[this.name].push) {
				dataArray[this.name] = [ dataArray[this.name] ];
			}
			dataArray[this.name].push(this.value || '');
		} else {
			dataArray[this.name] = this.value || '';
		}
	});
	return dataArray;
}

// 验证表单函数，form为表单的jquery对象
function verifyForm(form) {
	// form对象中带有data-pattern属性的输入项的集合
	var errNum = 0, patternArray = form.find("[data-pattern]");
	// 遍历要验证的项
	patternArray
			.each(function() {
				// 条件为带有错误类或未通过验证的必填项
				if ($(this).parent().hasClass("has-error")
						|| ($(this).is("[data-required]") && !$(this).hasClass(
								"success"))) {
					errNum++;
					$(this).parent().addClass("has-error has-feedback");
					$(this)
							.after(
									"<span class='glyphicon glyphicon-remove form-control-feedback' onclick='cleanInput(this)'></span>");
					$(this).focus();
					return false;
				}
			});
	if (errNum > 0) {
		return false;
	} else {
		return true;
	}
}
// 构建分页对象
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
	source += '<span class="paginationText">共 ' + page.totalPage + ' 页 ,当前第 '
			+ page.pageNum + ' 页</span>';
	if (pageNum != 1) {
		source += '<li><a href="javascript:pageRequestData(' + 1
				+ ')">首页</a></li>';
	}
	if (pageNum == 1) {
		source += '<li class="disabled"><a href="javascript:void(0)">上一页</a></li>';
	} else {
		source += '<li><a href="javascript:pageRequestData(' + (pageNum - 1)
				+ ')">上一页</a></li>';
	}
	for (var i = page.startPage; i <= page.endPage; i++) {
		if (i == pageNum) {
			source += '<li class="active"><a href="javascript:pageRequestData('
					+ i + ')">' + i + '</a></li>';
		} else {
			source += '<li><a href="javascript:pageRequestData(' + i + ')">'
					+ i + '</a></li>';
		}
	}
	if (page.totalPage > 5) {
		source += '<li><a href="javascript:void(0)">...</a></li>';
	}
	if (pageNum < page.totalPage) {
		source += '<li><a href="javascript:pageRequestData(' + (pageNum + 1)
				+ ')">下一页</a></li>';
	} else {
		source += '<li class="disabled"><a href="javascript:void(0)">下一页</a></li>';
	}
	if (page.totalPage != pageNum) {
		source += '<li><a href="javascript:pageRequestData(' + page.totalPage
				+ ')">末页</a></li>';
	}
	source += '<span class="paginationText">共 ' + totalRecord
			+ ' 条记录</span></ul>';
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
	var contentType = "application/json";// 默认: //
	// "application/x-www-form-urlencoded"
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
			// console.log(XMLHttpRequest);
		},
		success : function(data, textStatus, jqXHR) {
			// console.log(data);
			if (data.success) {
				settings.success(data, textStatus, jqXHR);
			} else {
				settings.success(data, textStatus, jqXHR);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 展示对话框
			// settings.error(data, textStatus, jqXHR);
			// console.log(XMLHttpRequest);
			// console.log(textStatus);
			// console.log(errorThrown);
			var message = '系统出错， 请联系管理员！';
			if (textStatus == 'timeout' || errorThrown == 'timeout') {
				message = '请求服务器超时，请确认网络正常！';
			} else if (textStatus == 'error') {
				var errorJson = XMLHttpRequest.responseJSON;
				message = '<h4>远程服务器出错！</h4>错误码：' + errorJson.code + ' <br/>'
						+ '发邮件反馈--<a href="' + errorJson.moreInfoUrl
						+ '">发邮件</a>';
			}
			BootstrapDialog.show({
				type : BootstrapDialog.TYPE_DANGER,
				title : '系统提示',
				message : message,
				closable : false,
				buttons : [ {
					label : '确认',
					cssClass : 'btn-danger',
					action : function(dialogRef) {
						dialogRef.close();
					}
				} ]
			});
		},
	};
	$.ajax(options);
};

var formValidation = function(formNode, rules) {

};
