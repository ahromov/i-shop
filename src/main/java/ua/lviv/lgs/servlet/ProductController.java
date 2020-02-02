package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

@WebServlet("/product")
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 530917315308551086L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String description = request.getParameter("description");
		String name = request.getParameter("name");
		String price = request.getParameter("price");

		Product product = new Product(description, name, getValidatedPrice(price));
		ProductServiceImpl.getProductService().create(product);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

	private double getValidatedPrice(String price) {
		if (price == null || price.isEmpty()) {
			return 0;
		}
		return Double.parseDouble(price);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("id");
		Product product = ProductServiceImpl.getProductService().read(productId);
		String role = (String) request.getSession().getAttribute("role");

		request.setAttribute("role", role);
		request.setAttribute("product", product);
		request.getRequestDispatcher("singleProduct.jsp").forward(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String productId = request.getParameter("productId");

		if (productId != null) {
			ProductServiceImpl.getProductService().delete(productId);
			response.getWriter().write("Success");
		} else
			response.getWriter().write("Error");
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String productId = request.getParameter("productId");
		String description = request.getParameter("newProductDescription");
		String name = request.getParameter("newProductName");
		String price = request.getParameter("newProductPrice");

		Product product = ProductServiceImpl.getProductService().read(productId);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(getValidatedPrice(price));

		if (productId != null) {
			ProductServiceImpl.getProductService().update(product);
			response.getWriter().write("Success");
		} else
			response.getWriter().write("Error");

//		request.setAttribute("product", product);
//		request.getRequestDispatcher("cabinet.jsp").forward(request, response);
	}

}
