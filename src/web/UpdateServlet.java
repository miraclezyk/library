package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.UserDao;
import domain.User;

public class UpdateServlet extends HttpServlet {

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

		String statusMessage[] = request.getParameterValues("status");
		if (statusMessage == null) {
			request.getRequestDispatcher("/librarian.jsp").forward(request,
					response);
			return;
		}

		BookDao bookDao = new BookDao();
		String statusSp[] = null;

		UserDao userDao = new UserDao();
		User user = new User();

		for (String statusInfo : statusMessage) {
			statusSp = statusInfo.split(",");
			bookDao.updateBookStatus(statusSp[1], statusSp[0]);
			if (statusSp[0].equals("available")) {
				bookDao.updateBookOwner(statusSp[1], "none");
				user = userDao.selectUser(statusSp[2]);
				userDao.changeBookno(user.getUsername(), user.getBookno() - 1);
			}
		}

		List list = bookDao.selectBooks();
		request.getSession().setAttribute("list", list);

		request.getRequestDispatcher("/librarian.jsp").forward(request,
				response);

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
