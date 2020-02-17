package ua.lviv.lgs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.manager.FactoryManager;
import ua.lviv.lgs.domain.Photo;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class PhotoDao implements AbstractCRUD<Photo> {

	private static final Logger log = LogManager.getLogger(PhotoDao.class);
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static PhotoDao photoDao;

	private PhotoDao() {

	}

	public static PhotoDao getPhotoDao() {
		if (photoDao == null) {
			photoDao = new PhotoDao();
		}

		return photoDao;
	}

	public Photo create(Photo file) {
		try {
			em.getTransaction().begin();
			em.persist(file);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t create " + file.getId(), e);
		}

		return file;
	}

	@Override
	public Photo read(String id) {
		Photo file = null;

		try {
			file = em.find(Photo.class, Integer.parseInt(id));
		} catch (Exception e) {
			log.error("Can`t read " + file.getId(), e);
		}

		return file;
	}

	@Override
	public Photo update(Photo file) {
		try {
			em.getTransaction().begin();
			em.merge(file);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t update " + file.getId(), e);
		}

		return file;
	}

	@Override
	public void delete(String id) {
		Photo file = read(id);

		try {
			em.getTransaction().begin();
			em.remove(file);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error("Can`t delete " + file.getId(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> readAll() {
		Query query = null;

		try {
			query = em.createQuery("SELECT e FROM Photo e");
		} catch (Exception e) {
			log.error("Can`t read all ", e);
		}

		return (List<Photo>) query.getResultList();
	}

}
