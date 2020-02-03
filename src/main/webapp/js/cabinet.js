var products = null;

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
												+ "<div class='card-body'><div class='image' style='height: 200px'><img style='width: 200px; margin-bottom: 20px' src='data:image/png;base64,"
												+ value.photo.content
												+ "'></div><h5 class='card-title'>"
												+ value.name
												+ "</h5><h6 class='card-subtitle mb-2 text-muted'>"
												+ value.price
												+ "$</h6><p class='card-text'>"
												+ value.description
												+ "</p><a class='btn btn-primary productCardElement' href='product?id="
												+ value.id
												+ "' class='card-link'>Buy</a></div></div></div></div>"
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
