package ua.lviv.lgs.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ua.lviv.lgs.domain.product.Product;
import ua.lviv.lgs.domain.product.ProductQtty;
import ua.lviv.lgs.domain.user.User;

@Entity
@Table(name = "bucket")
public class Bucket {

	@Id
	@Column(name = "id")
	private String id;

	@OneToOne(mappedBy = "bucket")
	private User user;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "bucket_product", joinColumns = @JoinColumn(name = "bucket_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private List<Product> products = new ArrayList<>();

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "bucket_pqtty", joinColumns = @JoinColumn(name = "bucket_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pqtty_id", referencedColumnName = "id"))
	private List<ProductQtty> productQttys = new ArrayList<>();

	public Bucket() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ProductQtty> getProductQttys() {
		return productQttys;
	}

	public void setProductQttys(List<ProductQtty> productQttys) {
		this.productQttys = productQttys;
	}

	public void addProduct(Product product) {
		products.add(product);
		product.getBuckets().add(this);
	}

	public void removeProduct(Product product) {
		products.remove(product);
		product.getBuckets().remove(this);
	}

	public void addProductQtty(ProductQtty pqtty) {
		productQttys.add(pqtty);
		pqtty.getBuckets().add(this);
	}

	public void removeProductQtty(ProductQtty pqtty) {
		productQttys.remove(pqtty);
		pqtty.getBuckets().remove(this);
	}

	public boolean isProductPresent(Product product) {
		if (products.contains(product))
			return true;

		return false;
	}

	public Product findProductById(int id) {
		return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}

	public ProductQtty findQttyByProdId(int id) {
		return productQttys.stream().filter(pq -> pq.getProduct().getId() == id).findFirst().orElse(null);
	}

}
