package ua.lviv.lgs.domain.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ua.lviv.lgs.domain.Bucket;

@Entity
@Table(name = "bproduct")
public class BucketProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToOne(optional = false)
	@JoinColumn(name = "product_id", nullable = true)
	private Product product;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "qtty_id", nullable = true)
	private ProductQtty qtty;

	@JsonIgnore
	@ManyToMany(mappedBy = "bProducts", cascade = CascadeType.ALL)
	private List<Bucket> buckets = new ArrayList<>();

	public BucketProduct() {

	}

	public BucketProduct(Product product, ProductQtty qtty) {
		this.product = product;
		this.qtty = qtty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductQtty getQtty() {
		return qtty;
	}

	public void setQtty(ProductQtty qtty) {
		this.qtty = qtty;
	}

	public List<Bucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<Bucket> buckets) {
		this.buckets = buckets;
	}

}
