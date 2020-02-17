package ua.lviv.lgs.domain.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ua.lviv.lgs.domain.Photo;

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
	@JoinColumn(name = "photo_id", nullable = true)
	private Photo photo;

	@JsonIgnore
	@OneToOne(mappedBy = "product")
	private BucketProduct bProduct;

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

	public BucketProduct getbProduct() {
		return bProduct;
	}

	public void setbProduct(BucketProduct bProduct) {
		this.bProduct = bProduct;
	}

}
