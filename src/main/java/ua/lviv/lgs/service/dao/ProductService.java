package ua.lviv.lgs.service.dao;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.product.Product;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class ProductService implements AbstractCRUD<Product> {

	private static final ProductDao productDao = ProductDao.getProductDao();

	private static ProductService productService;

	private ProductService() {
	}

	public static ProductService getProductService() {
		if (productService == null) {
			productService = new ProductService();
		}

		return productService;
	}

	@Override
	public Product create(Product t) {
		return productDao.create(t);
	}

	@Override
	public Product read(String id) {
		return productDao.read(id);
	}

	@Override
	public Product update(Product t) {
		return productDao.update(t);
	}

	@Override
	public void delete(String id) {
		productDao.delete(id);
	}

	@Override
	public List<Product> readAll() {
		return productDao.readAll();
	}

	public Map<Integer, Product> readAllMap() {
		return readAll().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
	}

}
