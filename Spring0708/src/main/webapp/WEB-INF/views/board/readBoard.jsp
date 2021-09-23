<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${board.boardTitle }</title>
		<style type="text/css">
			#boardContentTd{
				width: 600px;
				height: 300px;
				vertical-align: top;
			}
		</style>
		<script type="text/javascript">
			function deleteCheck(){
				var result  = confirm("정말 삭제하시겠습니까?");
				if(result){
					document.getElementById("deleteForm").submit();
				}
			}
			function updateReply(replyNum){
				var td = document.getElementById("reply"+replyNum);
				var replyContent = document.getElementById("replyContent"+replyNum).innerHTML;
				var str = "";
				str += "<form action ='/board/updateReply' method='post'>";
				str += "		<textarea name='replyContent'>";
				str += 			replyContent;
				str += "		</textarea>";
				str += "		<input type ='submit' value='수정하기'>";
				str += " 	<input type ='hidden' name='replyNum' value='"+replyNum+"'>"; //유동적으로 변하는값을 표현하기 위해 끊은것임
				str += "		<input type ='hidden' name='boardNum' value='"+${board.boardNum }+"'>";  
				str += "</form>";
				
				td.innerHTML = str;
			}
			function deleteReply(replyNum){
				var result = confirm("정말로 댓글을 삭제하시겠습니까?");
				if(result){
					location.href = "/board/deleteReply?replyNum=" + replyNum + "&boardNum=" + ${board.boardNum }; //loction.href는 get방식으로 함. 만약 post방식으로 하면 폼만들어야함.
				}											//?는 뒤에 적은 데이터를 가지고가는것임(뒤에 적는 데이터는 데이터의 name을 적음).replyNum과 boardNum 정보를 같이 가져가니까 &로 묶은거임
			}
		</script>
	</head>
	<body>
	<!-- 게시글 본문 테이블 -->
		<table border ="1">
			<tr>
				<td>${board.boardNum }</td>
				<td>${board.boardTitle }</td>
				<td>${board.memberId }</td>
				<td>${board.boardIndate }</td>
				<td>${board.count }</td>
			</tr>
			<tr>
				<td colspan ="5" id ="boardContentTd">
					${board.boardContent }
				</td>
			</tr>			
			<c:if test="${not empty file }">
				<tr>
					<td colspan="5">
						<img src="/images/${file.savedFileName }"><br>
						<a href ="/board/download?boardNum=${board.boardNum }">
							${file.originalFileName }
						</a>
					</td>							
				</tr>
			</c:if>
			<c:if test="${sessionScope.memberId == board.memberId }">
				<tr>
					<td colspan="5">
						<input type="button" value="수정" onclick="location.href='updateBoard?boardNum=${board.boardNum}';"> <!-- ?뒤에 조건은 컨트롤러로 넘어감 -->
						<input type="button" value="삭제" onclick="deleteCheck();">
					</td>
				</tr>						
			</c:if>
		</table>
		
		<!-- 댓글 테이블 댓글하나하나 마다 수정삭제 나타나게한거-->
			<table border="1">
			<c:forEach items="${replyList }" var="reply">
				<tr>
					<th>${reply.memberId }</th>
					<td id="reply${reply.replyNum }">
						<span id ="replyContent${reply.replyNum }">${reply.replyContent }</span>
						<c:if test="${reply.memberId == sessionScope.memberId }">
							<input type="button" value="수정" 
								onclick="updateReply(${reply.replyNum });">
							<input type="button" value="삭제" onclick="deleteReply(${reply.replyNum });">
						</c:if>
					</td>
					<td>${reply.replyIndate }</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="3">
						<form action="/board/writeReply" method="post"> <!-- 댓글작성 버튼 누르면 action에 있는 /board/writeReply의 Controller로 데이터를 가지고 넘어감 --> 
							<textarea rows="3" name="replyContent" style="width:439px; resize:none;"></textarea>
							<input type="submit" value="댓글 작성">
							<input type="hidden" name="boardNum" value="${board.boardNum }">
						</form>
					</td>
				</tr>
			</table><!--js에서 밑에 딜리트 서브밋 시켜주고잇어서 js실행되면 이것도 같이실행  -->
		<form id="deleteForm" action="/board/deleteBoard" method="post">
			<input type="hidden" name="boardNum" value="${board.boardNum }">
		</form>
	</body>
</html>