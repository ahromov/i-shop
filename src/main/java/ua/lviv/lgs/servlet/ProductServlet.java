package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import ua.lviv.lgs.domain.Photo;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

@WebServlet("/product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024
		* 50)
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 530917315308551086L;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (((String) request.getSession().getAttribute("role")).equals("ADMINISTRATOR")) {
			Product product = new Product(request.getParameter("description"), request.getParameter("name"),
					getValidatedPrice(request.getParameter("price")), getPhoto(request, response));
			ProductServiceImpl.getProductService().create(product);

			response.getWriter().write("Success");
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (((String) request.getSession().getAttribute("role")).equals("ADMINISTRATOR")) {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");

			String productId = request.getParameter("productId");

			if (productId != null) {
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				String price = request.getParameter("price");
				Photo photo = getPhoto(request, response);

				Product product = ProductServiceImpl.getProductService().read(productId);
				product.setName(name);
				product.setDescription(description);
				product.setPrice(getValidatedPrice(price));
				if (photo.getFileSize() != 0)
					product.setPhoto(photo);

				ProductServiceImpl.getProductService().update(product);
				response.getWriter().write("Success");
			} else
				response.getWriter().write("Error");
		}
	}

	private Photo getPhoto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = "";
		Photo photo = null;

		for (Part part : request.getParts()) {
			fileName = extractFileName(part);
			photo = new Photo();
			photo.setFileName(fileName);
			photo.setFileSize(part.getSize() / 1024);
			photo.setContent(IOUtils.toByteArray(part.getInputStream()));
			photo.setUploadStatus("Success");
		}

		return photo;
	}

	private String extractFileName(Part part) {
		String fileName = "", contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for (String item : items) {
			if (item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}

		return fileName;
	}

	private double getValidatedPrice(String price) {
		if (price == null || price.isEmpty()) {
			return 0;
		}

		return Double.parseDouble(price);
	}

}
