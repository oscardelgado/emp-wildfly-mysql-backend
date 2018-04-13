<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html  xmlns="http://www.w3.org/1999/xhtml" xml:lang="${fn:substring(renderContext.request.locale,0,2)}">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Days from last backup of DB</title>
</head>
<body bgcolor="white">

	<jsp:useBean id="now" class="java.util.Date" scope="request"/>
	<fmt:parseNumber
	value="${ now.time  / (1000*60*60*24) }"
	integerOnly="true" var="nowDays" scope="request"/>

	<c:import var="lastBackupDateSec" url="https://www.dropbox.com/s/ahag0gar11kdxef/last_backup_date.txt?raw=1" />
	<fmt:parseNumber
	value="${ lastBackupDateSec / (60*60*24) }"
	integerOnly="true" var="otherDays" scope="request"/>

	<c:set value="${nowDays - otherDays}" var="dateDiff"/>

	<c:choose>
	<c:when test="${dateDiff eq 0}">today</c:when>
	<c:when test="${dateDiff eq 1}">yesterday</c:when>
	<c:otherwise>${dateDiff} day(s) ago</c:otherwise>
</c:choose>
</body>
</html>
