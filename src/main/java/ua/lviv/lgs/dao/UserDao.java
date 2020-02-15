package ua.lviv.lgs.dao;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public interface UserDao extends AbstractCRUD<User> {

	User getUserByEmail(String email);

}
