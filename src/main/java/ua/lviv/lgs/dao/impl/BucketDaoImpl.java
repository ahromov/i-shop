package ua.lviv.lgs.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.shared.FactoryManager;

public class BucketDaoImpl implements BucketDao {

	private static final Logger log = LogManager.getLogger(BucketDaoImpl.class.getName());
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static BucketDaoImpl bucketDaoImpl;

	private BucketDaoImpl() {
	}

	public static BucketDaoImpl getBucketDaoImpl() {
		if (bucketDaoImpl == null) {
			bucketDaoImpl = new BucketDaoImpl();
		}

		return bucketDaoImpl;
	}

	@Override
	public Bucket create(Bucket bucket) {
		try {
			em.getTransaction().begin();
			em.persist(bucket);
			em.getTransaction().commit();
			log.info("New bucket '" + bucket.getId() + "' for user '" + bucket.getUser().getEmail() + "' was created.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return bucket;
	}

	@Override
	public Bucket read(String id) {
		Bucket bucket = null;

		try {
			bucket = em.find(Bucket.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return bucket;
	}

	@Override
	public Bucket update(Bucket t) {
		try {
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
			log.info("Bucket '" + t.getUser().getFirstName() + "' was updated.");
			return t;
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}

	@Override
	public void delete(String id) {
		try {
			Bucket bucket = read(id);
			em.getTransaction().begin();
			em.remove(bucket);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bucket> readAll() {
		Query query = null;

		try {
			query = em.createQuery("SELECT e FROM Bucket e");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return (List<Bucket>) query.getResultList();
	}

}
