<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

	<h1>Welcome to Spittr</h1>
	<a href="<c:url value='/spittles' />">Spittles</a>
	<a href="<c:url value='/spitter/register' />">Register</a>

	<p>

		<h3>Your Profile</h3>
        <c:out value="${spitter.username}" /><br/>
        <c:out value="${spitter.firstName}" />
            <c:out value="${spitter.lastName}" />


