<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shop</title>
<jsp:include page="includes/bootstrap.html"></jsp:include>
<jsp:include page="includes/common_styles.html"></jsp:include>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form class="register-form">
				<input class="email" type="email" placeholder="email address" /> <input
					class="firstName" type="text" placeholder="first name" /> <input
					class="lastName" type="text" placeholder="last name" /> <input
					class="password" type="password" placeholder="password" /> <input
					class="cpassword" type="password" placeholder="confirm password" />
				<button type="button" class="register">create</button>
				<p class="message">
					Already registered? <a href="#">Sign In</a>
				</p>
				<p class="message">
					<a href="index.jsp">Back to main page</a>
				</p>
			</form>

			<form class="login-form">
				<input class="email" type="email" placeholder="email address" /> <input
					class="password" type="password" placeholder="password" />
				<button type="button" class="login">login</button>
				<p class="message">
					Not registered? <a href="#">Create an account</a>
				</p>
				<p class="message">
					<a href="index.jsp">Back to main page</a>
				</p>
			</form>
		</div>

		<div class="alert alert-success  alert-dismissible fade show"
			role="alert">
			<b>Success!</b> You are registered.
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class="alert alert-warning account-exists alert-dismissible fade show"
			role="alert">
			<b>Warning!</b> This e-mail address is registered.
			<button type="button" class="close close-alert">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class="alert alert-danger not-exists alert-dismissible fade show"
			role="alert">
			<b>Error!</b> Account, not found.
			<button type="button" class="close close-alert">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		
		<div class="alert alert-danger invalid-email alert-dismissible fade show"
			role="alert">
			<b>Error!</b> Incorrect email. Check your email address.
			<button type="button" class="close close-alert">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-warning invalid-password alert-dismissible fade show"
			role="alert">
			<b>Warning!</b> Invalid password. Forgot it? <a href="#" class="remind-passwd"> Remind password</a>
			<button type="button" class="close close-alert">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-primary sended-password alert-dismissible fade show"
			role="alert">
			<b>Info.</b> Your password was send on your email. Check it!
			<button type="button" class="close close-alert">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		
		<div
			class="alert alert-loading fade show"
			role="alert">
			<div class="lds-dual-ring"></div>
		</div>
		
	</div>

	<jsp:include page="includes/footer.html"></jsp:include>

	<jsp:include page="includes/jquery.html"></jsp:include>

	<script src="js/login.js"></script>
</body>
</html>
