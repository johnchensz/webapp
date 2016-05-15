<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/style.css' />" />
</head>
<body>
	<h1>Welcome to Spittr</h1>
	<a href="<c:url value='/spittles' />">Spittles</a>
	<a href="<c:url value='/spitter/register' />">Register</a>

	<p>

		<h3>Your Profile</h3>
        <c:out value="${spitter.username}" /><br/>
        <c:out value="${spitter.firstName}" />
            <c:out value="${spitter.lastName}" />

</body>
</html>

