package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.user.User;
import ua.lviv.lgs.domain.user.UserRole;
import ua.lviv.lgs.service.SendMailService;
import ua.lviv.lgs.service.dao.UserService;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = -9186251900623717347L;

	private static final Logger log = LogManager.getLogger(RegistrationServlet.class.getName());
	private static final UserService userService = UserService.getUserService();
	private static final SendMailService mailSender = SendMailService.getMailSender();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");

		if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {

			User user = userService.getUserByEmail(email);

			if (user == null) {
				try {
					mailSender.sendMail(email, "Hello " + firstName + "!\n Your account was rigistered!\n",
							"Yours login " + email + ", and password " + password);

					user = new User(email, firstName, lastName, UserRole.USER.toString(), password);

					Bucket bucket = new Bucket();
					bucket.setUser(user);

					user.setBucket(bucket);
					userService.create(user);

					response.getWriter().write("Success");
				} catch (AddressException e) {
					log.error("Can`t user registered, invalid email: ", e);
					response.getWriter().write("InvalidEmail");
				} catch (MessagingException e) {
					log.error("Can`t user registered, mesaging error: ", e);
					response.getWriter().write("InvalidEmail");
				}
			} else {
				response.getWriter().write("Exists");
			}
		} else
			response.getWriter().write("InvalidData");
	}

}