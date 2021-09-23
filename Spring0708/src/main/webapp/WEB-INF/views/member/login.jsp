<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
		<script type="text/javascript" src="/resources/js/login.js"></script>
	</head>
	<body>
		<h1>로그인</h1>
		<form action="/member/login" method="post">
			<table>
				<tr>
					<th>ID</th>
					<td>
						<input type="text" id ="memberId" name = "memberId">
					</td>
					<td id="idCheck"></td>
				</tr>
				<tr>
					<th>PW</th>
					<td>
						<input type="password" id="memberPw" name ="memberPw">
					</td>
					<td id="pwCheck"></td>
				</tr>
				<tr>				
					<td colspan="3">
						<input type="submit" value="로그인">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>