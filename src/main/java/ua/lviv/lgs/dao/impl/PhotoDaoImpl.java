package ua.lviv.lgs.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.PhotoDao;
import ua.lviv.lgs.dao.manager.FactoryManager;
import ua.lviv.lgs.domain.Photo;

public class PhotoDaoImpl implements PhotoDao {

	private static final Logger log = LogManager.getLogger(PhotoDaoImpl.class.getName());
	private static final EntityManager em = FactoryManager.getEntityManager();

	private static PhotoDaoImpl photoDaoImpl;

	private PhotoDaoImpl() {
	}

	public static PhotoDaoImpl getPhotoDaoImpl() {
		if (photoDaoImpl == null) {
			photoDaoImpl = new PhotoDaoImpl();
		}

		return photoDaoImpl;
	}

	public Photo create(Photo file) {
		try {
			em.getTransaction().begin();
			em.persist(file);
			em.getTransaction().commit();
			log.info("New photo '" + file.getFileName() + "' was saved.");
		} catch (Exception e) {
			log.error("Can`t saving photo: ", e);
		}
		return file;
	}

	@Override
	public Photo read(String id) {
		Photo file = null;

		try {
			file = em.find(Photo.class, Integer.parseInt(id));
		} catch (Exception e) {
			log.error("Photo file not found: ", e);
		}

		return file;
	}

	@Override
	public Photo update(Photo file) {
		try {
			em.getTransaction().begin();
			em.merge(file);
			em.getTransaction().commit();
			log.info("Photo '" + file.getFileName() + "' was updated.");
			return file;
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}

	@Override
	public void delete(String id) {
		Photo file = read(id);

		try {
			em.getTransaction().begin();
			em.remove(file);
			em.getTransaction().commit();
			log.info("Photo '" + file.getFileName() + "' was deleted.");
		} catch (Exception e) {
			log.error(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> readAll() {
		Query query = null;

		try {
			query = em.createQuery("SELECT e FROM Photo e");
		} catch (Exception e) {
			log.error("Can`t read files: ", e);
		}

		return (List<Photo>) query.getResultList();
	}

}
