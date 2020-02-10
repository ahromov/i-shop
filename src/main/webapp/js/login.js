function loginRegisterSwitch() {
	$('form').animate({
		height : "toggle",
		opacity : "toggle"
	}, "slow");
}

function showAlertAfterRegistration(data) {
	if (data == 'Success') {
		$('div.alert.alert-success').show();
		return;
	}

	if (data == 'Exists') {
		$('div.alert.alert-warning').show();
		return;
	}

	if (data == 'NotExists') {
		$('div.alert.alert-danger').show();
		return;
	}
}

$('.message a').on('click', function() {
	loginRegisterSwitch();
	$('.alert').css('display', 'none');
});

$("button.register")
		.on(
				'click',
				function() {
					var email = $("form.register-form input.email").val();
					var firstName = $("form.register-form input.firstName")
							.val();
					var lastName = $("form.register-form input.lastName").val();
					var password = $("form.register-form input.password").val();
					var cpassword = $("form.register-form input.cpassword")
							.val();

					if (firstName == '' || lastName == '' || email == ''
							|| password == '' || cpassword == '') {
						alert("Please fill all fields...!!!!!!");
					} else if ((password.length) < 8) {
						alert("Password should atleast 8 character in length...!!!!!!");
					} else if (!(password).match(cpassword)) {
						alert("Your passwords don't match. Try again?");
					} else {
						var userRegistration = {
							firstName : firstName,
							lastName : lastName,
							email : email,
							password : password
						};

						$.post("registration", userRegistration).done(
								function(data) {
									$("form")[0].reset();
									$("form")[1].reset();
									loginRegisterSwitch();
									showAlertAfterRegistration(data);
									return;
								});
					}

				});

$("button.login").click(function() {
	var email = $("form.login-form input.email").val();
	var password = $("form.login-form input.password").val();

	if (email == '' || password == '') {
		alert("Please fill login form!");
	} else {
		var userLogin = {
			email : email,
			password : password
		};

		$.post("login", userLogin, function(data) {
			if (data == 'NotExists')
				showAlertAfterRegistration(data);
			else {
				var customUrl = '';
				var urlContent = window.location.href.split('/');

				for (var i = 0; i < urlContent.length - 1; i++) {
					customUrl += urlContent[i] + '/'
				}

				customUrl += data.destinationUrl;
				window.location = customUrl;

				$("form")[1].reset();
			}

		});
	}

});
