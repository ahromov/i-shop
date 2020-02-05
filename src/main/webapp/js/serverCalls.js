$("button.createProduct").click(function() {

	var form = new FormData($('form.createProduct')[0]);

	$.ajax({
		type : 'POST',
		url : 'product',
		encType : "multipart/form-data",
		data : form,
		processData : false,
		contentType : false,
		success : function(data) {
			alert(data);
		}
	})

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

$("button.save-product").click(function() {
	
	const formData = new FormData($('#editProductModal form.updateProduct')[0]);

	fetch('product', {
		method: 'PUT',
		body: formData
	}).then((response) => response).then((result) => {
	if (result.status == 200)
		alert('Success');
		$(location).attr('href', 'cabinet.jsp');
	}).catch((error) => {
		alert('Error:', error);
	});
	
});