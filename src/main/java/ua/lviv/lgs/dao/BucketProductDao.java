package ua.lviv.lgs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.manager.FactoryManager;
import ua.lviv.lgs.domain.product.BucketProduct;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class BucketProductDao implements AbstractCRUD<BucketProduct> {

	private static final Logger log = LogManager.getLogger(BucketProductDao.class);
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static BucketProductDao bucketProductDao;

	private BucketProductDao() {
	}

	public static BucketProductDao getProductDao() {
		if (bucketProductDao == null) {
			bucketProductDao = new BucketProductDao();
		}

		return bucketProductDao;
	}

	@Override
	public BucketProduct create(BucketProduct bProduct) {
		try {
			em.getTransaction().begin();
			em.persist(bProduct);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t create " + bProduct.getId(), e);
		}

		return bProduct;
	}

	@Override
	public BucketProduct read(String id) {
		BucketProduct bProduct = null;

		try {
			bProduct = em.find(BucketProduct.class, Long.parseLong(id));
		} catch (Exception e) {
			log.error("Can`t read " + bProduct.getId(), e);
		}

		return bProduct;
	}

	@Override
	public BucketProduct update(BucketProduct bProduct) {
		try {
			em.getTransaction().begin();
			em.merge(bProduct);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t update " + bProduct.getId(), e);
		}

		return bProduct;
	}

	@Override
	public void delete(String bucketId) {
		BucketProduct bProduct = read(bucketId);

		try {
			em.getTransaction().begin();
			em.remove(bProduct);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t delete " + bProduct.getId(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BucketProduct> readAll() {
		Query query = null;

		try {
			query = em.createQuery("SELECT e FROM BucketProduct e");
		} catch (Exception e) {
			log.error("Can`t read all ", e);
		}

		return (List<BucketProduct>) query.getResultList();
	}

}
