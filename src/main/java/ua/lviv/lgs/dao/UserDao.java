package ua.lviv.lgs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.manager.FactoryManager;
import ua.lviv.lgs.domain.user.User;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class UserDao implements AbstractCRUD<User> {

	private static final Logger log = LogManager.getLogger(UserDao.class);
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static UserDao userDao;

	private UserDao() {

	}

	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}

		return userDao;
	}

	@Override
	public User create(User user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t create " + user.getId(), e);
			em.getTransaction().rollback();
		}

		return user;
	}

	@Override
	public User getById(String id) {
		User user = null;

		try {
			user = em.find(User.class, Integer.parseInt(id));
		} catch (Exception e) {
			log.error("Can`t read " + user.getId(), e);
		}

		return user;
	}

	@Override
	public User update(User user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t update " + user.getId(), e);
			em.getTransaction().rollback();
		}

		return user;
	}

	@Override
	public void delete(User user) {
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t delete " + user.getId(), e);
			em.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> readAll() {
		Query query = null;

		try {
			query = em.createQuery("SELECT e FROM User e");
		} catch (Exception e) {
			log.error("Can`t read all ", e);
		}

		return (List<User>) query.getResultList();
	}

	public User getUserByEmail(String email) {
		User user = null;

		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();

			CriteriaQuery<User> query = builder.createQuery(User.class);

			Root<User> from = query.from(User.class);

			query.select(from);
			query.where(builder.equal(from.get("email"), email));

			user = em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			log.error("Can`t find user with email " + email);
		}

		return user;
	}

}
