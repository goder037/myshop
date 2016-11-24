package com.rocket.myshop.common.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class ExceptionData implements Serializable{

	private static final long serialVersionUID = -7438759136863987879L;

	//响应头中的 HTTP 状态码相同,
	private String status;
	
	//特定 REST API 的异常编码，考虑到仅有 24 个熟知的通用 HTTP 异常码存在局限性，
	private String code;
	
	//userMsg 是一个可读性很好的异常消息，可能会被直接展示给应用程序端的用户
	private String userMsg;
	
	//所有对 REST API 的开发者而言可能有用的技术信息。可以在这里包含异常信息，跟踪堆栈，或者其他
	private String devMsg;
	
	//moreInfo 属性指定一个 URL， 任何看到该 URL 的人都可在浏览器中点击（或复制粘贴）。
	//URL 的目标网页应该全面的描述出现的异常情况，以及潜在的解决方案，从而帮助他们解决异常情况。
	private String moreInfo;
	
}
