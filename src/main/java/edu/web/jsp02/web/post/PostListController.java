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
 * Servlet implementation class PostListController
 */
@Slf4j // Logger 객체 자동 생성.
@WebServlet(name = "postListController", urlPatterns = { "/post" })
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PostService postService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostListController() {
        postService = PostServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        log.info("doGet()");
        
        // PostService 객체의 메서드를 호출해서 포스트 목록 전체를 읽어옴.
        List<Post> list = postService.read();
        log.info("# of list = {}", list.size());
        
        // 포스트 목록을 뷰에 전달하기 위해서 request 객체에 속성 값으로 저장.
        request.setAttribute("posts", list);
        
        // 뷰로 페이지 이동(forward)
        request.getRequestDispatcher("/WEB-INF/post/list.jsp")
            .forward(request, response);
	}

}
