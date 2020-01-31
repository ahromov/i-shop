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
			$.post("bucket", {
				productId : productId
			}, function(data) {
				if (data == 'Success') {
					$("[data-dismiss=modal]").trigger({
						type : "click"
					});
					alert(data);
					$(location).attr('href', 'cabinet.jsp');
				}
			});
		}
	});

});