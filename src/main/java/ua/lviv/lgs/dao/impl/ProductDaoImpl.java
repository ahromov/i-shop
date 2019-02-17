package ua.lviv.lgs.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.shared.FactoryManager;

public class ProductDaoImpl implements ProductDao {

    private EntityManager em = FactoryManager.getEntityManager();
    private static Logger LOGGER = LogManager.getLogger(ProductDaoImpl.class);

    @Override
    public Product create(Product product) {
	try {
	    em.getTransaction().begin();
	    em.persist(product);
	    em.getTransaction().commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error(e);
	}
	return product;
    }

    @Override
    public Product read(String id) {
	Product product = null;
	try {
	    product = em.find(Product.class, Integer.parseInt(id));
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error(e);
	}
	return product;
    }

    @Override
    public Product update(Product product) {
	return null;
    }

    @Override
    public void delete(String id) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> readAll() {
	Query query = null;
	try {
	    query = em.createQuery("SELECT e FROM Product e");
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error(e);
	}
	return (List<Product>) query.getResultList();
    }

}
