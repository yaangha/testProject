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

<div>
    <header>
        <h1>회원 가입 페이지</h1>
    </header>
</div>
    
<nav>
    <ul class="list-group list-group-horizontal">
        <li class="list-group-item">
            <c:url var="mainPage" value="/"></c:url>
            <a class="list" href="${ mainPage }">메인 페이지</a>
        </li>
        <li class="list-group-item">
            <c:url var="userListPage" value="/user" ></c:url>
            <a class="list" href="${ userListPage }">회원 목록 페이지</a>
        </li>
    </ul>
</nav>
    
<br/>
    
<div>
    <form method="post">
        <div class="input-group">
            <span class="input-group-text">ID</span>
            <input type="text" name="userName" class="form-control" placeholder="ID"  required autofocus />
        </div>
        
        <div class="input-group">
            <span class="input-group-text">PW</span>
              <input type="password" name="password" class="form-control" placeholder="PASSWORD" required />
        </div>
        
        <div class="input-group">
            <span class="input-group-text">이메일</span>
            <input type="email" name="email" class="form-control" placeholder="example@example.com" aria-describedby="basic-addon1" required />
        </div>
        
        <div>
            <input type="submit" value="회원가입" />
        </div>
    </form>
</div>    

<!-- Bootstrap이 사용하는 JavaScript 파일 포함 -->
<script 
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" 
    crossorigin="anonymous">
</script>  
    
</body>
</html>