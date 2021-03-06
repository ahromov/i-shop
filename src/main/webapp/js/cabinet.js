var products = null;

function checkProdImg(data) {
	if (data == '')
		return 'img/default_prod.jpg';
	else
		return 'data:image/png;base64,' + data;
}

$
		.get("products", function(data) {
			if (data !== '') {
				products = data;
			}
		})
		.done(
				function() {
					var cardsContent = "";

					$
							.each(
									products,
									function(i, value) {
										cardsContent += "<div class='col'><div class='card'>"
												+ "<div class='card-body'><div class='image' style='height: 200px'><img style='width: 200px; margin-bottom: 20px' src='"
												+ checkProdImg(value.photo.content)
												+ "'></div><h5 class='card-title'>"
												+ value.name
												+ "</h5><h6 class='card-subtitle mb-2 text-muted'>"
												+ value.price
												+ "$</h6><p class='card-text'>"
												+ value.description
												+ "</p><a class='btn btn-primary productCardElement' onclick='checkRole("
												+ value.id
												+ ")' class='card-link'>Buy</a></div></div></div></div>"
									});

					$('#productCards').html(cardsContent);
				}).done(function() {
			$.get("user-role", function(data) {
				if (data !== '') {
					userRole = data;
				}
			}).done(function() {
				if (userRole === 'ADMINISTRATOR') {
					$('.productCardElement').html('Edit');
				}
			});

		});

function checkRole(id) {

	$.get("user-role", function(data) {
		if (data === null) {
			alert('You must be loginned for purchase!');
			$(location).attr('href', 'login.jsp');
		} else
			$(location).attr('href', 'product?id=' + id);
	})

}