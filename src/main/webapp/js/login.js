function loginRegisterSwitch() {
	$('form').animate({
		height : "toggle",
		opacity : "toggle"
	}, "slow");
}

function hideAlert() {
	$('.alert').css('display', 'none');
}

function showAlertAfterRegistration(data) {
	hideAlert();

	if (data == 'Success') {
		$('div.alert.alert-success').show();
		return;
	}

	if (data == 'Exists') {
		$('div.alert.alert-warning.account-exists').show();
		return;
	}

	if (data == 'NotExists') {
		$('div.alert.alert-danger.not-exists').show();
		return;
	}

	if (data == 'InvalidPassword') {
		$('div.alert.alert-warning.invalid-password').show();
		return;
	}

	if (data == 'PasswordSended') {
		$('div.alert.alert-primary.sended-password').show();
		return;
	}

	if (data == 'InvalidEmail') {
		$('div.alert.alert-danger.invalid-email').show();
		return;
	}
}

$('.message a').on('click', function() {
	hideAlert();
	loginRegisterSwitch();
});

$('button.close').on('click', function() {
	hideAlert();
});

$("button.register")
		.on(
				'click',
				function() {
					$('div.alert.alert-loading').show();

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

						$.post("registration", userRegistration,
								function(data) {
									$("form")[0].reset();
									$("form")[1].reset();
									loginRegisterSwitch();
									showAlertAfterRegistration(data);
								});
					}
				});

$("button.login").on('click', function() {
	hideAlert();

	var email = $("form.login-form input.email").val();
	var password = $("form.login-form input.password").val();

	if (email == '' || password == '') {
		alert("Please fill login form!");
	} else {
		var userLogin = {
			email : email,
			password : password
		};

		$.post("login", userLogin).done(function(data) {
			if (data == 'NotExists') {
				showAlertAfterRegistration(data);
				return;
			}

			if (data == 'InvalidPassword') {
				showAlertAfterRegistration(data);
				return;
			}

			var customUrl = '';
			var urlContent = window.location.href.split('/');

			for (var i = 0; i < urlContent.length - 1; i++) {
				customUrl += urlContent[i] + '/'
			}

			customUrl += data.destinationUrl;
			window.location = customUrl;

			$("form")[1].reset();
		});
	}
});

$("a.remind-passwd").on('click', function() {
	hideAlert();

	$('div.alert.alert-loading').show();

	var email = $("form.login-form input.email").val();

	$.get("login?email=" + email).done(function(data) {
		showAlertAfterRegistration(data);
	});
});