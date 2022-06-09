<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	회원
	<p>principal : <sec:authentication property="principal"/></p>
	<p>ID : <sec:authentication property="principal.userInfo.userId"/></p>
	<p>EMAIL : <sec:authentication property="principal.userInfo.userEmail"/></p>
	<p>NAME : <sec:authentication property="principal.userInfo.userName"/></p>
</body>
</html>