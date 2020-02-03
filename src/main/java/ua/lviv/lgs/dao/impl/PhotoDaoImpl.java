package ua.lviv.lgs.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.dao.PhotoDao;
import ua.lviv.lgs.domain.Photo;
import ua.lviv.lgs.shared.FactoryManager;

public class PhotoDaoImpl implements PhotoDao {

	private static Logger log = LogManager.getLogger(PhotoDaoImpl.class.getName());
	private EntityManager em = FactoryManager.getEntityManager();

	public Photo create(Photo photo) {
		try {
			em.getTransaction().begin();
			em.persist(photo);
			em.getTransaction().commit();
			log.info("New Photo '" + photo.getFileName() + "' was saved.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return photo;
	}

	@Override
	public Photo read(String id) {
		return null;
	}

	@Override
	public Photo update(Photo t) {
		return null;
	}

	@Override
	public void delete(String bucketId) {

	}

	@Override
	public List<Photo> readAll() {
		return null;
	}

}
