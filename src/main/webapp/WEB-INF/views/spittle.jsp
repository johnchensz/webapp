<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/style.css' />" />
</head>
<body>
	<h1>Welcome to Spittr</h1>
	<a href="<c:url value='/spittles' />">Spittles</a>
	<a href="<c:url value='/spittles/register' />">Register</a>

	<p>

		<ul>
			<li id="spittle_<c:out value="${spittle.id}" />" >
				<div class="spittleMessage">
					<c:out value="${spittle.message}" />
				</div>
				<div>
					<span class="spittleTime"><c:out value="${spittle.time}" /></span>
					<span class="spittleLocation"> 
					(<c:out value="${spittle.latitude}" />, 
					<c:out value="${spittle.longitude}" />)
					</span>
				</div>
			</li>
		</ul>
</body>
</html>
