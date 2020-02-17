package ua.lviv.lgs.service.dao;

import java.util.List;

import ua.lviv.lgs.dao.BucketProductDao;
import ua.lviv.lgs.domain.product.BucketProduct;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class BucketProductService implements AbstractCRUD<BucketProduct> {

	private static final BucketProductDao bucketProductDao = BucketProductDao.getProductDao();

	private static BucketProductService productService;

	private BucketProductService() {

	}

	public static BucketProductService getProductService() {
		if (productService == null) {
			productService = new BucketProductService();
		}

		return productService;
	}

	@Override
	public BucketProduct create(BucketProduct t) {
		return bucketProductDao.create(t);
	}

	@Override
	public BucketProduct read(String id) {
		return bucketProductDao.read(id);
	}

	@Override
	public BucketProduct update(BucketProduct t) {
		return bucketProductDao.update(t);
	}

	@Override
	public void delete(String bucketId) {
		bucketProductDao.delete(bucketId);
	}

	@Override
	public List<BucketProduct> readAll() {
		return bucketProductDao.readAll();
	}

}
