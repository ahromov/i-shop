package ua.lviv.lgs.service.dao;

import ua.lviv.lgs.domain.User;

public interface UserService extends AbstractCRUD<User> {

	User getUserByEmail(String email);

}
