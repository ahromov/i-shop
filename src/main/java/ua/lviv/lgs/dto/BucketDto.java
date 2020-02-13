package ua.lviv.lgs.dto;

public class BucketDto {

	public String bucketId;
	public Integer productId;
	public byte[] bs;
	public String productName;
	public Double productPrice;
	public Integer productsCount;

	public BucketDto(String bucketId, byte[] bs, Integer productId, String productName, Double productPrice,
			Integer productsCount) {
		this.bucketId = bucketId;
		this.bs = bs;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productsCount = productsCount;
	}

}
