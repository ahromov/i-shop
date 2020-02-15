package ua.lviv.lgs.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.dao.manager.FactoryManager;
import ua.lviv.lgs.domain.Product;

public class ProductDaoImpl implements ProductDao {

	private static final Logger log = LogManager.getLogger(ProductDaoImpl.class.getName());
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static ProductDaoImpl productDaoImpl;

	private ProductDaoImpl() {
	}

	public static ProductDaoImpl getProductDaoImpl() {
		if (productDaoImpl == null) {
			productDaoImpl = new ProductDaoImpl();
		}

		return productDaoImpl;
	}

	@Override
	public Product create(Product product) {
		try {
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
			log.info("New product '" + product.getName() + "' was added.");
		} catch (Exception e) {
			log.error(e);
		}

		return product;
	}

	@Override
	public Product read(String id) {
		Product product = null;

		try {
			product = em.find(Product.class, Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e);
		}

		return product;
	}

	@Override
	public Product update(Product product) {
		try {
			em.getTransaction().begin();
			em.merge(product);
			em.getTransaction().commit();
			log.info("Product '" + product.getName() + "' was updated.");
			return product;
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}

	@Override
	public void delete(String id) {
		Product product = read(id);

		try {
			em.getTransaction().begin();
			em.remove(product);
			em.getTransaction().commit();
			log.info("Product '" + product.getName() + "' was deleted.");
		} catch (Exception e) {
			log.error(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> readAll() {
		Query query = null;

		try {
			query = em.createQuery("SELECT e FROM Product e");
		} catch (Exception e) {
			log.error("Can`t read products: ", e);
		}

		return (List<Product>) query.getResultList();
	}

}
