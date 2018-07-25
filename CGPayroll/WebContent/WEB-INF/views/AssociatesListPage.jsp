<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List</title>
</head>
<body>

	<div align="center">
		<table border="1">
			<tr>
				<th>Associate ID</th>
				<th>FirstName</th>
				<th>Department</th>
				<th>Designation</th>
				<th>Bank Name</th>
				<th>Salary</th>

			</tr>

			<c:forEach items="${associate}" var="associates">
				<tr>
					<td>${associates.associateID}</td>
					<td>${associates.firstName}</td>
					<td>${associates.department}</td>
					<td>${associates.designation}</td>
					<td>${associates.bankDetails.bankName}</td>
					<td>${associates.salary.basicSalary}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>