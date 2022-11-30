package edu.web.jsp02.web.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.web.jsp02.dto.UserSignUpDto;
import edu.web.jsp02.service.UserService;
import edu.web.jsp02.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class UserCreateController
 */
@Slf4j
@WebServlet(name = "userSignUpController", urlPatterns = { "/user/signup" })
public class UserSignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignUpController() {
        userService = UserServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    log.info("doGet()");
	    
	    request.getRequestDispatcher("/WEB-INF/user/signup.jsp")
	        .forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    log.info("doPost()");
	    
	    // 요청 파라미터(아이디, 비밀번호, 이메일)를 분석
	    String userName = request.getParameter("userName");
	    String password = request.getParameter("password");
	    String email = request.getParameter("email");
	    
	    // Service Layer에 전달할 객체
	    UserSignUpDto dto = UserSignUpDto.builder()
	            .userName(userName).password(password).email(email)
	            .build();
	    
	    int result = userService.signUp(dto);
	    
	    log.info("회원 가입 결과 = {}", result);
	    
	    if (result == 1) { // 회원 가입 성공
	        response.sendRedirect("/jsp02/user/signin"); // 로그인 페이지로 이동
	    } else { // 회원 가입 실패
	        response.sendRedirect("/jsp02/user/signup"); // 회원 가입 페이지로 이동
	    }
	    
	}

}
