package edu.web.jsp02.web.post;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.web.jsp02.domain.Post;
import edu.web.jsp02.service.PostService;
import edu.web.jsp02.service.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class PostSearchController
 */
@Slf4j
@WebServlet(name = "postSearchController", urlPatterns = { "/post/search" })
public class PostSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PostService postService;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostSearchController() {
        postService = PostServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        log.info("doGet()");
        
        // 요청 파라미터 분석 - 검색 타입, 검색 키워드
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        
        // postService 객체의 검색 서비스 메서드를 호출.
        List<Post> list = postService.search(type, keyword);
        
        // 검색 결과를 뷰에 전달.
        request.setAttribute("posts", list); // posts -> list.jsp의 테이블 items 이름..
        request.setAttribute("searchPage", true); // 목록 페이지/검색 페이지 구분을 위해서
        
        // 뷰로 페이지 이동(forward)
        request.getRequestDispatcher("/WEB-INF/post/list.jsp")
            .forward(request, response);
        
	}

}
