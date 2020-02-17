package ua.lviv.lgs.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ua.lviv.lgs.domain.product.BucketProduct;

@Entity
@Table(name = "bucket")
public class Bucket {

	@Id
	@Column(name = "id")
	private String id;

	@OneToOne(mappedBy = "bucket", fetch = FetchType.EAGER)
	private User user;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "bucket_bproduct", joinColumns = @JoinColumn(name = "bucket_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "bpoduct_id", referencedColumnName = "id"))
	private List<BucketProduct> bProducts;

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

	public List<BucketProduct> getBProducts() {
		return bProducts;
	}

	public void setBProducts(List<BucketProduct> products) {
		this.bProducts = products;
	}

	public void addBProduct(BucketProduct bProduct) {
		bProducts.add(bProduct);
		bProduct.getBuckets().add(this);
	}

	public void removeBProduct(BucketProduct bProduct) {
		bProducts.remove(bProduct);
		bProduct.getBuckets().remove(this);
	}

	public boolean isPresent(BucketProduct product) {
		if (bProducts.contains(product))
			return true;
		return false;
	}

	public BucketProduct findBPbyProductId(int id) {
		for (BucketProduct bucketProduct : bProducts) {
			if (bucketProduct.getProduct().getId() == id)
				return bucketProduct;
		}

		return null;
	}

}
