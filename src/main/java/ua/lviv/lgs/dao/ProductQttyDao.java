package ua.lviv.lgs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.manager.FactoryManager;
import ua.lviv.lgs.domain.product.ProductQtty;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class ProductQttyDao implements AbstractCRUD<ProductQtty> {

	private static final Logger log = LogManager.getLogger(ProductQttyDao.class);
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static ProductQttyDao productQttyDao;

	private ProductQttyDao() {
	}

	public static ProductQttyDao getProductQttyDao() {
		if (productQttyDao == null) {
			productQttyDao = new ProductQttyDao();
		}

		return productQttyDao;
	}

	@Override
	public ProductQtty create(ProductQtty productQtty) {
		try {
			em.getTransaction().begin();
			em.persist(productQtty);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t create " + productQtty.getId(), e);
			em.getTransaction().rollback();
		}

		return productQtty;
	}

	@Override
	public ProductQtty getById(String id) {
		ProductQtty productQtty = null;

		try {
			productQtty = em.find(ProductQtty.class, Long.parseLong(id));
		} catch (Exception e) {
			log.error("Can`t read pqtty ", e);
		}

		return productQtty;
	}

	@Override
	public ProductQtty update(ProductQtty productQtty) {
		try {
			em.getTransaction().begin();
			em.merge(productQtty);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t update " + productQtty.getId(), e);
			em.getTransaction().rollback();
		}

		return productQtty;
	}

	@Override
	public void delete(ProductQtty pq) {
		try {
			em.getTransaction().begin();
			em.remove(pq);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t delete pQtty", e);
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductQtty> readAll() {
		Query query = null;

		try {
			query = em.createQuery("SELECT e FROM ProductQtty e");
		} catch (Exception e) {
			log.error("Can`t read all ", e);
		}

		return (List<ProductQtty>) query.getResultList();
	}

}
