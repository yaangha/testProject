<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 2</title>
</head>
<body>

    <div>
        <h1>포스트 작성 페이지</h1>
        
        <nav>
            <ul>
                <%-- 로그인 정보가 있으면 --%>
                <c:if test="${ not empty signInUser }">
                    <li>
                        <span>${ signInUser }</span>
                        <c:url var="signOutPage" value="/user/signout"></c:url>
                        <a href="${ signOutPage }">로그아웃</a>
                    </li>
                </c:if>
                <li>
                    <c:url var="mainPage" value="/"></c:url>
                    <a href="${ mainPage }">메인 페이지</a>
                </li>
                <li>
                    <c:url var="postListPage" value="/post"></c:url>
                    <a href="${ postListPage }">목록 페이지</a>
                </li>
            </ul>
        </nav>
        
        <main>
            <!-- form의 action 속성: 요청을 보내는 주소. 
            생략된 경우에는 현재 페이지로 요청을 보냄. -->
            <form method="post">
                <div>
                    <input type="text" name="title" placeholder="제목" required autofocus />
                </div>
                <div>
                    <textarea rows="5" cols="80" name="content" placeholder="내용" required ></textarea>
                </div>
                <div>
                    <%-- 작성자 input은 로그인한 사용자 아이디로 세팅. --%>
                    <input type="hidden" name="author" value="${ signInUser }" readonly />
                </div>
                <div>
                    <input type="submit" value="작성완료" />
                </div>
            </form>
        </main>
    </div>

</body>
</html>