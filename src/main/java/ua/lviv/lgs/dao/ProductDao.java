package ua.lviv.lgs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.manager.FactoryManager;
import ua.lviv.lgs.domain.product.Product;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class ProductDao implements AbstractCRUD<Product> {

	private static final Logger log = LogManager.getLogger(ProductDao.class);
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static ProductDao productDao;

	private ProductDao() {

	}

	public static ProductDao getProductDao() {
		if (productDao == null) {
			productDao = new ProductDao();
		}

		return productDao;
	}

	@Override
	public Product create(Product product) {
		try {
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t create " + product.getId(), e);
		}

		return product;
	}

	@Override
	public Product read(String id) {
		Product product = null;

		try {
			product = em.find(Product.class, Integer.parseInt(id));
		} catch (Exception e) {
			log.error("Can`t read " + product.getId(), e);
		}

		return product;
	}

	@Override
	public Product update(Product product) {
		try {
			em.getTransaction().begin();
			em.merge(product);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t update " + product.getId(), e);
		}

		return product;
	}

	@Override
	public void delete(String id) {
		Product product = read(id);

		try {
			em.getTransaction().begin();
			em.remove(product);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t delete " + product.getId(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> readAll() {
		Query query = null;

		try {
			query = em.createQuery("SELECT e FROM Product e");
		} catch (Exception e) {
			log.error("Can`t read all ", e);
		}

		return (List<Product>) query.getResultList();
	}

}
