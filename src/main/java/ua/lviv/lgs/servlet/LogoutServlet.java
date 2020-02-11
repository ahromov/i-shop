package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = -7014862132555465217L;

	private static final Logger log = LogManager.getLogger(LogoutServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session != null) {
			log.info("Session of user ID " + session.getAttribute("userId") + " ended at: " + LocalDateTime.now());
			session.invalidate();
		}

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("login.jsp");
	}

}
