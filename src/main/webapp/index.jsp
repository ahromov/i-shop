<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	<div id="wrapper" class="animate">
		<nav
			class="navbar header-top fixed-top navbar-expand-lg  navbar-dark bg-dark">
			<span class="navbar-toggler-icon leftmenutrigger"></span> <a
				class="navbar-brand" href="#">DailyShop</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarText" aria-controls="navbarText"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav animate side-nav">
					<li class="nav-item"><a class="nav-link" href="cabinet.jsp">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item create-product-option"><a class="nav-link"
						href="createProduct.jsp">Add product</a></li>
					<li class="nav-item user-bucket-option"><a class="nav-link"
						href="bucket.jsp">Bucket</a></li>
				</ul>
				<ul class="navbar-nav ml-md-auto d-md-flex">
					<li class="nav-item">
						<button class="nav-link product-login"
							style="background-color: green">Login</button>
					</li>
				</ul>
			</div>
		</nav>
	</div>

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