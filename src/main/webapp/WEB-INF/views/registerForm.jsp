<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/style.css' />" />
<style type="text/css">
  span.error {
  color: red;
}
div.errors {
  background-color: #ffcccc;
  border: 2px solid red;
}
label.error {
  color: red;
}
input.error {
  background-color: #ffcccc;
}
</style>
</head>
<body>
	<h1>Register</h1>
	<sf:form method="POST" commandName="spitter">
		<sf:errors path="*" element="div" cssClass="errors" />
	
		<sf:label path="firstName" cssErrorClass="error">First Name</sf:label>:
        <sf:input path="firstName" cssErrorClass="error" />
		<!-- <sf:errors path="firstName" cssClass="error" /> --> <br /> 
		Last Name: <sf:input path="lastName" /><br /> 
		Username: <sf:input path="username" /><br /> 
		Password: <sf:password path="password" /><br /> 
		<input type="submit" value="Register" />
	</sf:form>
</body>
</html>

