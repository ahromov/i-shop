<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="bootstrap.html"></jsp:include>
<jsp:include page="common_styles.html"></jsp:include>
<title>Cabinet</title>
</head>
<body>
	<jsp:include page="header.html"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<form class="createProduct">
				<div class="form-group">
					<input type="text" class="form-control productName" 
						placeholder="enter product name">
				</div>

				<div class="form-group">
					<input type="text" class="form-control productDescription" 
						placeholder="enter product description">
				</div>

				<div class="form-group">
					<input type="number" class="form-control productPrice" 
						placeholder="enter product price">
				</div>

				<button class="btn btn-primary createProduct">Submit</button>
			</form>
		</div>
	</div>

	<jsp:include page="footer.html"></jsp:include>
	
	<jsp:include page="jquery.html"></jsp:include>
	
	<script src="js/header.js"></script>
	<script src="js/serverCalls.js"></script>
</body>
</html>