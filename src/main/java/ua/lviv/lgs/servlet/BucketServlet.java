package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.service.impl.ProductServiceImpl;
import ua.lviv.lgs.service.impl.UserServiceImpl;

@WebServlet("/bucket")
public class BucketServlet extends HttpServlet {

	private static final long serialVersionUID = -3449676006995456547L;

	private static final BucketService bucketService = BucketServiceImpl.getBucketServiceImpl();
	private static final ProductService productService = ProductServiceImpl.getProductService();
	private static final UserService userService = UserServiceImpl.getUserServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");
		Integer buyCount = Integer.parseInt(request.getParameter("qtty"));

		Product product = productService.read(productId);
		product.setBuyCount(buyCount);

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		User user = userService.read(userId.toString());
		Bucket bucket = user.getBucket();

		bucket.addProduct(product);
		bucketService.update(bucket);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("pId");
		User user = userService.read(request.getSession().getAttribute("userId").toString());

		Bucket bucket = user.getBucket();
		bucket.removeProduct(productService.read(productId));

		bucketService.update(bucket);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

}