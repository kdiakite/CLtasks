package pl.coderslab.excersises;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Session7
 */
@WebServlet("/Session7")
public class Session7 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setEncoding(response);

		Random rn = new Random();
		Integer a = rn.nextInt(981) + 20;
		Integer b = rn.nextInt(981) + 20;

		setSessionAttributes(request, a, b);
		generateMathForm(response, a, b);
	}

	private void setEncoding(HttpServletResponse response) {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
	}

	private void generateMathForm(HttpServletResponse response, Integer a, Integer b) throws IOException {
		response.getWriter().append("<form action=\"Session7\" method=\"post\">");
		response.getWriter().append(a + " + " + b + " = " + "<input type=\"number\" name=\"add\">");
		response.getWriter().append(a + " - " + b + " = " + "<input type=\"number\" name=\"substract\">");
		response.getWriter().append(a + " * " + b + " = " + "<input type=\"number\" name=\"multiply\">");
		response.getWriter().append("<input type=\"submit\" name=\"submit\">");
		response.getWriter().append("</form>");
	}

	private void setSessionAttributes(HttpServletRequest request, Integer a, Integer b) {
		HttpSession sess = request.getSession();
		sess.setAttribute("a", a);
		sess.setAttribute("b", b);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setEncoding(response);
		String add = request.getParameter("add");
		String substract = request.getParameter("substract");
		String multiply = request.getParameter("multiply");
		Integer addNumeric = Integer.valueOf(add);
		Integer subNumeric = Integer.valueOf(substract);
		Integer multNumeric = Integer.valueOf(multiply);

		HttpSession sess = request.getSession();
		Object a = sess.getAttribute("a");
		Object b = sess.getAttribute("b");
		Integer aNumeric = (Integer) a;
		Integer bNumeric = (Integer) b;

		checkResults(response, addNumeric, subNumeric, multNumeric, aNumeric, bNumeric);

	}

	private void checkResults(HttpServletResponse response, Integer addNumeric, Integer subNumeric, Integer multNumeric,
			Integer aNumeric, Integer bNumeric) throws IOException {
		if (aNumeric + bNumeric == addNumeric) {
			response.getWriter().append("Poprawny wynik dodawania<br>");
		} else {
			response.getWriter().append("Niepoprawny wynik dodawania!<br>");
		}

		if (aNumeric - bNumeric == subNumeric) {
			response.getWriter().append("Poprawny wynik odejmowania!<br>");
		} else {
			response.getWriter().append("Niepoprawny wynik odejmowania!<br>");
		}

		if (aNumeric * bNumeric == multNumeric) {
			response.getWriter().append("Poprawny wynik mnożenia<br>");
		} else {
			response.getWriter().append("Niepoprawny wynik mnożenia!<br>");
		}
	}

}
