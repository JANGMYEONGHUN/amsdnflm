function formCheck(){
	var idTestCheck = document.getElementById("idTestCheck").value;
	if(idTestCheck == "false"){
		alert("ID 중복확인을 진행해주세요.")
		return false;
	}
	
	var memberPw = document.getElementById("memberPw").value;
	if(memberPw.length < 4 || memberPw.length > 12){
		alert("비밀번호는 4~12글자 사이로 입력해주세요.");
		return false;
	}	
	var pwCheck = document.getElementById("pwCheck").value;
	if(memberPw != pwCheck){
		alert("비밀번호와 비밀번호 확인의 값을 동일하게 입력해주세요.");
		return false;
	}	
	return true;
}

function idCheck(){//아이디 중복확인
	var openWindow = window.open("/member/idCheck","_blank","width=600, height=800");
	var memberId = document.getElementById("memberId").value;
	openWindow.onload = function(){
		openWindow.document.getElementById("idCheck").value = memberId;
	}	
}




