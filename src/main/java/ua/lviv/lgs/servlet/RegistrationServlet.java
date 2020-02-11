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

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.service.impl.UserServiceImpl;
import ua.lviv.lgs.shared.MailSender;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = -9186251900623717347L;
	private static final Logger log = LogManager.getLogger(RegistrationServlet.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");

		if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
			User user = UserServiceImpl.getUserService().getUserByEmail(email);

			if (user == null) {
				try {
					UserServiceImpl.getUserService()
							.create(new User(email, firstName, lastName, UserRole.USER.toString(), password));

					MailSender.getMailSender().sendMail(email,
							"Hello " + firstName + "!\n Your account was rigistered!\n",
							"Yours login " + email + ", and password " + password);

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