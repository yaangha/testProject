package edu.web.jsp02.web.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.web.jsp02.domain.User;
import edu.web.jsp02.service.UserService;
import edu.web.jsp02.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class UserSignInController
 */
@Slf4j
@WebServlet(name = "userSignInController", urlPatterns = { "/user/signin" })
public class UserSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignInController() {
        userService = UserServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        log.info("doGet()");
        
        request.getRequestDispatcher("/WEB-INF/user/signin.jsp")
            .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        log.info("doPost()");
        
        // 로그인 페이지의 요청 파라미터를 분석
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        
        // UserService 메서드를 호출해서 로그인 처리 - 아이디/비밀번호가 일치하는 사용자 정보가 있는 지 DB에서 검색(select).
        User user = userService.signIn(userName, password);
        
        // 로그인 성공/실패 경우에 redirect
        if (user == null) { // 로그인 실패
            log.info("로그인 실패");
            response.sendRedirect("/jsp02/user/signin"); // 로그인 페이지로 이동
            return; // doPost() 메서드 종료
        }
        
        // 로그인 성공: 
        // (1) 세션(session)에 로그인 정보 저장. 
        HttpSession session = request.getSession();
        // 로그인 사용자 아이디를 세션에 저장
        session.setAttribute("signInUser", user.getUserName());
        // EL scope: pageScope -> requestScope -> sessionScope -> applicationScope
        
        // (2) 적절한 페이지로 이동.
        response.sendRedirect("/jsp02/post"); // 포스트 목록 페이지로 이동
        
	}

}
