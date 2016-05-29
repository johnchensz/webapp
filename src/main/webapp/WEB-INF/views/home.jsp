<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<%@ page isELIgnored="false"%>

<h1>
	<s:message code="spittr.welcome" />
</h1>
<a href="<c:url value='/spittles' />">Spittles</a>
<a href="<s:url value='/spitter/register' />">Register</a>

<p>
	<s:escapeBody htmlEscape="true">
		<h1>Hello</h1>
	</s:escapeBody>
<p>

	<s:url value="/spittles" var="spittlesJSUrl" javaScriptEscape="true">
		<s:param name="max" value="60" />
		<s:param name="count" value="20" />
	</s:url>
	<script>
			var spittlesUrl = "${spittlesJSUrl}"
		</script>