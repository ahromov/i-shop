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
import ua.lviv.lgs.dao.manager.FactoryManager;
import ua.lviv.lgs.domain.User;

public class UserDaoImpl implements UserDao {

	private static final Logger log = LogManager.getLogger(UserDaoImpl.class.getName());
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static UserDaoImpl userDaoImpl;

	private UserDaoImpl() {
	}

	public static UserDaoImpl getUserDaoImpl() {
		if (userDaoImpl == null) {
			userDaoImpl = new UserDaoImpl();
		}

		return userDaoImpl;
	}

	@Override
	public User create(User user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			log.info("New user '" + user.getEmail() + "' was created.");
		} catch (Exception e) {
			log.error(e);
		}
		return user;
	}

	@Override
	public User read(String id) {
		User user = null;
		try {
			user = em.find(User.class, Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e);
		}
		return user;
	}

	@Override
	public User update(User user) {
		try {
		} catch (Exception e) {
			log.error(e);
		}
		return user;
	}

	@Override
	public void delete(String id) {
		try {
		} catch (Exception e) {
			log.error(e);
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
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> from = query.from(User.class);
			query.select(from);
			query.where(builder.equal(from.get("email"), email));
			TypedQuery<User> tq = em.createQuery(query);
			user = tq.getSingleResult();
		} catch (Exception e) {
			log.error(e);
		}

		return user;
	}

}
