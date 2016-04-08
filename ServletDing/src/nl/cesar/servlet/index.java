package nl.cesar.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getQueryString();
		String prm = request.getParameter("isTitles");
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">");
		sb.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>");
		sb.append("<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>");
		sb.append("<title> Servlet redirect test.");
		sb.append("</title>");
		sb.append("<style>");
		sb.append("body { background-color: " + prm + "; }");
		sb.append("</style>");
		sb.append("</head>");
		sb.append("<body> Ik ben tekst: " + ((str != null && str.length() > 1) ? str.substring(1) : "nope"));
		
		sb.append("<p><form method=\"post\">");
		sb.append("<select id=\"isTitles\" name=\"isTitles\">");
		sb.append("<option value=\"red\">Rood</option>");
		sb.append("<option value=\"green\">Groen</option>");
		sb.append("<option value=\"blue\">Blauw</option>");
		sb.append("</select>");
		sb.append("<br><input type=\"submit\">");
		sb.append("</form>");
		
		sb.append("<p> Selected from list: " + prm);
		sb.append("<p> I am a cookie: " + request.getSession(false).toString());
		
		sb.append("<div class=\"dropdown\">");
		sb.append("<button class=\"btn btn-primary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\">Dropdown Menu");
		sb.append("<span class=\"caret\"></span></button>");
		sb.append("<ul class=\"dropdown-menu\">");
		sb.append("<li><a href=\"Product\">Project</a></li>");
		sb.append("</ul>");
		sb.append("</div>");
		
		sb.append("</body>");
		sb.append("</html>");
		
		response.getWriter().append(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
