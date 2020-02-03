package ua.lviv.lgs.service.impl;

import java.util.List;

import ua.lviv.lgs.dao.impl.PhotoDaoImpl;
import ua.lviv.lgs.domain.Photo;
import ua.lviv.lgs.service.PhotoService;

public class PhotoServiceImpl implements PhotoService {

	private static PhotoService photoServiceImpl;
	private PhotoDaoImpl photoDaoImpl;

	private PhotoServiceImpl() {
		photoDaoImpl = new PhotoDaoImpl();
	}

	public static PhotoService getPhotoService() {
		if (photoServiceImpl == null) {
			photoServiceImpl = new PhotoServiceImpl();
		}
		return photoServiceImpl;
	}

	@Override
	public Photo create(Photo t) {
		return photoDaoImpl.create(t);
	}

	@Override
	public Photo read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Photo update(Photo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String bucketId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Photo> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
