<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>
<div class="header-top">
    <div class="header-left">
        <p style="margin: 2px;">
            <a href="<%=cp%>/" style="text-decoration: none;">
                <span style="width: 200px; height: 70; position: relative; left: 0; top:20px; color: #2984ff; filter: mask(color=red) shadow(direction=135) chroma(color=red);font-style: italic; font-family: arial black; font-size: 30px; font-weight: bold;">SPRING</span>
            </a>
        </p>
    </div>
    <div class="header-right">
        <div style="padding-top: 20px;  float: right;">
            <c:if test="${empty sessionScope.member}">
                <a href="<%=cp%>/">로그인</a>
                    &nbsp;|&nbsp;
                <a href="<%=cp%>/">회원가입</a>
            </c:if>
            <c:if test="${not empty sessionScope.member}">
                <span style="color:blue;">${sessionScope.member.userName}</span>님
                    &nbsp;|&nbsp;
                    <a href="<%=cp%>/">로그아웃</a>
                    &nbsp;|&nbsp;
                    <a href="<%=cp%>/">정보수정</a>
            </c:if>
        </div>
    </div>
</div>

<div class="menu">
    <ul class="nav">
        <li><a href="#">방명록</a></li>
        <li><a href="<%=cp%>/bbs/list">게시판</a></li>
        <li><a href="#">질문 답변</a></li>
        <li><a href="#">공지사항</a></li>
        <li><a href="#">일정관리</a></li>
			
        <li style="float: right;"><a href="#"><span style="font-family: Wingdings; font-size: 20px;">+</span></a></li>
    </ul>      
</div>
