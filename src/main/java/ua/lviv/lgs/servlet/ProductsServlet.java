package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.lviv.lgs.domain.product.Product;
import ua.lviv.lgs.service.dao.ProductService;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

	private static final long serialVersionUID = -6987179294005671682L;

	private static final ProductService productService = ProductService.getProductService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> products = productService.readAll();

		String json = new ObjectMapper().writeValueAsString(products);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}