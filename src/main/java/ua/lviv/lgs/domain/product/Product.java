package ua.lviv.lgs.domain.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Photo;
import ua.lviv.lgs.domain.ProductQtty;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "description")
	private String description;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private Double price;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "photo_id")
	private Photo photo;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ProductQtty> productQttys;

	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<Bucket> buckets = new ArrayList<>();

	public Product() {

	}

	public Product(String description, String name, Double price, Photo photo) {
		this.description = description;
		this.name = name;
		this.price = price;
		this.photo = photo;
	}

	public Product(Integer id, String description, String name, Double price, Photo photo) {
		this(description, name, price, photo);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Set<ProductQtty> getProductQtty() {
		return productQttys;
	}

	public void setProductQtty(Set<ProductQtty> productQtty) {
		this.productQttys = productQtty;
	}

	public List<Bucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<Bucket> buckets) {
		this.buckets = buckets;
	}

	public void addProductQtty(ProductQtty pQtty) {
		productQttys.add(pQtty);
	}

	public void removeProductQtty(ProductQtty product) {
		productQttys.remove(product);
	}

	public ProductQtty findQttyByProductId(String id) {
		return productQttys.stream().filter(pq -> pq.getProduct().getId() == Integer.parseInt(id)).findFirst()
				.orElse(null);
	}

}
