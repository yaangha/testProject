<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MVC</title>
    
    <!-- Bootstrap에서 사용하는 CSS 파일 링크 -->
    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" 
        crossorigin="anonymous">
        
    <style>
        div a {
            text-decoration: none;
            color: black;
        }
    </style>

</head>
<body>
    
<div>
    <div>
        <h1>메인 페이지</h1>
    </div>
    
    <br/>
    
    <div>
        <ul class="list-group list-group-horizontal">
            <%-- 로그인 정보가 있는 경우 --%>
            <%-- EL not empty 연산자: not null && !""(null이 아니고, 문자열인 경우 빈 문자열이 아닌 경우) --%>
            <c:if test="${ not empty signInUser }">
                <li class="list-group-item">
                    <c:url var="signOutPage" value="/user/signout"></c:url>
                    <span>${ signInUser }</span>
                    <a href="${ signOutPage }">로그아웃</a>
                </li>
            </c:if>
            
            <%-- 로그인 정보가 없는 경우 --%>
            <c:if test="${ empty signInUser }">
                <li class="list-group-item">
                    <c:url var="signInPage" value="/user/signin"></c:url>
                    <a href="${ signInPage }">로그인</a>
                </li>
                <li class="list-group-item">
                    <c:url var="signUpPage" value="/user/signup"></c:url>
                    <a href="${ signUpPage }">회원가입</a>
                </li>
            </c:if>
            
            <li class="list-group-item">
                <c:url var="userList" value="/user"></c:url>
                <a href="${ userList }">USER List</a> 
            </li>
            
            <li class="list-group-item">
                <c:url var="postList" value="/post"></c:url>
                <a href="${ postList }">POST List</a>
            </li>
            
        </ul>
    </div>
    
</div>
    
    <!-- Bootstrap이 사용하는 JavaScript 파일 포함 -->
    <script 
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" 
        crossorigin="anonymous">
    </script>    
    
</body>
</html>
