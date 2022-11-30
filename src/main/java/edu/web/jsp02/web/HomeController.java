package edu.web.jsp02.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name = "homeController", urlPatterns = { "" })
// "http://localhost:8888/jsp02" (context root) 요청 주소를 처리하는 서블릿
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    System.out.println("homeController.doGet()");
	    
	    // 뷰(View)로 이동(forward)
	    request.getRequestDispatcher("/WEB-INF/home.jsp")
	        .forward(request, response);
	}

}
