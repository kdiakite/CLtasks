package pl.coderslab.get;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 3. Dodaj do servletu `Sess03_All` funkcjonalność, która wyświetli w formie tabeli 
 wszystkie dane znajdujące się w sesji (zarówno klucz jak i wartość). 
Do przechowywania kluczy wszystkich wartości w sesji użyj dodatkowej zmiennej sesyjnej, 
która będzie przechowywać w tablicy lub liście wszystkie klucze.
 */
@WebServlet("/showAllSession")
public class Sess03_All extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		HttpSession sess = request.getSession();
		Enumeration<String> attributeNames = sess.getAttributeNames();
		String key = "";
		Object value = "";
		
		while(attributeNames.hasMoreElements()) { 
			key = attributeNames.nextElement(); 
			value = sess.getAttribute(key);
			response.getWriter().append(key).append(" - ").append(value.toString()).append("<br>");
			
		}
		
	}



}
