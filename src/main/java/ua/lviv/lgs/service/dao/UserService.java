package ua.lviv.lgs.service.dao;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public interface UserService extends AbstractCRUD<User> {

	User getUserByEmail(String email);

}
