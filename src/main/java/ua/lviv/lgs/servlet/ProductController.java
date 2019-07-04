package ua.lviv.lgs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

@WebServlet("/product")
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 530917315308551086L;

    ProductService productService = ProductServiceImpl.getProductService();

    // to create resource (product)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String description = request.getParameter("description");
	String name = request.getParameter("name");
	String price = request.getParameter("price");

	Product product = new Product(description, name, getValidatedPrice(price));
	productService.create(product);

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
	Product product = productService.read(productId);

	request.setAttribute("product", product);
	request.getRequestDispatcher("singleProduct.jsp").forward(request, response);
    }

}
