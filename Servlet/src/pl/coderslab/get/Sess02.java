package pl.coderslab.get;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * W projekcie stwórz servlet `Sess02`. Następnie: 1. Dodaj do niego formularz z
 * możliwością wpisania oceny. 2. Po zatwierdzeniu formularza dodaj ocenę do
 * sesji. Oceny trzymaj w tablicy, którą będziesz wkładać do sesji. Zadbaj o
 * walidację wprowadzanych ocen (nie mniej niż 1 i nie więcej niż 6). 3. Wylicz
 * średnią z ocen (pamiętaj o właśnie dodanej ocenie). 4. Wszystkie zapamiętane
 * oceny i ich średnia powinny być wyświetlane pod formularzem.
 */

@WebServlet("/Sess02")
public class Sess02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.append("<form action='Sess02' method='post'>");
		writer.append("<div><input type='number' name='score'/><div>");
		writer.append("<div><input type='submit' name='submit'/><div>");
		writer.append("</form>");
		writer.append("<br>");
		writer.append("średnia ocen to: ").append(Double.toString(getAverageGrade(request)));
		

	}
	
	
	private double getAverageGrade(HttpServletRequest request) { 
		
		HttpSession sess = request.getSession(); 
		Object scoresObject = sess.getAttribute("scores"); 
		
		if(scoresObject == null) { 
			return 0; 
			
		} else { 
			
			List<Double> scores = (List<Double>) scoresObject; 
			Double sum = (double) 0; 
			for(Double d : scores) { 
			sum += d; 
				
			}
			
			return sum / scores.size();
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Double score = Double.valueOf(request.getParameter("score"));
		HttpSession sess = request.getSession(); 
		Object scoresObject = request.getSession().getAttribute("scores");
		
		//compareTo daje 1 jeżeli score > anoherDouble. 0 jeżeli równe, -1 jeżeli score < anotherDouble
		if(score.compareTo(1.0d) < 0 || score.compareTo(6.0d) > 0) { 
			
			response.getWriter().append("Zła ocena"); 
			
		} else { 
			
		
		if(scoresObject == null) { 
			List<Double> scores = new ArrayList<Double>();
			scores.add(score);
			sess.setAttribute("scores", scores);			
			
		} else {
			List<Double> scores = (List<Double>) scoresObject;
			scores.add(score);
			sess.setAttribute("scores", scores);
		}

		}
		
		
	}

}
