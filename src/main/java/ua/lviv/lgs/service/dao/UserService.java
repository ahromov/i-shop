package ua.lviv.lgs.service.dao;

import java.util.List;

import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.user.User;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class UserService implements AbstractCRUD<User> {

	private static final UserDao userDao = UserDao.getUserDao();

	private static UserService userService;

	private UserService() {

	}

	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}

	@Override
	public User create(User t) {
		return userDao.create(t);
	}

	@Override
	public User read(String id) {
		return userDao.read(id);
	}

	@Override
	public User update(User t) {
		return userDao.update(t);
	}

	@Override
	public void delete(String id) {
		userDao.delete(id);
	}

	@Override
	public List<User> readAll() {
		return userDao.readAll();
	}

	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

}
