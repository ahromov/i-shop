package ua.lviv.lgs.domain;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import ua.lviv.lgs.domain.product.Product;

@Entity
public class Order implements Serializable {

	private Set<Product> products;
	private LocalDate orderDate;
	private static final long serialVersionUID = 1L;

	public Order() {
	}

	public LocalDate getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

}
