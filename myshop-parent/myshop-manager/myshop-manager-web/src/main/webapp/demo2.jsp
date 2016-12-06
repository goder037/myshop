<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>demo2</title>
</head>
<body>
<c:forEach items="${listDemo}" var="demo" >
	${demo.id}---------${demo.name}<br/>
</c:forEach>
</body>
</html>