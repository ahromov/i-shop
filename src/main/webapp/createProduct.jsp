<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="includes/bootstrap.html"></jsp:include>
<jsp:include page="includes/common_styles.html"></jsp:include>
<title>Cabinet</title>
</head>
<body>
	<jsp:include page="includes/header.html"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<form class="createProduct" enctype="multipart/form-data"
				method="post" action="product">
				<div class="form-group">
					<input type="text" name="name" class="form-control productName"
						placeholder="enter product name">
				</div>

				<div class="form-group">
					<input type="text" name="description"
						class="form-control productDescription"
						placeholder="enter product description">
				</div>

				<div class="form-group">
					<input type="number" name="price" class="form-control productPrice"
						placeholder="enter product price">
				</div>

				<div class="form-group">
					<input type="file" name="file" class="form-control productPhoto">
				</div>

				<button type="submit" class="btn btn-primary createProduct">Submit</button>
			</form>
		</div>
	</div>

	<jsp:include page="includes/footer.html"></jsp:include>

	<jsp:include page="includes/jquery.html"></jsp:include>

	<script src="js/header.js"></script>
	<script src="js/serverCalls.js"></script>
</body>
</html>