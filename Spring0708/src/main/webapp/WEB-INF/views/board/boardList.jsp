<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판</title>
	</head>
	<body>
		<h1>게시판</h1>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${boardList }" var="board">
				<tr>
					<td>${board.boardNum }</td>
					<td>
						<a href ="/board/readBoard?boardNum=${board.boardNum }">${board.boardTitle }</a>
					</td>            <!--    /->이동할 주소 ?->가지고가는 데이터        -->
					<td>${board.memberId }</td>
					<td>${board.boardIndate }</td>
					<td>${board.count }</td>
				</tr>				
			</c:forEach>
		</table>
		<input type="button" value = "게시글 작성" onclick="location.href = '/board/writeBoard';"><br>
		
		<form action="/board/searchBoard" method="get">
			<select name = "condition">
				<option value = "boardTitle">제목</option>
				<option value = "boardContent">내용</option>
				<option value = "memberId">작성자</option>
			</select>
			<input type="text" name = "searchWord">
			<input type="submit" value="검색">
		</form>		
	</body>
</html>