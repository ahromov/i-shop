package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.dto.BucketDto;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

@WebServlet("/buckets")
public class BucketsController extends HttpServlet {

	private static final long serialVersionUID = -7884991345661641441L;

	private BucketService bucketService = BucketServiceImpl.getBucketService();
	private ProductService productService = ProductServiceImpl.getProductService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Bucket> allBuckets = bucketService.readAll();
		Map<Integer, Product> allProducts = productService.readAllMap();

		List<BucketDto> bucketsDtos = toDTO(allBuckets, allProducts);
		String json = new Gson().toJson(bucketsDtos);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	public List<BucketDto> toDTO(List<Bucket> allBuckets, Map<Integer, Product> allProducts) {
		return allBuckets.stream().map(bucket -> {
			BucketDto bucketDto = new BucketDto();
			bucketDto.bucketId = bucket.getId();
			bucketDto.purchaseDate = bucket.getPurchaseDate();

			Product product = allProducts.get(bucket.getProduct().getId());
			bucketDto.productName = product.getName();
			bucketDto.productDescription = product.getDescription();
			bucketDto.productPrice = product.getPrice();

			return bucketDto;
		}).collect(Collectors.toList());
	}

}
