package ua.lviv.lgs.service.dao.impl;

import java.util.List;

import ua.lviv.lgs.dao.PhotoDao;
import ua.lviv.lgs.dao.impl.PhotoDaoImpl;
import ua.lviv.lgs.domain.Photo;
import ua.lviv.lgs.service.dao.PhotoService;

public class PhotoServiceImpl implements PhotoService {

	private static final PhotoDao photoDaoImpl = PhotoDaoImpl.getPhotoDaoImpl();

	private static PhotoService photoServiceImpl;

	private PhotoServiceImpl() {
	}

	public static PhotoService getPhotoService() {
		if (photoServiceImpl == null) {
			photoServiceImpl = new PhotoServiceImpl();
		}

		return photoServiceImpl;
	}

	@Override
	public Photo create(Photo photo) {
		return photoDaoImpl.create(photo);
	}

	@Override
	public Photo read(String id) {
		return photoDaoImpl.read(id);
	}

	@Override
	public Photo update(Photo photo) {
		return photoDaoImpl.update(photo);
	}

	@Override
	public void delete(String id) {
		photoDaoImpl.delete(id);
	}

	@Override
	public List<Photo> readAll() {
		return photoDaoImpl.readAll();
	}

}
