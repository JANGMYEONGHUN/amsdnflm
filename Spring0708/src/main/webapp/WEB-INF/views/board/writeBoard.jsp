<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 작성</title>
		<script type="text/javascript">
			function formCheck(){
				var uploadFile = document.getElementById("uploadFile").files[0];
				var fileName = uploadFile.name;
				var fileSize = uploadFile.size;
				
				//console.log(uploadFile);
				//console.log(fileName);
				//console.log(fileSize);
				
				var ext = fileName.substring(fileName.lastIndexOf("."),fileName.length);
				
				//console.log(extension);
				
				if(ext == ".jpg" || ext == ".jpeg" || ext == ".png"){
					return true;					
				}else{
					alert("파일 확장자를 확인해주세요(jpg, jpeg, png, gif 업로드 가능)");				
					return false;
				}				
			}
		</script>
	</head>
	<body>`
		<form action="/board/writeBoard" method ="post" enctype="multipart/form-data"  onsubmit="return formCheck();"> <!-- enctype없으면 파일이 전송이안됨 -->
			<table border ="1">
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name ="boardTitle">
					</td>					
				</tr>
				<tr>
					<th colspan="2">내용</th>
				</tr>
				<tr>
					<td colspan="2">
						<textarea name="boardContent" style="width:205px;height:300px;resize: none;"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="file" id ="uploadFile" name="uploadFile">
					</td>
				</tr>
			</table>
			<input type="submit" value="작성하기">
			<input type="button" value="게시글 목록 페이지로 돌아가기" onclick="location.href='/board/boardList';">
			<input type="hidden" name="memberId" value=${sessionScope.memberId }>
		</form>
	</body>
</html>