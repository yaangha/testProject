package edu.web.jsp02.web.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.web.jsp02.domain.User;
import edu.web.jsp02.service.UserService;
import edu.web.jsp02.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class UserSearchPage
 */
@Slf4j
@WebServlet(name = "userSearchController", urlPatterns = { "/user/search" })
public class UserSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchController() {
        userService = UserServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    log.info("doget()");
	    
	    String type = request.getParameter("type");
	    String keyword = request.getParameter("keyword");
	    
	    List<User> list = userService.search(type, keyword);
	    
	    request.setAttribute("users", list); // keyword에 맞는 리스트 넘기기
	    request.setAttribute("searchPage", true);
	    
	    request.getRequestDispatcher("/WEB-INF/user/list.jsp")
	        .forward(request, response);
	}

}
