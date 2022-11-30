package edu.web.jsp02.web.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class UserSignOutController
 */
@Slf4j
@WebServlet(name = "userSignOutController", urlPatterns = { "/user/signout" })
public class UserSignOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    log.info("doGet()");
	    
	    // 로그아웃:
	    // (1) 세션에 저장된 객체를 제거
	    HttpSession session = request.getSession();
	    session.removeAttribute("signInUser"); 
	    // session.setAttribute()에서 사용한 속성 이름을 제거.
	    
	    // (2) 세션 객체 무효화
	    session.invalidate();
	    
	    // 로그아웃 이후에는 로그인 페이지로 이동
	    response.sendRedirect("/jsp02/user/signin");
	    
	}

}
