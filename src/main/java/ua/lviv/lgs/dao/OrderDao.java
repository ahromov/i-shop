package ua.lviv.lgs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.manager.FactoryManager;
import ua.lviv.lgs.domain.Order;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class OrderDao implements AbstractCRUD<Order> {

	private static final Logger log = LogManager.getLogger(OrderDao.class);
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static OrderDao orderDao;

	private OrderDao() {

	}

	public static OrderDao getOrderDao() {
		if (orderDao == null) {
			orderDao = new OrderDao();
		}

		return orderDao;
	}

	@Override
	public Order create(Order order) {
		try {
			em.getTransaction().begin();
			em.persist(order);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t create " + order.getId(), e);
			em.getTransaction().rollback();
		}

		return order;
	}

	@Override
	public Order getById(String id) {
		Order order = null;

		try {
			order = em.find(Order.class, id);
		} catch (Exception e) {
			log.error("Can`t read " + order.getId(), e);
		}

		return order;
	}

	@Override
	public Order update(Order order) {
		try {
			em.getTransaction().begin();
			em.merge(order);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t update " + order.getId(), e);
			em.getTransaction().rollback();
		}

		return order;
	}

	@Override
	public void delete(Order order) {
		try {
			em.getTransaction().begin();
			em.remove(order);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t delete " + order.getId(), e);
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> readAll() {
		Query query = null;

		try {
			query = em.createQuery("SELECT e FROM Order e");
		} catch (Exception e) {
			log.error("Can`t read all ", e);
		}

		return (List<Order>) query.getResultList();
	}

}
