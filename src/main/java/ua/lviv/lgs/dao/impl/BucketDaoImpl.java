package ua.lviv.lgs.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.shared.FactoryManager;

public class BucketDaoImpl implements BucketDao {

    private EntityManager em = FactoryManager.getEntityManager();
    private static Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);

    @Override
    public Bucket create(Bucket bucket) {
	try {
	    em.getTransaction().begin();
	    em.persist(bucket);
	    em.getTransaction().commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error(e);
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
	    LOGGER.error(e);
	}
	return bucket;
    }

    @Override
    public Bucket update(Bucket t) {
	throw new IllegalStateException("there is no update for bucket");
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
	    LOGGER.error(e);
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
	    LOGGER.error(e);
	}
	return (List<Bucket>) query.getResultList();
    }

}
