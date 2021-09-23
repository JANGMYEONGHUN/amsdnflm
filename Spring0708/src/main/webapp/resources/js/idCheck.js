function formCheck(){
	var idCheck = document.getElementById("idCheck").value;
	if(idCheck.length < 3 || idCheck.length > 10){
		alert("ID는 3~10자리로 입력해주세요.");
		return false;
	}
	
	return true;
}

function confirmId(){
	var idCheck  = document.getElementById("idCheck").value;
	//window.opener -> 자기를 열어준 창을 가리킴
	window.opener.document.getElementById("memberId").value = idCheck;
	window.opener.document.getElementById("idTestCheck").value = "true";
	
	window.close();
}

function idCheckKeyUp(){
	document.getElementById("confirmBtn").disabled="disabled";
}