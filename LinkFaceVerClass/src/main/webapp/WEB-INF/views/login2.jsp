<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="login">
		<div>
			<input type="text" name="username" value="member">
		</div>
		<div>
			<input type="password" name="password" value="member">
		</div>
		<div>
			<input type="submit">
		</div>
		<div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		</div>
	</form>
	<button>회원가입</button>
	<button>ID/PW 찾기</button>
</body>
</html>