$(document)
		.ready(
				function() {

					var userRole = '';

					$
							.get(
									"user-role",
									function(data) {
										if (data !== '') {
											userRole = data;

											$
													.ajax({
														type : 'GET',
														url : 'order',
														success : function(
																ordersDto) {
															var tableContent = "<tr class='header main'>"
																	+ "<th style='width: 20%;'>Order ID</th>"
																	+ "<th style='width: 20%;'>Order Date</th>"
																	+ "<th style='width: 20%;'>User login</th>"
																	+ "<th style='width: 20%;'>User First Name</th>"
																	+ "<th style='width: 20%;'>User Last Name</th>"
																	+ "<th style='width: 20%;'>Total Price $</th>"
																	+ "<th style='width: 20%;'>Options</th>";

															jQuery
																	.each(
																			ordersDto,
																			function(
																					i,
																					order) {
																				tableContent += "<tr class='user'><td><!-- <img style='width: 50px; margin-bottom: 20px' src='data:image/png;base64, -->"
																						+ order.id
																						+ "<td>"
																						+ new Date(
																								order.orderDate)
																								.toDateString()
																						+ "</td><td><a href=''>"
																						+ order.email
																						+ "</a></td><td>"
																						+ order.firstName
																						+ "</td><td>"
																						+ order.lastName
																						+ "</td>"
																						+ "<td>"
																						+ order.totalPrice
																						+ "</td><td>"
																						+ "<button>Edit</button>"
																						+ "</td>"
																						+ "</tr>"

																				var productContent = "<tr class='header'>"
																						+ "<th style='width: 20%;'>photo</th>"
																						+ "<th style='width: 20%;'>description</th>"
																						+ "<th style='width: 20%;'>name</th>"
																						+ "<th style='width: 20%;'>price</th>"
																						+ "<th style='width: 20%;'>qtty</th></tr>";

																				jQuery
																						.each(
																								order.products,
																								function(
																										i,
																										product) {
																									productContent += "<tr><td><img style='width: 50px; margin-bottom: 20px' src='data:image/png;base64, "
																											+ product.photo
																											+ "'><td>"
																											+ product.description
																											+ "</td><td>"
																											+ product.name
																											+ "</td><td>"
																											+ product.price
																											+ "</td><td>"
																											+ product.qtty
																											+ "</td>"
																											+ "</tr>"
																								});

																				tableContent += productContent;

																			});

															$('div.row table')
																	.html(
																			tableContent);
														}
													});
										} else
											alert(data);
									})

				});
