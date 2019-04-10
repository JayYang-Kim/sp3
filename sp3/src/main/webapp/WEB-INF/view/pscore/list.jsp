<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>spring</title>
<style type="text/css">
*{
    margin:0; padding: 0;
}
body {
    font-size:14px;
	font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
}
a{
    color:#000000;
    text-decoration:none;
    cursor:pointer;
}
a:active, a:hover {
    text-decoration: underline;
    color:tomato;
}
.title {
    font-weight:bold;
    font-size:16px;
    font-family:나눔고딕, "맑은 고딕", 돋움, sans-serif;
}
.btn {
    color:#333333;
    font-weight:500;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
    border:1px solid #cccccc;
    background-color:#ffffff;
    text-align:center;
    cursor:pointer;
    padding:3px 10px 5px;
    border-radius:4px;
}
.btn:active, .btn:focus, .btn:hover {
    background-color:#e6e6e6;
    border-color:#adadad;
    color:#333333;
}
.boxTF {
    border:1px solid #999999;
    padding:3px 5px 5px;
    border-radius:4px;
    background-color:#ffffff;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
}
.selectField {
    border:1px solid #999999;
    padding:2px 5px 4px;
    border-radius:4px;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
}
</style>

<script type="text/javascript">
function deleteScore(hak) {
	var url="<%=cp%>/pscore/delete?hak="+hak+"&page=${page}";
	if(confirm("자료를 삭제 하시겠습니까?")) {
		location.href=url;
	}
}

function updateScore(hak) {
	var url="<%=cp%>/pscore/update?hak="+hak+"&page=${page}";
	location.href=url;
}
</script>

</head>
<body>

<div style="width: 700px; margin: 30px auto 0px;">
<table style="width: 100%; margin: 0px auto;">
<tr height="50">
	<td align="center" colspan="2">
	    <span style="font-size: 15pt; font-family: 맑은 고딕, 돋움; font-weight: bold;">성적처리</span>
	</td>
</tr>

<tr height="35">
	<td width="70%">
		&nbsp;
	</td>
	<td align="right">
		<input type="button" value="등록하기" class="btn"
		           onclick="javascript:location.href='<%=cp%>/pscore/write';">
	</td>
</tr>
</table>

<table style="width: 100%; margin: 0px auto; border-spacing: 1px; background: #cccccc;">
<tr height="30" bgcolor="#eeeeee" align="center">
	<th width="60">학번</th>
	<th width="80">이름</th>
	<th width="90">생년월일</th>
	<th width="70">국어</th>
	<th width="70">영어</th>
	<th width="70">수학</th>
	<th width="70">총점</th>
	<th width="70">평균</th>
	<th width="120">수정</th>
</tr>

<c:forEach var="dto" items="${list}">
<tr height="35" bgcolor="#ffffff" align="center">
	<td>${dto.hak}</td>
	<td>${dto.name}</td>
	<td>${dto.birth}</td>
	<td>${dto.kor}</td>
	<td>${dto.eng}</td>
	<td>${dto.mat}</td>
	<td>${dto.tot}</td>
	<td>${dto.ave}</td>
	<td>
		<a href="javascript:updateScore('${dto.hak}')">수정</a> | 
		<a href="javascript:deleteScore('${dto.hak}')">삭제</a>
	</td>
</tr>
</c:forEach>
</table>

<table style="width: 100%; margin: 10px auto;">
<tr height="30" align="center">
	<td>
		${paging}
	</td>
</tr>
</table>
</div>

</body>
</html>