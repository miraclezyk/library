package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.UserDao;
import domain.Book;
import domain.User;

public class CheckServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao();
		User user = new User();
		user = userDao.selectUser(username);

		if (user == null) {
			request.setAttribute("message", "invalid username");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
			return;
		}

		if (!user.getPassword().equals(password)) {
			request.setAttribute("message", "wrong password");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
			return;
		}

		BookDao bookDao = new BookDao();
		List list = bookDao.selectBooks();
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("list", list);
		

		if (user.getIdentity().equals("member")) {
			response.sendRedirect(request.getContextPath() + "/member.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/librarian.jsp");
		}
	}

}
