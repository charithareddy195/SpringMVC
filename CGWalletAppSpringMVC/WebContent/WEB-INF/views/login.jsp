<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style>
.error{
color:red;
font-weight:bold;
}
</style>
<body>
	<form:form action="showMenu" method="post" modelAttribute="customer">
	Mobile Number:
	<form:input path="mobileNo" size="30" />
		<br>
		<input type="submit" value="login">
	</form:form>
	<div>
	<font color='red'>
	<c:if test="${not empty errorMessage}">
	${errorMessage}
	</c:if>
	</font>
	</div>
</body>
</html>