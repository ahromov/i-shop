package ua.lviv.lgs.service.dao;

import java.util.List;

import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class BucketService implements AbstractCRUD<Bucket> {

	private static final BucketDao bucketDao = BucketDao.getBucketDao();

	private static BucketService bucketService;

	private BucketService() {
	}

	public static BucketService getBucketService() {
		if (bucketService == null) {
			bucketService = new BucketService();
		}

		return bucketService;
	}

	@Override
	public Bucket create(Bucket t) {
		return bucketDao.create(t);
	}

	@Override
	public Bucket read(String id) {
		return bucketDao.read(id);
	}

	@Override
	public Bucket update(Bucket t) {
		return bucketDao.update(t);
	}

	@Override
	public void delete(String id) {
		bucketDao.delete(id);
	}

	@Override
	public List<Bucket> readAll() {
		return bucketDao.readAll();
	}

}
