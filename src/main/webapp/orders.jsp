<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="includes/bootstrap.html"></jsp:include>
<jsp:include page="includes/common_styles.html"></jsp:include>
<title>Cabinet</title>

<style type="text/css">
.row table{
	width: 100%;
}

.row table a{
	color: orange;
}

.row table tr.main{
	background: aqua;
}

.row table tr.user{
	background: gray;
	color: white;
}
</style>
</head>
<body>
	<jsp:include page="includes/header.html"></jsp:include>
	<h1>Welcome ${userName}</h1>
	<div class="container-fluid">
		<div class="row">
			<table>
			
			</table>
		</div>
	</div>

	<jsp:include page="includes/footer.html"></jsp:include>

	<jsp:include page="includes/jquery.html"></jsp:include>

	<script src="js/header.js"></script>
	<script src="js/ordersCall.js"></script>
</body>
</html>