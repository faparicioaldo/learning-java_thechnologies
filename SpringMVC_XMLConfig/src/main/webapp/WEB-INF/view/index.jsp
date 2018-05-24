<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>

		<h1>Welcome to index!!</h1>

 		<c:url var="messageUrl" value="/home/users" context="/SpringMVC_XMLConfig" />
 		<a href="${messageUrl}">Click to enter to go home</a>

	</body>
</html>
