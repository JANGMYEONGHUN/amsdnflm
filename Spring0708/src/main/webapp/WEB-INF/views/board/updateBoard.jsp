<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 수정</title>		
	</head>
	<body>
			<form action="/board/updateBoard" method ="post">
			<table border ="1">
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name ="boardTitle" value="${board.boardTitle }">
					</td>					
				</tr>
				<tr>
					<th colspan="2">내용</th>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="10" name="boardContent" style="width:205px;resize: none;">${board.boardContent }</textarea>
					</td>
				</tr>
			</table>
			<input type="submit" value="수정하기">
			<input type="button" value="게시글 목록 페이지로 돌아가기" onclick="location.href='/board/boardList';">
			<input type="hidden" name="boardNum" value=${board.boardNum }>
		</form>
	</body>
</html>