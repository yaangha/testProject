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
        <h1>포스트 수정 페이지</h1>
        
        <nav>
            <ul>
                <c:if test="${ not empty signInUser }">
                    <li>
                        <span>${ signInUser }</span>
                        <c:url var="signOutPage" value="/uesr/signout"></c:url>
                        <a href="${ signOutPage }">로그아웃</a>
                    </li>                
                </c:if>
                <li>
                    <c:url var="mainPage" value="/"></c:url>
                    <a href="${ mainPage }">메인 페이지</a>
                </li>
                <li>
                    <c:url var="postListPage" value="/post"></c:url>
                    <a href="${ postListPage }">포스트 목록</a>
                </li>
                <li>
                    <c:url var="postDetailPage" value="/post/detail">
                        <c:param name="id" value="${ post.id }"></c:param>
                    </c:url>
                    <a href="${ postDetailPage }">포스터 상세</a>
                </li>
            </ul>
        </nav>
        
        <main>
            <form id="postForm">  
            <!-- action: 제출(submit) 주소. 기본값은 현재 페이지 주소.
             method: 제출(submit) 방식. 기본값은 'get' 방식. -->
                <div>
                    <label for="id">번호</label>
                    <input id="id" type="text" name="id" value="${ post.id }" readonly />
                </div>
                <div>
                    <label for="title">제목</label>
                    <input id="title" type="text" name="title" value="${ post.title }" required autofocus />
                </div>
                <div>
                    <label for="content">내용</label>
                    <textarea id="content" type="text" name="content" required>${ post.content }</textarea>
                </div>
                <div style="display: none;">
                    <label for="author">작성자</label>
                    <input id="author" type="hidden" value="${ post.author }" readonly />
                </div>
                
                <c:if test="${ signInUser == post.author }">
                    <div>
                        <button id="btnDelete">삭제</button>
                        <button id="btnUpdate">수정완료</button>
                        <!-- form 안에서 작성된 버튼들은 form의 action 주소로 method 방식의 요청을 보냄. -->
                    </div>                 
                </c:if>
            </form>
        </main>
    </div>
    
    <c:url var="postDeletePage" value="/post/delete"></c:url>
    <c:url var="postUpdatePage" value="/post/modify"></c:url>    
    
    <script>
    // id="postForm" 인 HTML 요소를 찾음.
    const form = document.querySelector('#postForm');
    
    // id="btnDelete" 인 버튼을 찾음.
    const btnDelete = document.querySelector('#btnDelete');
    
    // 버튼 클릭 이벤트 리스너를 등록.
    btnDelete.addEventListener('click', function(event) {
    	event.preventDefault(); 
    	// 이벤트 기본 처리방식을 막음(실행되지 않도록 함).
    	// -> 폼 양식이 서버로 제출(submit)되지 않도록 함. 
    	
        // 사용자에게 삭제 확인.
        const check = confirm('정말 삭제?');
    	if (check) { // 사용자 "확인"을 선택했을 때
    		form.action = '${ postDeletePage }'; // 제출 요청 주소
    		form.method = 'post'; // 제출 요청 방식
    		form.submit(); // 서버로 제출(데이터 전송)
    	}
    });
    
    // id="btnUpdate" 인 버튼을 찾음.
    const btnUpdate = document.querySelector('#btnUpdate');
    // 버튼의 'click' 이벤트 리스너를 등록.
    btnUpdate.addEventListener('click', function(event) {
    	event.preventDefault(); // 버튼 기본 기능(submit)을 막음.
    	
    	// <input> title에 입력된 값
    	const title = document.querySelector('#title').value;
    	// <textarea> content에 입력된 값
    	const content = document.querySelector('#content').value;
    	
    	if (title == '' || content == '') {
    		alert('제목과 내용은 반드시 입력해야 합니다.');
    		return; // 이벤트 리스너 종료
    	}
    	
    	// 수정 완료 체크
    	const check = confirm('수정하시겠습니까?');
    	if (check) {
    		form.action = '${ postUpdatePage }';
    		form.method = 'post';
    		form.submit();
    	}
    	
    });
    
    </script>

</body>
</html>