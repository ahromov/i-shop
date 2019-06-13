<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cabinet</title>
<jsp:include page="bootstrap.html"></jsp:include>
<jsp:include page="common_styles.html"></jsp:include>
<link rel="stylesheet" href="css/bucket.css">
</head>
<body>
	<jsp:include page="header.html"></jsp:include>

	<div class="container-fluid">
		<input type="text" id="myInput" onkeyup="myFunction()"
			placeholder="Search for names.." title="Type in a name">

		<table id="myTable">
		</table>
	</div>

	<jsp:include page="footer.html"></jsp:include>

	<jsp:include page="jquery.html"></jsp:include>

	<script src="js/header.js"></script>
	<script src="js/bucket.js"></script>
</body>
</html>