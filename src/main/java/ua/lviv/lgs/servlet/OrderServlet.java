package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.product.Product;
import ua.lviv.lgs.domain.product.ProductQtty;
import ua.lviv.lgs.domain.user.User;
import ua.lviv.lgs.service.SendMailService;
import ua.lviv.lgs.service.dao.BucketService;
import ua.lviv.lgs.service.dao.ProductQttyService;
import ua.lviv.lgs.service.dao.UserService;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 6397216188062628915L;

	private static final Logger log = LogManager.getLogger(LoginServlet.class.getName());
	private static final UserService userService = UserService.getUserService();
	private static final BucketService bucketService = BucketService.getBucketService();
	private static final ProductQttyService productQttyService = ProductQttyService.getProductQttyService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		User user = userService.getById(((Integer) request.getSession().getAttribute("userId")).toString());

		StringBuffer sb = new StringBuffer();

		Bucket bucket = user.getBucket();

		if (bucket.getProducts().size() > 0) {
			Double sum = 0.0;

			sb.append("\n*** Your items: ***\n\n");

			for (Iterator<Product> bucketProductsIter = bucket.getProducts().iterator(); bucketProductsIter
					.hasNext();) {
				Product product = bucketProductsIter.next();

				ProductQtty pq = bucket.findQttyByProdId(product.getId());

				sb.append("NAME:\t\t" + product.getName() + "\n")
						.append("DESCRIPTION:\t" + product.getDescription() + "\n")
						.append("PRICE:\t\t" + product.getPrice() + "\n").append("QUANTITY:\t" + pq.getQtty() + "\n")
						.append("\n");

				sum += product.getPrice();

				for (Iterator<ProductQtty> qttysIter = bucket.getProductQttys().iterator(); qttysIter.hasNext();) {
					ProductQtty pqtty = qttysIter.next();
					if (pq.getId() == pqtty.getId()) {
						qttysIter.remove();
						productQttyService.delete(pqtty);
					}
				}

				bucketProductsIter.remove();
			}

			sb.append("TOTAL PRICE:\t" + sum);

			bucketService.update(bucket);

			try {
				SendMailService.getMailSender().sendMail(user.getEmail(), "Your order", sb.toString());

				log.info(sb.toString());

				response.getWriter().write("Success");
			} catch (MessagingException e) {
				log.error(e);

				response.getWriter().write("Error");
			}
		} else
			response.getWriter().write("BucketEmpty");
	}

}
