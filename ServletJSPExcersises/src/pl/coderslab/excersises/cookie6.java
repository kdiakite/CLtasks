package pl.coderslab.excersises;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cookie6
 */
@WebServlet("/cookie6")
public class cookie6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cookie6() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies(); 
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		
		String background = null;
		String text = null; 
		
		for(int i=0; i<cookies.length;i++) { 
			if("backgroundColor".equals(cookies[i].getName())) { 
				background = cookies[i].getValue();
			}
			
			if("textColor".equals(cookies[i].getName())) { 
				text = cookies[i].getValue();
			}
		}
		
		if(background!=null) { 
			response.getWriter().append("<body style=\"background-color:"+background+"\">");
			response.getWriter().append("Kolor tła został wybrany i jest "+background);
		} else { 
			
			response.getWriter().append("Nie wybrano koloru tła"); 
		}
		
		if(text!=null) { 
			response.getWriter().append("<font color=\""+text+"\">");
			response.getWriter().append("Kolor tekstu został wybrany i jest "+text);
		} else { 
			
			response.getWriter().append("Nie wybrano koloru tekstu");
			
		}
		
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String background = request.getParameter("background");
		String text = request.getParameter("text");
		
		Cookie backgroundColor = new Cookie("backgroundColor",background);
		Cookie textColor = new Cookie("textColor",text); 
		
		response.addCookie(backgroundColor);
		response.addCookie(textColor);
		response.getWriter().append("<body style=\"background-color:"+background+"\">");
		response.getWriter().append("<font color=\""+text+"\">");
		response.getWriter().append("Wybrałeś kolor tekstu: "+text+"<br>");
		response.getWriter().append("Wybrałeś kolor tła: "+background+"<br>");
		response.getWriter().append("</body>");
	}
}
