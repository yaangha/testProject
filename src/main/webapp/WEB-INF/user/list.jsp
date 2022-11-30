<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 2</title>

    <!-- Bootstrap에서 사용하는 CSS 파일 링크 -->
    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" 
        crossorigin="anonymous">
        
    <style>
        .list {
            text-decoration: none;
            color: black;
        }
    </style>
        
</head>
<body>
    <h1>User 목록 페이지</h1>
    
    <nav>
        <ul class="list-group list-group-horizontal">
            <li class="list-group-item">
                <c:url var="mainPage" value="/"></c:url>
                <a class="list" href="${ mainPage }">메인 페이지</a>
            </li>
            
            <c:if test="${ searchPage }">
                <li class="list-group-item">
                    <c:url var="userList" value="/user"></c:url>
                    <a class="list" href="${ userList }">전체 목록 보기</a>
                </li>
            </c:if>
            
            <li class="list-group-item">
                <c:url var="userSignUpPage" value="/user/signup"></c:url>
                <a class="list" href="${ userSignUpPage }">회원가입</a>
            </li>
        </ul>
    </nav>
    
    <br/>
    
    <main>
        <div>
            <c:url var="userSearchPage" value="/user/search"></c:url>
            <form method="get" action="${ userSearchPage }"> 
                <select name="type">
                    <option value="u">아이디</option>
                    <option value="e">이메일</option>
                </select>
                <input type="text" name="keyword" placeholder="검색어를 입력하세요." required />
                <input type="submit" value="찾기" />
            </form>
        </div>
    
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>아이디</th>
                    <th>이메일</th>
                    <th>포인트</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="u" items="${ users }">
                    <tr>
                        <td>${ u.id }</td>
                        <td>
                            <c:url var="userDetailPage" value="/user/detail">
                                <c:param name="id" value="${ u.id }"></c:param>
                            </c:url>
                            <a href="${ userDetailPage }">${ u.userName }</a>
                        </td>
                        <td>${ u.email }</td>
                        <td>${ u.points }</td>
                    </tr>
                    
                </c:forEach>
            </tbody>
        </table>
        
        
    </main>
    
    <!-- Bootstrap이 사용하는 JavaScript 파일 포함 -->
    <script 
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" 
        crossorigin="anonymous">
    </script>  
    
</body>
</html>