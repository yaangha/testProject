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
    <h1>회원정보 수정 페이지</h1>
    
    <nav>
        <ul>
            <li>
                <c:url var="mainPage" value="/"></c:url>
                <a href="${ mainPage }">메인 페이지</a>
            </li>
            <li>
                <c:url var="userListPage" value="/user"></c:url>
                <a href="${ userListPage }">회원 목록 페이지</a>
            </li>
        </ul>
    </nav>
    
    <main>
        <form id="userForm">
            <div>
                <label for="id">번호</label>
                <input id="id" name="id" type="text" value="${ user.id }" readonly />
            </div>
            <div>
                <label for="userName">아이디</label>
                <input id="userName" name="userName" type="text" value="${ user.userName }" />
            </div>
            <div>
                <label for="password">비밀번호</label>
                <input id="password" name="password" type="text" value="${ user.password }" />
            </div>
            <div>
                <label for="email">이메일</label>
                <input id="email" name="email" type="text" value="${ user.email }" />
            </div>
            <div>
                <button id="btnDelete">삭제</button>
                <button id="btnUpdate">수정</button>
            </div>
        </form>
    </main>
    
    <c:url var="userDeletePage" value="/user/delete"></c:url>
    <c:url var="userModifyPage" value="/user/modify"></c:url>
    
    <script>
    const form = document.querySelector('#userForm');
    
    const btnDelete = document.querySelector('#btnDelete');
    
    btnDelete.addEventListener('click', function(event) {
    	event.preventDefault();
    	
    	const check = confirm('정말 삭제하시겠습니까?');
    	if (check) {
    		form.action = '${ userDeletePage }';
    		form.method = 'post';
    		form.submit();
    	}
    	
    });
    
    const btnUpdate = document.querySelector('#btnUpdate');
    
    btnUpdate.addEventListener('click', function(event) {
    	event.preventDefault();
    	
    	const userName = document.querySelector('#userName').value;
    	const password = document.querySelector('#password').value;
    	const email = document.querySelector('#email').value;
    	
    	if (userName == '' || password == '' || email == '') {
    		alert('아이디와 비밀번호, 이메일 주소는 반드시 입력해야 합니다.');
    		return;
    	}
    	
    	const check = confirm('수정하시겠습니까?');
    	if (check) {
            form.action = '${ userModifyPage }';
            form.method = 'post';
            form.submit();    		
    	}
    	
    })
    
    </script>
    
</body>
</html>