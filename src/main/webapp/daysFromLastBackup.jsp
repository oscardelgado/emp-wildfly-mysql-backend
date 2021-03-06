<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html  xmlns="http://www.w3.org/1999/xhtml" xml:lang="${fn:substring(renderContext.request.locale,0,2)}">
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Days from last backup of DB</title>
</head>
<body>
        <jsp:useBean id="now" class="java.util.Date" scope="request"/>
        <fmt:parseNumber
	        value="${now.time}"
        	integerOnly="true" var="nowMs" scope="request"/>

        <c:import var="lastBackupDateSecString" url="https://www.dropbox.com/s/3t5ufqxa9b9eqy1/last_backup_date.txt?raw=1" />
        <fmt:parseNumber
        	value="${lastBackupDateSecString}"
        	integerOnly="true" var="lastBackupDateSec" scope="request"/>
        
	<c:set value="${nowMs / 1000 - lastBackupDateSec}" var="diffSec"/>

        <c:choose>
                <c:when test="${diffSec / (60 * 60 * 24) lt 1}">Less than a day. OK</c:when>
                <c:otherwise>${diffSec / (60 * 60)} hours(s) ago. FAILED</c:otherwise>
        </c:choose>
</body>
</html>
