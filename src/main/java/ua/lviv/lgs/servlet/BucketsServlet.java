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

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.dto.BucketDto;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

@WebServlet("/buckets")
public class BucketsServlet extends HttpServlet {

	private static final long serialVersionUID = -7884991345661641441L;
	
	private static final BucketService bucketService = BucketServiceImpl.getBucketServiceImpl();
	private static final ProductService productService = ProductServiceImpl.getProductService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Bucket> allBuckets = bucketService.readAll().stream()
				.filter(bucket -> bucket.getUser().getId() == request.getSession().getAttribute("userId"))
				.collect(Collectors.toList());
		Map<Integer, Product> allProducts = productService.readAllMap();

		List<BucketDto> bucketsDtos = toDto(allBuckets, allProducts);

		String json = new ObjectMapper().writeValueAsString(bucketsDtos);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	public List<BucketDto> toDto(List<Bucket> allBuckets, Map<Integer, Product> allProducts) {
		return allBuckets.stream().map(bucket -> {
			return new BucketDto(bucket.getId(), bucket.getPurchaseDate(),
					allProducts.get(bucket.getProduct().getId()).getName(),
					allProducts.get(bucket.getProduct().getId()).getDescription(),
					allProducts.get(bucket.getProduct().getId()).getPrice(), bucket.getCount());
		}).collect(Collectors.toList());
	}

}
