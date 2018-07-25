<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Associate Details</title>
</head>
<body>
	<div align="left">
		<p>ID:${associate.associateID}</p>
		<br>
		<p>FirstName:${associate.firstName}</p>
		<br>
		<p>LastName:${associate.lastName }</p>
		<br>
		<p>Department:${associate.department}</p>
		<br>
		<p>Designation:${associate.designation}</p>
		<br>
		<p>BankName:${associate.bankDetails.bankName}</p>
		<br>
		<p>BasicSalary:${associate.salary.basicSalary}</p>
		<br>
	</div>
</body>
</html>