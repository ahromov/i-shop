package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Order;
import ua.lviv.lgs.domain.product.Product;
import ua.lviv.lgs.domain.product.ProductQtty;
import ua.lviv.lgs.domain.user.User;
import ua.lviv.lgs.domain.user.UserRole;
import ua.lviv.lgs.dto.OrdersDto;
import ua.lviv.lgs.service.SendMailService;
import ua.lviv.lgs.service.dao.BucketService;
import ua.lviv.lgs.service.dao.OrderService;
import ua.lviv.lgs.service.dao.ProductQttyService;
import ua.lviv.lgs.service.dao.UserService;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 6397216188062628915L;

	private static final Logger log = LogManager.getLogger(LoginServlet.class.getName());
	private static final UserService userService = UserService.getUserService();
	private static final OrderService orderService = OrderService.getOrderService();
	private static final BucketService bucketService = BucketService.getBucketService();
	private static final ProductQttyService productQttyService = ProductQttyService.getProductQttyService();
	private static final SendMailService mailSender = SendMailService.getMailSender();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		User user = userService.getById(((Integer) request.getSession().getAttribute("userId")).toString());

		StringBuffer sb = new StringBuffer();

		Bucket bucket = user.getBucket();

		if (bucket.getProducts().size() > 0) {
			Order order = new Order();
			order.setUser(user);
			order.setOrderDate(Date.valueOf(LocalDate.now(ZoneId.systemDefault())));

			Double sum = 0.0;

			sb.append("\n*** Items: ***\n\n");

			for (Iterator<Product> bucketProductsIter = bucket.getProducts().iterator(); bucketProductsIter
					.hasNext();) {
				Product product = bucketProductsIter.next();

				ProductQtty pq = bucket.findQttyByProdId(product.getId());

				sum += product.getPrice();

				order.getProducts().add(product);
				order.getProductQttys().add(pq);

				for (Iterator<ProductQtty> qttysIter = bucket.getProductQttys().iterator(); qttysIter.hasNext();) {
					ProductQtty pqtty = qttysIter.next();
					if (pq.getId() == pqtty.getId()) {
						qttysIter.remove();
						productQttyService.delete(pqtty);
					}
				}

				order.setTotalPrice(sum);

				bucketProductsIter.remove();
			}

			order.getProducts().forEach(p -> sb.append("NAME:\t\t" + p.getName() + "\n")
					.append("DESCRIPTION:\t" + p.getDescription() + "\n").append("PRICE:\t\t" + p.getPrice() + "\n")
					.append("QUANTITY:\t" + order.findQttyByProdId(p.getId()).getQtty() + "\n\n"));

			sb.append("\n").append("TOTAL PRICE:\t" + order.getTotalPrice())
					.append("\nORDER DATE:\t" + order.getOrderDate());

			orderService.create(order);

			bucketService.update(bucket);

			try {
				mailSender.sendMail(order.getUser().getEmail(), "Your order #" + order.getId(), sb.toString());
				mailSender.sendMail(userService.getUserByRole(UserRole.ADMINISTRATOR.toString()).getEmail(),
						"!!!NEW ORDER!!! #" + order.getId(), sb.toString());

				log.info("New order for user " + order.getUser().getEmail() + " was created:" + sb.toString());

				response.getWriter().write("Success");
			} catch (MessagingException e) {
				log.error(e);

				response.getWriter().write("Error");
			}
		} else
			response.getWriter().write("BucketEmpty");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = userService.getById(request.getSession().getAttribute("userId").toString());

		if (user != null) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			List<Order> orders = null;
			List<OrdersDto> ordersDto = null;

			if (user.getRole().equals("ADMINISTRATOR")) {
				orders = orderService.readAll();

				ordersDto = toOrdersDto(orders);
			}

			if (user.getRole().equals("USER")) {
				orders = user.getOrders();

				ordersDto = toOrdersDto(orders);
			}

			ObjectMapper objMapper = new ObjectMapper();
			objMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

			response.getWriter().write(objMapper.writeValueAsString(ordersDto));
		}

	}

	private List<OrdersDto> toOrdersDto(List<Order> orders) {
		List<OrdersDto> ordersDto = new ArrayList<>();

		for (Order order : orders) {
			ordersDto.add(new OrdersDto(order));
		}

		return ordersDto;
	}

}
