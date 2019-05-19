package ua.lviv.lgs.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.shared.FactoryManager;

public class UserDaoImpl implements UserDao {

	private EntityManager em = FactoryManager.getEntityManager();
	private static Logger LOGGER = LogManager.getLogger(UserDaoImpl.class.getName());

	@Override
	public User create(User user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			LOGGER.info("New user '" + user.getEmail() + "' was created.");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public User read(String id) {
		User user = null;
		try {
			user = em.find(User.class, Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public User update(User user) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public void delete(String id) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
	}

	@Override
	public List<User> readAll() {
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> from = cq.from(User.class);
			cq.select(from);
			cq.where(cb.equal(from.get("email"), email));
			TypedQuery<User> tq = em.createQuery(cq);
			user = tq.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		return user;
	}

}
