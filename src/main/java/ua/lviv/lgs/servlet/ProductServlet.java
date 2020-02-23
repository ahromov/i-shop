package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Photo;
import ua.lviv.lgs.domain.product.Product;
import ua.lviv.lgs.service.dao.BucketService;
import ua.lviv.lgs.service.dao.ProductService;

@WebServlet("/product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024
		* 50)
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 530917315308551086L;

	private static final ProductService productService = ProductService.getProductService();
	private static final BucketService bucketService = BucketService.getBucketService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("id");
		Product product = productService.getById(productId);
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

		Product product = productService.getById(productId);

		if (!productId.equals("")) {
			List<Bucket> bkts = product.getBuckets();

			Iterator<Bucket> buketsIter = bkts.stream().iterator();

			while (buketsIter.hasNext()) {
				Bucket b = buketsIter.next();

				b.removeProductQtty(b.findQttyByProdId(product.getId()));
				b.removeProduct(product);

				bucketService.update(b);
			}

			productService.delete(product);

			response.getWriter().write("Success");
		} else
			response.getWriter().write("Error");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (((String) request.getSession().getAttribute("role")).equals("ADMINISTRATOR")) {
			String name = request.getParameter("name");

			String description = request.getParameter("description");

			Double price = 0.0;

			Photo photo = getFileContent(request, response);

			if (!request.getParameter("price").equals(""))
				price = Double.parseDouble(request.getParameter("price"));

			if (photo.getFileSize() == 0) {
				photo.setFileName("default");
				byte[] content = IOUtils
						.toByteArray(getServletContext().getResourceAsStream("WEB-INF/default_prod.jpg"));
				photo.setFileSize(content.length / 1024);
				photo.setContent(content);
				photo.setUploadStatus("Success");
			}

			if (name.equals("") || description.equals("") || price <= 0) {
				response.getWriter().write("Error");
			} else {
				Product product = new Product(description, name, price, photo);
				productService.create(product);

				response.getWriter().write("Success");
			}
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
				Photo newPhoto = getFileContent(request, response);

				Product product = productService.getById(productId);
				product.setName(name);
				product.setDescription(description);
				product.setPrice(getValidatedPrice(price));

				if (newPhoto.getFileSize() != 0) {
					Photo oldPhoto = product.getPhoto();
					oldPhoto.setFileName(newPhoto.getFileName());
					oldPhoto.setFileSize(newPhoto.getFileSize());
					oldPhoto.setContent(newPhoto.getContent());
					oldPhoto.setUploadStatus(newPhoto.getUploadStatus());

					product.setPhoto(oldPhoto);
					System.out.println(newPhoto.getContent());
				}

				productService.update(product);

				response.getWriter().write("Success");
			} else
				response.getWriter().write("Error");
		}
	}

	private Photo getFileContent(HttpServletRequest request, HttpServletResponse response)
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
