<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Home</title>
	</head>
	<body>
		<h1>
			Hello world!  
		</h1>
		
		<P>  The time on the server is ${serverTime}. </P>
		<c:if test="${empty sessionScope.memberId }">
			<a href="/member/join">회원가입</a> ★★a태그는 무조건 get 방식★★
			<br>
			<a href="/member/login">로그인</a>
		</c:if>
		<c:if test="${not empty sessionScope.memberId }">
			<a href="/member/logout">로그아웃</a>
			<a href="/board/boardList">게시판으로 이동</a>
		</c:if>				
		<%-- <c:choose>
			<c:when test="${empty sessionScope.memberId }">
				<a href="/member/join">회원가입</a> ★★a태그는 무조건 get 방식★★
				<br>
				<a href="/member/login">로그인</a>
			</c:when>
			<c:otherwise>
				<a href="/member/logout">로그아웃</a>
			</c:otherwise>
		</c:choose> --%>
	</body>
</html>
