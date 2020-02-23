package ua.lviv.lgs.service.dao;

import java.util.List;

import ua.lviv.lgs.dao.ProductQttyDao;
import ua.lviv.lgs.domain.product.ProductQtty;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class ProductQttyService implements AbstractCRUD<ProductQtty> {

	private static final ProductQttyDao productQttyDao = ProductQttyDao.getProductQttyDao();

	private static ProductQttyService productQttyService;

	private ProductQttyService() {

	}

	public static ProductQttyService getProductQttyService() {
		if (productQttyService == null) {
			productQttyService = new ProductQttyService();
		}

		return productQttyService;
	}

	@Override
	public ProductQtty create(ProductQtty t) {
		return productQttyDao.create(t);
	}

	@Override
	public ProductQtty getById(String id) {
		return productQttyDao.getById(id);
	}

	@Override
	public ProductQtty update(ProductQtty t) {
		return productQttyDao.update(t);
	}

	@Override
	public void delete(ProductQtty pq) {
		productQttyDao.delete(pq);
	}

	@Override
	public List<ProductQtty> readAll() {
		return productQttyDao.readAll();
	}

}
