package ua.lviv.lgs.service.dao;

import java.util.Map;

import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public interface ProductService extends AbstractCRUD<Product> {

	public Map<Integer, Product> readAllMap();

}
