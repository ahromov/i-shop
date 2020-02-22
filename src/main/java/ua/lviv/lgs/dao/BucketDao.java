package ua.lviv.lgs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.manager.FactoryManager;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class BucketDao implements AbstractCRUD<Bucket> {

	private static final Logger log = LogManager.getLogger(BucketDao.class);
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static BucketDao bucketDao;

	private BucketDao() {

	}

	public static BucketDao getBucketDao() {
		if (bucketDao == null) {
			bucketDao = new BucketDao();
		}

		return bucketDao;
	}

	@Override
	public Bucket create(Bucket bucket) {
		try {
			em.getTransaction().begin();
			em.persist(bucket);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t create " + bucket.getId(), e);
			em.getTransaction().rollback();
		}

		return bucket;
	}

	@Override
	public Bucket getById(String id) {
		Bucket bucket = null;

		try {
			bucket = em.find(Bucket.class, id);
		} catch (Exception e) {
			log.error("Can`t read " + bucket.getId(), e);
		}

		return bucket;
	}

	@Override
	public Bucket update(Bucket bucket) {
		try {
			em.getTransaction().begin();
			em.merge(bucket);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t update " + bucket.getId(), e);
			em.getTransaction().rollback();
		}

		return bucket;
	}

	@Override
	public void delete(Bucket bucket) {
		try {
			em.getTransaction().begin();
			em.remove(bucket);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t delete " + bucket.getId(), e);
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bucket> readAll() {
		Query query = null;

		try {
			query = em.createQuery("SELECT e FROM Bucket e");
		} catch (Exception e) {
			log.error("Can`t read all ", e);
		}

		return (List<Bucket>) query.getResultList();
	}

}
