package ua.lviv.lgs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.service.impl.UserServiceImpl;
import ua.lviv.lgs.shared.MailSender;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = -9186251900623717347L;

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
				UserServiceImpl.getUserService()
						.create(new User(email, firstName, lastName, UserRole.USER.toString(), password));

				MailSender.getMailSender().sendMail(email, "Hello " + firstName + "!\n Your account was rigistered!\n",
						"Yours login " + email + ", and password " + password);

				response.getWriter().write("Success");
			} else {
				response.getWriter().write("Exists");
			}
		}

	}

}
