<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=cp%>/demo/request" method="post">
		<p>아이디 : <input type="text" name="id"/></p>
		<p>이름 : <input type="text" name="name"/></p>
		<p>생일 : <input type="text" name="birth"/></p>
		<p>전화번호 : <input type="text" name="tel"/></p>
		<p><button type="submit">등록하기</button></p>
	</form>
</body>
</html>