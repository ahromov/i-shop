$("button.createProduct").click(function() {

	var description = $("form.createProduct input.productDescription").val();
	var name = $("form.createProduct input.productName").val();
	var price = $("form.createProduct input.productPrice").val();

	var product = {
		description : description,
		name : name,
		price : price
	};

	$.post("product", product, function(data) {
		if (data == 'Success') {
			alert('Success');
		}
	});

});

$("button.buy-product").click(function() {

	var productId = $("button.buy-product").attr("product-id");
	let userRole = '';

	$.get("user-role", function(data) {
		if (data !== '') {
			userRole = data;
		} else
			alert(data);
	}).done(function() {
		if (userRole === 'ADMINISTRATOR') {
			$.ajax({
				type : 'DELETE',
				url : 'product?productId=' + productId,
				success : function(data) {
					alert(data);
					$(location).attr('href', 'cabinet.jsp');
				}
			});
		} else {
			var qtty = $("input.number").val();

			if (qtty >= 1 && qtty <= 100) {
				$.post("bucket", {
					productId : productId,
					qtty : qtty
				}, function(data) {
					if (data == 'Success') {
						$("[data-dismiss=modal]").trigger({
							type : "click"
						});
						alert(data);
						$(location).attr('href', 'cabinet.jsp');
					}
				});
			} else {
				alert("Invalid quantity of items");
				$(location).attr('href', 'cabinet.jsp');
			}
		}
	});

});

$("button.save-product").click(
		function() {

			var productId = $("button.save-product").attr("product-id");
			var description = $("input#newProductDescription").val();
			var name = $("input#newProductName").val();
			var price = $("input#newProductPrice").val();

			$.ajax({
				type : 'PUT',
				url : 'product?productId=' + productId
						+ '&newProductDescription=' + description
						+ '&newProductName=' + name + '&newProductPrice='
						+ price,
				success : function(data) {
					alert(data);
					$(location).attr('href', 'cabinet.jsp');
				}
			});

		});