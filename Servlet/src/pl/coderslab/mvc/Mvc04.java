package pl.coderslab.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Mvc04
 */
@WebServlet("/Mvc04")
public class Mvc04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/book-form.jsp").forward(request,  response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Book> bookList = new ArrayList<Book>();
		for(int i=1; i<=5; i++) { 

			bookList.add(getBookData(request, i));
		}
		
		
		request.setAttribute("bookList", bookList);
		getServletContext().getRequestDispatcher("/WEB-INF/book-displayer.jsp").forward(request, response);
		
		
	}

	private Book getBookData(HttpServletRequest request, int i) {
		Book book1 = new Book();
		String author = "author"+String.valueOf(i);
		String title = "title"+String.valueOf(i);
		String isbn = "isbn"+String.valueOf(i);
		book1.setAuthor(request.getParameter(author)); 
		book1.setTitle(request.getParameter(title));
		book1.setIsbn(request.getParameter(isbn));
		return book1;
	}

}
