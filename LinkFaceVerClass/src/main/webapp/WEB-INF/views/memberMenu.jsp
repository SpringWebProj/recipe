<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	메뉴
	<sec:authorize access="isAnonymous()">
		 <button class="login" onClick="location.href='/login'">로그인</button>
		 <button class="signUp" onClick="location.href='/singup'">회원가입</button>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<form action="logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
		<button>로그아웃</button>
	</form>
		<button class="member" onClick="location.href='/member'">회원페이지</button>
	</sec:authorize>
	
	<sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')">
		<button class="admin" onClick="location.href='/admin'">관리자페이지</button>
	</sec:authorize>
	
</body>
</html>