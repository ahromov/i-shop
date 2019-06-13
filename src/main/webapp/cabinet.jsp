<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cabinet</title>
<jsp:include page="includes/bootstrap.html"></jsp:include>
<jsp:include page="includes/common_styles.html"></jsp:include>
</head>
<body>
	<jsp:include page="includes/header.html"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<div id="productCards"></div>
		</div>
	</div>

	<jsp:include page="includes/footer.html"></jsp:include>
	
	<jsp:include page="includes/jquery.html"></jsp:include>
	
	<script src="js/header.js"></script>
	<script src="js/cabinet.js"></script>
</body>
</html>