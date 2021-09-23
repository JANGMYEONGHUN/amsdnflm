<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<script type="text/javascript" src="/resources/js/memberJoin.js"></script>
	</head>
	<body>
		<h1>회원가입</h1>
		
		<form action="/member/join" method="post" onsubmit="return formCheck();">
			<table>
				<tr>
					<th>
						아이디(ID)
					</th>
					<td>
						<input type="text" id="memberId" name="memberId">
						<input type="button" value="중복확인" onclick="idCheck();">
					</td>					
				</tr>
				<tr>
					<th>
						비밀번호
					</th>
					<td>
						<input type="password" id ="memberPw" name="memberPw">
					</td>					
				</tr>
				<tr>
					<th>
						비밀번호 확인
					</th>
					<td>
						<input type="password" id ="pwCheck"> <!-- name이 있어야 데이터 전송할 수 있음 -->
					</td>					
				</tr>
				<tr>
					<td colspan="2">
						<input type="reset" value ="취소">
						<input type="submit" value ="가입하기">
					</td>
				</tr>
			</table>			
		</form>
		<input type="hidden" id="idTestCheck" value="false">		
	</body>
</html>