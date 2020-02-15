package ua.lviv.lgs.service.dao;

import java.util.Map;

import ua.lviv.lgs.domain.Product;

public interface ProductService extends AbstractCRUD<Product> {

	public Map<Integer, Product> readAllMap();

}
