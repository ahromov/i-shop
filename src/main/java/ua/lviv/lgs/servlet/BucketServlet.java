package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.product.Product;
import ua.lviv.lgs.domain.product.ProductQtty;
import ua.lviv.lgs.service.dao.BucketService;
import ua.lviv.lgs.service.dao.ProductQttyService;
import ua.lviv.lgs.service.dao.ProductService;
import ua.lviv.lgs.service.dao.UserService;

@WebServlet("/bucket")
public class BucketServlet extends HttpServlet {

	private static final long serialVersionUID = -3449676006995456547L;

	private static final UserService userService = UserService.getUserService();
	private static final BucketService bucketService = BucketService.getBucketService();
	private static final ProductService productService = ProductService.getProductService();
	private static final ProductQttyService productQttyService = ProductQttyService.getProductQttyService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");

		Integer buyCount = Integer.parseInt(request.getParameter("qtty"));

		Bucket bucket = userService.getById(((Integer) request.getSession().getAttribute("userId")).toString())
				.getBucket();

		Product p = bucket.findProductById(Integer.parseInt(productId));

		if (p != null) {
			ProductQtty pq = bucket.findQttyByProdId(Integer.parseInt(productId));
			pq.setProductQtty(buyCount);
		} else {
			p = productService.getById(productId);
			ProductQtty pq = new ProductQtty(buyCount);
			pq.setProduct(p);

			bucket.addProductQtty(pq);
			bucket.addProduct(p);
		}

		bucketService.update(bucket);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bucket bucket = userService.getById(request.getSession().getAttribute("userId").toString()).getBucket();

		Product product = bucket.findProductById(Integer.parseInt(request.getParameter("pId")));

		for (Iterator<Product> pIter = bucket.getProducts().iterator(); pIter.hasNext();) {
			Product p = pIter.next();
			if (p.getId() == product.getId()) {
				for (Iterator<ProductQtty> qttysIter = bucket.getProductQttys().iterator(); qttysIter.hasNext();) {
					ProductQtty pqtty = qttysIter.next();
					if (bucket.findQttyByProdId(product.getId()).getId() == pqtty.getId()) {
						qttysIter.remove();
						productQttyService.delete(pqtty);
					}
				}

				pIter.remove();
			}
		}

		bucketService.update(bucket);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

}