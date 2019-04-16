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
	<form action="<%=cp%>/user/request" method="post" enctype="multipart/form-data">
		<p>
			<span>제목 : </span>
			<input type="text" name="subject"/>
		</p>
		<p>
			<span>파일: </span>
			<input type="file" name="selectFile"/>
		</p>
		<p>
			<button>등록하기</button>
		</p>
	</form>
</body>
</html>