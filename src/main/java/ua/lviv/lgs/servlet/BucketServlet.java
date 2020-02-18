package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.product.BucketProduct;
import ua.lviv.lgs.domain.product.ProductQtty;
import ua.lviv.lgs.domain.user.User;
import ua.lviv.lgs.service.dao.BucketProductService;
import ua.lviv.lgs.service.dao.BucketService;
import ua.lviv.lgs.service.dao.ProductService;
import ua.lviv.lgs.service.dao.UserService;

@WebServlet("/bucket")
public class BucketServlet extends HttpServlet {

	private static final long serialVersionUID = -3449676006995456547L;

	private static final BucketService bucketService = BucketService.getBucketService();
	private static final BucketProductService bpService = BucketProductService.getProductService();
	private static final ProductService productService = ProductService.getProductService();
	private static final UserService userService = UserService.getUserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");
		Integer buyCount = Integer.parseInt(request.getParameter("qtty"));

		User user = userService.read(((Integer) request.getSession().getAttribute("userId")).toString());
		Bucket bucket = user.getBucket();
		BucketProduct bp = bucket.findBPbyProductId(Integer.parseInt(productId));

		if (bp != null && bucket.isPresent(bp)) {
			bp.getQtty().setProductBuysQtty(buyCount);
		} else {
			bucket.addBProduct(new BucketProduct(productService.read(productId), new ProductQtty(buyCount)));
		}

		bucketService.update(bucket);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bucket bucket = userService.read(request.getSession().getAttribute("userId").toString()).getBucket();

		BucketProduct bProduct = bpService.read(request.getParameter("pId"));

		if (bucket.isPresent(bProduct)) {
			bucket.removeBProduct(bProduct);
			bpService.delete(bProduct.getId().toString());
			bucketService.update(bucket);
		}

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

}