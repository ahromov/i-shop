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
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

@WebServlet("/buckets")
public class BucketsController extends HttpServlet {

    private static final long serialVersionUID = -7884991345661641441L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Bucket> allBuckets = BucketServiceImpl.getBucketService().readAll().stream()
		.filter(bucket -> bucket.getUser().getId() == request.getSession().getAttribute("userId"))
		.collect(Collectors.toList());
	Map<Integer, Product> allProducts = ProductServiceImpl.getProductService().readAllMap();

	List<BucketDto> bucketsDtos = toDTO(allBuckets, allProducts);
	String json = new Gson().toJson(bucketsDtos);

	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write(json);
    }

    public List<BucketDto> toDTO(List<Bucket> allBuckets, Map<Integer, Product> allProducts) {
	return allBuckets.stream().map(bucket -> {
	    return new BucketDto(bucket.getId(), bucket.getPurchaseDate(),
		    allProducts.get(bucket.getProduct().getId()).getName(),
		    allProducts.get(bucket.getProduct().getId()).getDescription(),
		    allProducts.get(bucket.getProduct().getId()).getPrice());
	}).collect(Collectors.toList());
    }

}
