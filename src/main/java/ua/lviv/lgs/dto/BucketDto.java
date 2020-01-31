package ua.lviv.lgs.dto;

import java.util.Date;

public class BucketDto {

	public String bucketId;
	public Date purchaseDate;
	public String productName;
	public String productDescription;
	public Double productPrice;
	public Integer productsCount;

	public BucketDto(String bucketId, Date purchaseDate, String productName, String productDescription,
			Double productPrice, Integer productsCount) {
		this.bucketId = bucketId;
		this.purchaseDate = purchaseDate;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productsCount = productsCount;
	}

}
