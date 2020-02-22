package ua.lviv.lgs.service.dao.AI;

import java.util.List;

public interface AbstractCRUD<T> {

	T create(T t);

	T getById(String id);

	T update(T t);

	void delete(T t);

	List<T> readAll();

}
