package ua.lviv.lgs.service.dao;

import java.util.List;

import ua.lviv.lgs.dao.OrderDao;
import ua.lviv.lgs.domain.Order;
import ua.lviv.lgs.service.dao.AI.AbstractCRUD;

public class OrderService implements AbstractCRUD<Order> {

	private static final OrderDao orderDao = OrderDao.getOrderDao();

	private static OrderService orderService;

	private OrderService() {

	}

	public static OrderService getOrderService() {
		if (orderService == null) {
			orderService = new OrderService();
		}

		return orderService;
	}

	@Override
	public Order create(Order t) {
		return orderDao.create(t);
	}

	@Override
	public Order getById(String id) {
		return orderDao.getById(id);
	}

	@Override
	public Order update(Order t) {
		return orderDao.update(t);
	}

	@Override
	public void delete(Order t) {
		orderDao.delete(t);
	}

	@Override
	public List<Order> readAll() {
		return orderDao.readAll();
	}

}
