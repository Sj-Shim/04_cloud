<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    long currentTimeMillis = System.currentTimeMillis();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
    <title>NH 로우코드 포탈 (내부망)</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/default.css?<%= currentTimeMillis %>" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/dragndrop.css?<%= currentTimeMillis %>" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css?<%= currentTimeMillis %>" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/table.css?<%= currentTimeMillis %>" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/layout.css?<%= currentTimeMillis %>" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/sub.css?<%= currentTimeMillis %>" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css?<%= currentTimeMillis %>" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/details.css?<%= currentTimeMillis %>" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css?<%= currentTimeMillis %>" >
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.6.1.js?<%= currentTimeMillis %>"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jsrender.js?<%= currentTimeMillis %>"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/move.js?<%= currentTimeMillis %>"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js?<%= currentTimeMillis %>"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/callService.js?<%= currentTimeMillis %>"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commonFn.js?<%= currentTimeMillis %>"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dragndrop.js?<%= currentTimeMillis %>"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/fileInfo.js?<%= currentTimeMillis %>"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/userMng.js?<%= currentTimeMillis %>"></script>
    <script type="text/javascript" defer src="${pageContext.request.contextPath}/static/js/uiControl.js?<%= currentTimeMillis %>"></script>
	<script type="text/javascript" >
		let csrfParameterName = "${_csrf.parameterName}";
	    let csrfToken = "${_csrf.token}";
	</script>
</head>