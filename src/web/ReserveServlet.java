package web;//this is from a new branch

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import dao.UserDao;
import domain.User;

public class ReserveServlet extends HttpServlet {

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

		String reserveTitle[] = request.getParameterValues("select");
		if (reserveTitle == null) {
			request.getRequestDispatcher("/member.jsp").forward(request,
					response);
			return;
		}

		User user = (User) request.getSession().getAttribute("user");

		if (reserveTitle.length + user.getBookno() > 2) {
			request.setAttribute("errorMsg",
					"Sorry,you can't borrow more than 2 books!");
			request.getRequestDispatcher("/member.jsp").forward(request,
					response);
			return;
		}

		BookDao bookDao = new BookDao();

		for (String bookTitle : reserveTitle) {
			bookDao.updateBookOwner(bookTitle, user.getUsername());
			bookDao.updateBookStatus(bookTitle, "reserved");
		}

		UserDao userDao = new UserDao();
		userDao.changeBookno(user.getUsername(), reserveTitle.length
				+ user.getBookno());
		user.setBookno(reserveTitle.length + user.getBookno());
		//request.getSession().setAttribute("user", user);

		List list = bookDao.selectBooks();
		request.getSession().setAttribute("list", list);

		request.getRequestDispatcher("/member.jsp").forward(request, response);

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
