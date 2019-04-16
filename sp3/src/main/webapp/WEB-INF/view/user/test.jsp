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
<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#sendButton").click(function(){
			var url = "<%=cp%>/test/main";
			var query = "age=10";
			
			$.ajax({
				type:"post",
				url:url,
				data:query,
				dataType:"json",
				success : function(data) {
					var age = data.age;
					var s = data.result;
					
					alert("성공 : " + age + ", " + s);
				},
				error : function(e) {
					console.log(e);
				}
				
			});
		});
	});
</script>
</head>
<body>
	<p>@Controller 예외처리 : <a href="<%=cp%>/user/main">확인</a></p>
	<p>
		@RestContoller 예외처리 :
		<button type="button" id="sendButton">확인</button>
	</p>
</body>
</html>