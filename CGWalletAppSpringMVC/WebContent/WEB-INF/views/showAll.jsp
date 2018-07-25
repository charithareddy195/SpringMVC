<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Details</title>
</head>
<body>
<div align="center">
			<table border="3">
				<tr>
				<th>name</th>
				<th>mobile Number</th>
				<th>Amount</th>
			</tr>
			<c:forEach items="${customer}" var="customer">
				<tr>
					<td>${customer.name}</td>
					<td>${customer.mobileNo}</td>
					<td>${customer.wallet.balance}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>