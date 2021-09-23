<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ID 중복검사</title>
		<script type="text/javascript" src="/resources/js/idCheck.js"></script>		
	</head>
	<body>
		<h1>ID 중복검사</h1>
		<form action="/member/idCheck" method = "post" onsubmit ="return formCheck();">
			<input type="text" id = "idCheck" name="idCheck" onkeyup ="idCheckKeyUp();">
			<input type="submit" value="ID조회">
		</form>
		<input type="button" id="confirmBtn" value="사용하기" onclick="confirmId();" disabled="disabled">
		
		<script type="text/javascript">
			var result = "${result }";
				
			if(result == ""){
			}else if(result == "true"){
				alert("사용할 수 있는 ID입니다.");
				document.getElementById("idCheck").value = "${idCheck }";
				document.getElementById("confirmBtn").disabled = "";
			}else{
				alert("중복된 ID입니다.")
			}
		</script>
	</body>
</html>