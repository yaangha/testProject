package edu.web.jsp02.web.post;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.web.jsp02.dto.PostCreateDto;
import edu.web.jsp02.service.PostService;
import edu.web.jsp02.service.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class PostCreateController
 */
@Slf4j
@WebServlet(name = "postCreateController", urlPatterns = { "/post/create" })
public class PostCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PostService postService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostCreateController() {
        postService = PostServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override // tomcat이 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    log.info("doGet()");
	    
	    // 포스트 작성 뷰(JSP) 이동
	    // WEB-INf: 해당 폴더에 있는 파일은 외부(브라우저)에서 '직접' 접근 불가
	    //     --> forward: 서버 내 이동(WEB-INf 접근 가능) vs redirect: 서버 외부로 이동(WEB-INf 접근 불가)
	    request.getRequestDispatcher("/WEB-INF/post/create.jsp") 
	        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {

        log.info("doPost()");
	   
        // request.setCharacterEncoding("UTF-8"); -> Filter로 해결~!
	    
	    // 요청 파라미터 분석: title, content, author 값을 찾음.
	    String title = request.getParameter("title");
	    String content = request.getParameter("content");
	    String author = request.getParameter("author");
	    
	    // PostCreateDto 타입 객체 생성
	    // PostCreateDto dto = new PostCreateDto(title, content, author);
	    PostCreateDto dto = PostCreateDto.builder()
	            .title(title).content(content).author(author)
	            .build();
	    
	    log.info("dto = {}", dto);
	    
	    // postService.create(dto) 메서드 호출 --> postDao 호출 --> DB에 저장
	    // PostServiceImpl postService = PostServiceImpl.getInstance(); --> 위에 필드로 생성, 생성자에서 초기화
	    int result = postService.create(dto);
	    log.info("create result = {}", result);
	    
	    // 포스트 목록 페이지 이동(redirect)
        // WEB-INf: 해당 폴더에 있는 파일은 외부(브라우저)에서 '직접' 접근 불가
        //     --> forward: 서버 내 이동(WEB-INf 접근 가능) vs redirect: 서버 외부로 이동(WEB-INf 접근 불가)
	    response.sendRedirect("/jsp02/post");
	    
	    // PRG(Post - Redirect - Get) 패턴
	    
	}

}
