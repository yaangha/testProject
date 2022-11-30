package edu.web.jsp02.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@Slf4j
@WebFilter(
        filterName = "authenticationFilter", 
        urlPatterns = { "/post/detail", "/post/create", "/post/modify", "/post/delete" }
)
// 인증이 필요한 요청 주소들에 대해서 로그인 여부를 확인하고,
// 로그인이 되어 있으면 요청을 계속해서 처리(Servlet으로 요청을 전달)
// 로그인이 안 되어 있으면 Servlet으로 요청을 보내지 않고 로그인 페이지로 redirect.
public class AuthenticationFilter extends HttpFilter implements Filter {
    
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	        throws IOException, ServletException {
        log.info("doFilter()");
        
		// 로그인되어 있는 지를 체크 - 세션에 로그인 정보(signInUser)가 있는 지를 확인.
        HttpSession session = ((HttpServletRequest) request).getSession(); 
        Object signInUser = session.getAttribute("signInUser");

        if (signInUser != null) { // 로그인 정보가 있다면
            log.info("로그인 상태 - {}", signInUser);
            // pass the request along the filter chain
            chain.doFilter(request, response); // 필터 체인 순서대로 요청을 Servlet까지 전달.
            return; // 메서드 종료
        }
        
        log.info("로그아웃 상태");
        // 로그인 페이지로 이동(redirect)
        ((HttpServletResponse) response).sendRedirect("/jsp02/user/signin");
        
	}

}
