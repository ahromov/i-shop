package ua.lviv.lgs.service.dao.AI;

import java.util.List;

public interface AbstractCRUD<T> {

	T create(T t);

	T read(String id);

	T update(T t);

	void delete(String bucketId);

	List<T> readAll();

}
