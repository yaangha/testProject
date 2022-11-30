<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP 2</title>
</head>
<body>

<div>
    <div>
        <h1>로그인 페이지</h1>
    </div>
    
    <div>
        <form method="post">
            <div>
                <input type="text" name="userName" placeholder="아이디" required autofocus />
            </div>
            <div>
                <input type="password" name="password" placeholder="비밀번호" required />
            </div>
            <div>
                <input type="submit" value="로그인" />
            </div>
        </form>
    </div>
</div>

</body>
</html>