package ua.lviv.lgs.domain;

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

@Entity
@Table(name = "bucket")
public class Bucket {

	@Id
	@Column(name = "id")
	private String id;

	@OneToOne(mappedBy = "bucket")
	private User user;

	@ManyToMany(cascade = { CascadeType.ALL, })
	@JoinTable(name = "bucket_product", joinColumns = @JoinColumn(name = "bucket_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;

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

	public void addProduct(Product product) {
		products.add(product);
		product.getBuckets().add(this);
	}

	public void removeProduct(Product product) {
		products.remove(product);
		product.getBuckets().remove(this);
	}

}
