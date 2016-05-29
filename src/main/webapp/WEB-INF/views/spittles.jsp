<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<h1>Welcome to Spittr</h1>
<a href="<c:url value='/spittles' />">Spittles</a>
<a href="<c:url value='/spittles/register' />">Register</a>

<p>

	<c:forEach items="${spittleList}" var="spittle">
		<li id="spittle_<c:out value="${spittle.id}"/>">
			<div class="spittleMessage">
				<c:out value="${spittle.message}" />
			</div>
			<div>
				<span class="spittleTime"><c:out value="${spittle.time}" /></span>
				<span class="spittleLocation"> (<c:out
						value="${spittle.latitude}" />, <c:out
						value="${spittle.longitude}" />)
				</span>
			</div>
		</li>
	</c:forEach>