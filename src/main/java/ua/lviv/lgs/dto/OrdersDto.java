package ua.lviv.lgs.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ua.lviv.lgs.domain.Order;
import ua.lviv.lgs.domain.product.Product;
import ua.lviv.lgs.domain.product.ProductQtty;

public class OrdersDto {

	public Long id;
	public String email;
	public String firstName;
	public String lastName;
	public Date orderDate;
	public Double totalPrice;
	public List<ProductDto> products = new ArrayList<>();

	@SuppressWarnings("unused")
	private class ProductDto {

		String description;
		String name;
		Double price;
		byte[] photo;
		int qtty;

		ProductDto(String description, String name, Double price, byte[] photo, int qtty) {
			this.description = description;
			this.name = name;
			this.price = price;
			this.photo = photo;
			this.qtty = qtty;
		}

	}

	public OrdersDto(Order order) {
		this.id = order.getId();
		this.orderDate = order.getOrderDate();
		this.totalPrice = order.getTotalPrice();
		this.email = order.getUser().getEmail();
		this.firstName = order.getUser().getFirstName();
		this.lastName = order.getUser().getLastName();

		for (Iterator<Product> prducts = order.getProducts().iterator(); prducts.hasNext();) {
			Product p = prducts.next();
			ProductQtty pq = order.findQttyByProdId(p.getId());
			this.products.add(new ProductDto(p.getDescription(), p.getName(), p.getPrice(), p.getPhoto().getContent(),
					pq.getQtty()));
		}
	}

}
