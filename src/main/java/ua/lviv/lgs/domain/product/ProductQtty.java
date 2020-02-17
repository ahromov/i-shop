package ua.lviv.lgs.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "qtty")
public class ProductQtty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "qtty")
	private Integer productBuysQtty;

	@OneToOne(mappedBy = "qtty")
	private BucketProduct product;

	public ProductQtty() {

	}

	public ProductQtty(Integer buyCount) {
		this.productBuysQtty = buyCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProductBuysQtty(Integer buysQtty) {
		this.productBuysQtty = buysQtty;
	}

	public Integer getProductBuysQtty() {
		return productBuysQtty;
	}

	public BucketProduct getProduct() {
		return product;
	}

	public void setProduct(BucketProduct product) {
		this.product = product;
	}

}
