package ua.lviv.lgs.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ua.lviv.lgs.domain.product.Product;

@Entity
@Table(name = "pqtty")
public class ProductQtty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "pqtty")
	private Integer productQtty;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@JsonIgnore
	@ManyToMany(mappedBy = "productQttys")
	private List<Bucket> buckets = new ArrayList<>();

	public ProductQtty() {

	}

	public ProductQtty(Integer count) {
		this.productQtty = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQtty() {
		return productQtty;
	}

	public void setProductQtty(Integer productQtty) {
		this.productQtty = productQtty;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Bucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<Bucket> buckets) {
		this.buckets = buckets;
	}

}
