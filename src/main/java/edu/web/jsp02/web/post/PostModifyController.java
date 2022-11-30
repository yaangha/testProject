package edu.web.jsp02.web.post;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.web.jsp02.domain.Post;
import edu.web.jsp02.dto.PostUpdateDto;
import edu.web.jsp02.service.PostService;
import edu.web.jsp02.service.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class PostModifyController
 */
@Slf4j
@WebServlet(name = "postModifyController", urlPatterns = { "/post/modify" })
public class PostModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PostService postService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostModifyController() {
        postService = PostServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        log.info("doGet()");
        
        // 요청 파라미터 id(포스트 번호) 찾기.
        Integer id = Integer.valueOf(request.getParameter("id"));
        log.info("id = {}", id);
        
        // id(포스트 번호)로 레코드 찾기.
        Post post = postService.read(id);
        
        // 뷰에 전달.
        request.setAttribute("post", post); 
        request.getRequestDispatcher("/WEB-INF/post/modify.jsp")
            .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        // 포스트(제목 또는 내용) 수정.
        log.info("doPost()");
        
        // 요청 파라미터 분석.
        Integer id = Integer.valueOf(request.getParameter("id")); // 변경할 글 번호
        String title = request.getParameter("title"); // 변경할 글 제목
        String content = request.getParameter("content"); // 변경할 글  내용
        
        // postService 객체에게 전달할 DTO 생성
        PostUpdateDto dto = PostUpdateDto.builder()
                .id(id).title(title).content(content)
                .build();
        
        // postService 메서드 호출 -> DB에 업데이트 되도록.
        int result = postService.update(dto);
        log.info("post update result = {}", result);
        
        // 상세보기 이동(Redirect)
        response.sendRedirect("/jsp02/post/detail?id=" + id);
        
        // PRG 패턴
	}

}
