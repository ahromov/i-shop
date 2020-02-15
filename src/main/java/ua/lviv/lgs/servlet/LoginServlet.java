package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.dto.UserLogin;
import ua.lviv.lgs.service.SendMailService;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.UserServiceImpl;

@WebServlet(value = "/login", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1115455152301778383L;

	private static final Logger log = LogManager.getLogger(LoginServlet.class.getName());
	private static final UserService userService = UserServiceImpl.getUserServiceImpl();
	private static final SendMailService mailSender = SendMailService.getMailSender();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = userService.getUserByEmail(email);

		if (user != null) {
			if (user.getPassword().equals(password)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userId", user.getId());
				session.setAttribute("userName", user.getFirstName());
				session.setAttribute("role", user.getRole().toString());
				log.info("Session of user ID " + session.getAttribute("userId") + " started at: " + LocalDateTime.now());

				UserLogin userLogin = new UserLogin();
				userLogin.userEmail = user.getEmail();
				userLogin.destinationUrl = "cabinet.jsp";

				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(userLogin);

				response.setContentType("application/json");
				response.getWriter().write(json);
			} else {
				response.setContentType("text/html");
				response.getWriter().write("InvalidPassword");
			}
		} else {
			response.setContentType("text/html");
			response.getWriter().write("NotExists");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		String email = request.getParameter("email");
		User user = userService.getUserByEmail(email);

		if (user != null) {
			try {
				mailSender.sendMail(email, "Hello " + user.getFirstName(), "Your password " + user.getPassword());
				
				response.getWriter().write("PasswordSended");
			} catch (AddressException e) {
				log.error("Invalid email eddress: ", e);
			} catch (MessagingException e) {
				log.error("Can`t send email: ", e);
			}
		} else {
			response.getWriter().write("NotExists");
		}
	}

}
