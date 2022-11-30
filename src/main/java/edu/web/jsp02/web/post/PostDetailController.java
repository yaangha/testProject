package edu.web.jsp02.web.post;

import java.io.IOException;
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
 * Servlet implementation class PostDetailController
 */
@Slf4j
@WebServlet(name = "postDetailController", urlPatterns = { "/post/detail" })
public class PostDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PostService postService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDetailController() {
        postService = PostServiceImpl.getInstance(); // 초기화
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        log.info("doGet()");
        
        // Query string에 포함된 요청 파라미터 id(Post 번호) 값을 읽음.
        Integer id = Integer.valueOf(request.getParameter("id")); // String => Integer 변환
        log.info("id = {}", id);
        
        // 서비스 객체의 메서드를 호출해서 DB에 저장된 해당 id의 Post를 읽음.
        Post post = postService.read(id);
        log.info("post = {}", post);
        
        // 뷰에 전달.
        request.setAttribute("post", post);
        request.getRequestDispatcher("/WEB-INF/post/detail.jsp")
            .forward(request, response);
	}

}
