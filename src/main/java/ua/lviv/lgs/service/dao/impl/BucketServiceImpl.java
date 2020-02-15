package ua.lviv.lgs.service.dao.impl;

import java.util.List;
import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.dao.impl.BucketDaoImpl;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.dao.BucketService;

public class BucketServiceImpl implements BucketService {

	private static final BucketDao bucketDao = BucketDaoImpl.getBucketDaoImpl();

	private static BucketServiceImpl bucketServiceImpl;

	private BucketServiceImpl() {
	}

	public static BucketServiceImpl getBucketServiceImpl() {
		if (bucketServiceImpl == null) {
			bucketServiceImpl = new BucketServiceImpl();
		}

		return bucketServiceImpl;
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