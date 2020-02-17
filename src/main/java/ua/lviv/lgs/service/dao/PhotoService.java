package ua.lviv.lgs.service.dao;

import java.util.List;

import ua.lviv.lgs.dao.PhotoDao;
import ua.lviv.lgs.domain.Photo;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class PhotoService implements AbstractCRUD<Photo> {

	private static final PhotoDao photoDao = PhotoDao.getPhotoDao();

	private static PhotoService photoService;

	private PhotoService() {
	}

	public static PhotoService getPhotoService() {
		if (photoService == null) {
			photoService = new PhotoService();
		}

		return photoService;
	}

	@Override
	public Photo create(Photo photo) {
		return photoDao.create(photo);
	}

	@Override
	public Photo read(String id) {
		return photoDao.read(id);
	}

	@Override
	public Photo update(Photo photo) {
		return photoDao.update(photo);
	}

	@Override
	public void delete(String id) {
		photoDao.delete(id);
	}

	@Override
	public List<Photo> readAll() {
		return photoDao.readAll();
	}

}
